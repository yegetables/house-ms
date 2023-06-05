package com.xupt.house.controller.admin;

import com.xupt.house.controller.common.BaseController;
import com.xupt.house.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台附件控制器
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin/file")
public class AttachmentController extends BaseController {


    /**
     * 上传文件
     * @param file file
     * @return Map
     */
    @PostMapping(value = "/upload", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>(1);
        String path = FileUtil.upload(file);
        map.put("link", path);
        return map;
    }


}
