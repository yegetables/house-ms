package com.xupt.house.config.shiro;

import com.xupt.house.common.constant.CommonConstant;
import com.xupt.house.entity.Permission;
import com.xupt.house.entity.Role;
import com.xupt.house.entity.User;
import com.xupt.house.enums.UserStatusEnum;
import com.xupt.house.service.PermissionService;
import com.xupt.house.service.RoleService;
import com.xupt.house.service.UserService;
import com.xupt.house.utils.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户信息认证
 */
@Slf4j
public class MyRealm extends AuthorizingRealm {

    //Subjrct:用户主体（登录，注销，判断授权等等一些的方法）（把操作交给SecurityManager）
    //
    //SecurityManager:安全管理器（关联Realm）
    //
    //Realm:Shiro连接数据的桥梁（操作查询数据库或配置文件，获取用户的信息）
    
    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private RoleService roleService;

    @Autowired
    @Lazy
    private PermissionService permissionService;

    /**
     * 认证信息(身份验证) Authentication 是用来验证用户身份
     * 执行一些认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("认证-->MyShiroRealm.doGetAuthenticationInfo()");
        //1.验证账号
        User user;
        String account = (String) token.getPrincipal();
        if (RegexUtil.isIdCard(account)) {
            user = userService.findByIdCard(account);
        } else {
            user = userService.findByUserName(account);
        }
        if (user == null) {
            //用户不存在
            log.info("用户不存在! 登录名:{}, 密码:{}", account, token.getCredentials());
            return null;
        }
        Role role = roleService.findByUserId(user.getId());
        if (role != null) {
            user.setRole(role);
        }

        //2.判断账号是否被封号
        if (!Objects.equals(user.getStatus(), UserStatusEnum.NORMAL.getCode())) {
            throw new LockedAccountException("账号被封禁");
        }

        //3.封装authenticationInfo，准备验证密码
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, // 账号
                user.getUserPass(), // 密码
                ByteSource.Util.bytes(CommonConstant.PASSWORD_SALT), // 盐
                getName() // realm name
        );
        System.out.println("realName:" + getName());
        return authenticationInfo;
    }


    /**
     * 执行一些授权逻辑
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();

        Role role = roleService.findByUserId(user.getId());

        authorizationInfo.addRole(role.getRole());
        List<Permission> permissions = permissionService.listPermissionsByRoleId(role.getId());
        //把权限的URL全部放到authorizationInfo中去
        Set<String> urls = permissions.stream().map(p -> p.getUrl()).collect(Collectors.toSet());
        authorizationInfo.addStringPermissions(urls);

        return authorizationInfo;
    }
}