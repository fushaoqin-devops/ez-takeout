package com.shaoqin.ez_take_out.controller;

import com.shaoqin.ez_take_out.common.R;
import com.shaoqin.ez_take_out.service.FileService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: CommonController
 * Package: com.shaoqin.ez_take_out.controller
 * Description:
 * Author Shaoqin
 * Create 6/27/23 1:55 PM
 * Version 1.0
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) throws IOException {
        String filename = fileService.uploadFile(file);
        return R.success(filename);
    }

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) throws IOException {
        fileService.downloadFile(name, response);
    }

}
