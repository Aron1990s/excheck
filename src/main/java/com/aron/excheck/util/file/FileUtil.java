package com.aron.excheck.util.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName FileUtil
 * @Description File Operation Class
 * @Author aron
 * @Date 2019/5/9 15:33
 **/
public class FileUtil {
    protected final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /*
     * 文件上传
     */
    public static String fileUpload(MultipartFile file, String filePath, String fileName){
        String extName = ""; // 扩展名格式：
        try {
            if (file.getOriginalFilename().lastIndexOf(".") >= 0){
                extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).split("_")[0];
            }
            copyFile(file.getInputStream(), filePath, fileName+extName).replaceAll("-", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName+extName;
    }

    /*
     * 存储文件功能
     */
    private static String copyFile(InputStream in, String dir, String realName)
            throws IOException {
        File file = new File(dir, realName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        FileUtils.copyInputStreamToFile(in, file);
        return realName;
    }
}
