package com.example.carbonlife.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 华为云OBS服务接口
 */
public interface IHweiYunOBSService {

    /**
     * @Description 删除文件
     * @param: objectKey 文件名
     * @return: boolean 执行结果
     */
//    boolean delete(String objectKey);
//    boolean deleteFileByPath(String objectKey);

    /**
     * @Description 批量删除文件
     * @param: objectKeys 文件名集合
     * @return: boolean 执行结果
     */
//    boolean delete(List<String> objectKeys);

    /**
     * @Description 上传文件
     * @param: uploadFile 上传文件
     * @param: objectKey 文件名称
     * @return: java.lang.String url访问路径
     */
    String fileUpload(MultipartFile uploadFile, String objectKey);

//    String uploadFileByte(byte data[], String objectKey);
//    String uploadFile(File file);
//
//    /**
//     * @Description 文件下载
//     * @param: objectKey
//     * @return: java.io.InputStream
//     */
//    InputStream fileDownload(String objectKey);
}

