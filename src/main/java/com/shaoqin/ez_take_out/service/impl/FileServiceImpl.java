package com.shaoqin.ez_take_out.service.impl;

import com.shaoqin.ez_take_out.service.FileService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: FileServiceImpl
 * Package: com.shaoqin.ez_take_out.service.impl
 * Description:
 * Author Shaoqin
 * Create 6/28/23 1:22 AM
 * Version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${ez_take_out.path}")
    private String basePath;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = UUID.randomUUID().toString() + fileExtension;

        File dir = new File(basePath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        file.transferTo(new File(basePath + filename));

        return filename;
    }

    @Override
    public void downloadFile(String name, HttpServletResponse response) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("image/jpeg");
        byte[] bytes = new byte[1024];
        int len = 0;
        while ( (len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }

        outputStream.close();
        fileInputStream.close();
    }

}
