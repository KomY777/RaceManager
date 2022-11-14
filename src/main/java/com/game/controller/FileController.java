package com.game.controller;

import com.game.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/file")
@CrossOrigin
@Api("文件得上传与下载")
public class FileController {

    @Value("${gameFile.path}")
    private String rootPath;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "上传文件")
    public Result<String> upload(MultipartFile file) {
        log.info("请求成功");

        String fileName = file.getOriginalFilename();
        if (fileName == null)
            return Result.error("文件格式有误");
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + suffix;

//        判断存储目录是否存在
        File pathFile = new File(rootPath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }

        try {
            file.transferTo(new File(rootPath + newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("上传文件：{}", file);
        return Result.success(newFileName);
    }

    /**
     * 文件下载
     * @param fileName 文件名
     */
    @ApiOperation(value = "下载文件")
    @ApiImplicitParam(name = "fileName", value = "文件名", required = true)
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(String fileName, HttpServletResponse response) {
        try {
            FileInputStream fileInputStream = new FileInputStream(rootPath + fileName);
            ServletOutputStream outputStream = response.getOutputStream();

//            返回word文档
            response.setContentType("application/msword; charset=UTF-8");

            int len;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
