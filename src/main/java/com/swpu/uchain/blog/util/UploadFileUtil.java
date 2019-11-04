package com.swpu.uchain.blog.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author hobo
 * @description
 */
@Slf4j
public class UploadFileUtil {

    public static String uploadFile(String fileUrl, MultipartFile file) {
        File dest = new File(fileUrl);
        if (dest.exists()) {
            dest.delete();
        }
        if (!dest.getParentFile().exists()) {
            File parentDir = dest.getParentFile();
            parentDir.mkdir();
        }
        try {
            file.transferTo(dest);
            log.info("文件上传成功，文件路径:{}", fileUrl);
            return fileUrl;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传失败:", e);
            return "";
        }
    }

}
