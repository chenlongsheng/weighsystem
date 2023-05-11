package com.jeeplus.modules.web;


import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
public class MyController {
    @Autowired
    private ResourceLoader resourceLoader;

  // private final ResourceLoader resourceLoader;

    public MyController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        // 获取与应用程序JAR文件相同的路径
        String jarPath = resourceLoader.getResource("classpath:").getFile().getParentFile().toString();

        // 创建static目录
        String staticPath = jarPath + "/static";
        File staticDir = new File(staticPath);
        staticDir.mkdir();

        // 读取静态文件
        InputStream inputStream = getClass().getResourceAsStream("/static/image.png");
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);

        // 写入静态文件
//        FileOutputStream outputStream = new FileOutputStream(staticPath + "/myfile.txt");
//        outputStream.write(bytes);
//        outputStream.close();

        // 返回静态文件
        response.setContentType("text/plain");
        InputStream in = new FileInputStream(staticPath + "/image.png");
        FileCopyUtils.copy(in, response.getOutputStream());
    }
}