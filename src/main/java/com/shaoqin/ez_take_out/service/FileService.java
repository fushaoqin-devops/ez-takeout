package com.shaoqin.ez_take_out.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ClassName: FileService
 * Package: com.shaoqin.ez_take_out.service
 * Description:
 * Author Shaoqin
 * Create 6/28/23 1:20 AM
 * Version 1.0
 */
public interface FileService {

    public String uploadFile(MultipartFile file) throws IOException;

    public void downloadFile(String name, HttpServletResponse response) throws IOException;

}
