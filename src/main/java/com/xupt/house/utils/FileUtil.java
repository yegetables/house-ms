package com.xupt.house.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrBuilder;
import com.alibaba.fastjson.JSONException;
import com.xupt.house.exception.MyBusinessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 文件上传工具类
 */
public class FileUtil {
    /**
     * 上传文件返回URL
     * @param file
     * @return
     */
    public static String upload(MultipartFile file) {
        String path = "";
        try {
            //用户目录
            final StrBuilder uploadPath = new StrBuilder(System.getProperties().getProperty("user.home"));
            uploadPath.append("/sens/upload/" + cn.hutool.core.date.DateUtil.thisYear()).append("/").append(cn.hutool.core.date.DateUtil.thisMonth() + 1).append("/");
            final File mediaPath = new File(uploadPath.toString());
            if (!mediaPath.exists()) {
                if (!mediaPath.mkdirs()) {
                    throw new MyBusinessException("上传失败");
                }
            }

            //不带后缀
            String nameWithOutSuffix = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf('.')).replaceAll(" ", "_").replaceAll(",", "");

            //文件后缀
            final String fileSuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);

            //带后缀
            String fileName = nameWithOutSuffix + "." + fileSuffix;

            //判断文件名是否已存在
            File descFile = new File(mediaPath.getAbsoluteFile(), fileName);
            int i = 1;
            while (descFile.exists()) {
                String newNameWithOutSuffix = nameWithOutSuffix + "(" + i + ")";
                descFile = new File(mediaPath.getAbsoluteFile(), newNameWithOutSuffix + "." + fileSuffix);
                i++;
            }
            file.transferTo(descFile);

            //文件原路径
            final StrBuilder fullPath = new StrBuilder(mediaPath.getAbsolutePath());
            fullPath.append("/");
            fullPath.append(nameWithOutSuffix + "." + fileSuffix);
            System.out.println("文件原路径"+fullPath.toString());

            //压缩文件路径
            final StrBuilder fullSmallPath = new StrBuilder(mediaPath.getAbsolutePath());
            fullSmallPath.append("/");
            fullSmallPath.append(nameWithOutSuffix);
            fullSmallPath.append("_small.");
            fullSmallPath.append(fileSuffix);
            System.out.println("压缩文件路径"+fullSmallPath.toString());

            //映射路径
            final StrBuilder filePath = new StrBuilder("/upload/");
            filePath.append(cn.hutool.core.date.DateUtil.thisYear());
            filePath.append("/");
            filePath.append(DateUtil.thisMonth() + 1);
            filePath.append("/");
            filePath.append(nameWithOutSuffix + "." + fileSuffix);
            path = filePath.toString().replace("\\", "/");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String readStringFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return jsonText;
        } finally {
            is.close();
        }
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
}
