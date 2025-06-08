package com.example.carbonlife.controller;

import com.example.carbonlife.exception.ServiceException;
import com.example.carbonlife.service.IHweiYunOBSService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import static com.example.carbonlife.common.Constants.CODE_500;

/**
 * @author YT
 */
@RestController
@Tag(name = "文件上传接口")
@RequestMapping("/file")
public class FileController {
    @Resource
    private IHweiYunOBSService iHweiYunOBSService;

    @PostMapping("/upload")
    public String uploadFile(@RequestPart("file") MultipartFile multipartFile, HttpServletRequest request) {
        System.out.println(11111);
        if (multipartFile==null){
            throw new ServiceException(CODE_500,"文件不能为空");
        }
        String uuid = RandomStringUtils.randomAlphabetic(8);
        String filename = uuid + "-" + multipartFile.getOriginalFilename();
        final String test = iHweiYunOBSService.fileUpload(multipartFile, "img/" + filename);
        return test;
    }

}
