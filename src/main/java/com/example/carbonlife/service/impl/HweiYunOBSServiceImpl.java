package com.example.carbonlife.service.impl;


import com.example.carbonlife.config.HweiOBSConfig;
import com.example.carbonlife.service.IHweiYunOBSService;
import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * 华为云OBS服务业务层
 */

@Service
@Slf4j
public class HweiYunOBSServiceImpl implements IHweiYunOBSService {


    @Autowired
    private HweiOBSConfig hweiOBSConfig;

//    @Override
//    public boolean delete(String objectKey) {
//        ObsClient obsClient = null;
//        try {
//            // 创建ObsClient实例
//            obsClient = hweiOBSConfig.getInstance();
//            // obs删除
//            obsClient.deleteObject(hweiOBSConfig.getBucketName(), objectKey);
//        } catch (ObsException e) {
//        } finally {
//            hweiOBSConfig.destroy(obsClient);
//        }
//        return true;
//    }

//    @Override
//    public boolean deleteFileByPath(String path) {
//        //path：https://wwwbak.obs.ap-southeast-1.myhuaweicloud.com/uploadFiles/xiehui/yongjiu/fujian/wode.jpg
//        ObsClient obsClient = null;
//        try {
//            // 创建ObsClient实例
//            obsClient = hweiOBSConfig.getInstance();
//
//            String file_http_url = PropertiesUtil.readValue("file_http_url");
//
//            File file = new File(path);
//            String key = file.getName();//wode.jpg
//
//            //realPath : uploadFiles/xiehui/yongjiu/fujian
//            String realPath = path.replace(file_http_url, "");
//            realPath = realPath.substring(0, realPath.lastIndexOf('/'));
//
//            // obs删除
//            obsClient.deleteObject(hweiOBSConfig.getBucketName(), realPath + "/" + key);
//        } catch (ObsException e) {
//        } finally {
//            hweiOBSConfig.destroy(obsClient);
//        }
//        return false;
//    }

//    @Override
//    public boolean delete(List<String> objectKeys) {
//        ObsClient obsClient = null;
//        try {
//            obsClient = hweiOBSConfig.getInstance();
//            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(hweiOBSConfig.getBucketName());
//            objectKeys.forEach(x -> deleteObjectsRequest.addKeyAndVersion(x));
//            // 批量删除请求
//            obsClient.deleteObjects(deleteObjectsRequest);
//            return true;
//        } catch (ObsException e) {
//        } finally {
//            hweiOBSConfig.destroy(obsClient);
//        }
//        return false;
//    }

    @Override
    public String fileUpload(MultipartFile uploadFile, String objectKey) {
        ObsClient obsClient = null;
        try {
            String bucketName = hweiOBSConfig.getBucketName();
            obsClient = hweiOBSConfig.getInstance();
            // 判断桶是否存在
            boolean exists = obsClient.headBucket(bucketName);
            if (!exists) {
                // 若不存在，则创建桶
                HeaderResponse response = obsClient.createBucket(bucketName);
            }
            InputStream inputStream = uploadFile.getInputStream();
            long available = inputStream.available();
            PutObjectRequest request = new PutObjectRequest(bucketName, objectKey, inputStream);

            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(available);
            request.setMetadata(objectMetadata);
            // 设置对象访问权限为公共读
            request.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
            PutObjectResult result = obsClient.putObject(request);
            // 读取该已上传对象的URL
            return result.getObjectUrl();
        } catch (ObsException e) {
        } catch (IOException e) {
        } finally {
            hweiOBSConfig.destroy(obsClient);
        }
        return null;
    }

//    @Override
//    public String uploadFileByte(byte[] data, String fileName) {
//        try {
//            ObsClient obsClient = null;
//            String bucketName = hweiOBSConfig.getBucketName();
//            obsClient = hweiOBSConfig.getInstance();
//            // 判断桶是否存在
//            boolean exists = obsClient.headBucket(bucketName);
//            if (!exists) {
//                // 若不存在，则创建桶
//                HeaderResponse response = obsClient.createBucket(bucketName);
//            }
//
//            String file_http_url = PropertiesUtil.readValue("file_http_url");
//
//            //转为File
//            InputStream inputStream = new ByteArrayInputStream(data);
//            String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);//后缀
//            String uuid = UUID.randomUUID().toString().replace("-", "");//customer_YYYYMMDDHHMM
//            fileName = uuid + "." + prefix; //文件全路径
//            obsClient.putObject(bucketName, fileName.substring(0, fileName.lastIndexOf("/")) + "/" + fileName, inputStream);
//            return file_http_url + fileName;
//        } catch (ObsException e) {
//        }
//        return null;
//    }

//    @Override
//    public String uploadFile(File file) {
//        ObsClient obsClient = null;
//        try {
//            String bucketName = hweiOBSConfig.getBucketName();
//            obsClient = hweiOBSConfig.getInstance();
//            // 判断桶是否存在
//            boolean exists = obsClient.headBucket(bucketName);
//            if (!exists) {
//                // 若不存在，则创建桶
//                HeaderResponse response = obsClient.createBucket(bucketName);
//            }
//
//            String file_http_url = PropertiesUtil.readValue("file_http_url");
//
//            //转为File
//            String filename = file.getName();
//            String prefix = filename.substring(filename.lastIndexOf(".") + 1);//后缀
//            String uuid = UUID.randomUUID().toString().replace("-", "");//customer_YYYYMMDDHHMM
//            String fileName = uuid + "." + prefix; //文件全路径
//            obsClient.putObject(bucketName, fileName.substring(0, fileName.lastIndexOf("/")) + "/" + fileName, file);
//            return file_http_url + fileName;
//        } catch (Exception e) {
//        } finally {
//            hweiOBSConfig.destroy(obsClient);
//        }
//        return null;
//    }

//    @Override
//    public InputStream fileDownload(String objectKey) {
//        ObsClient obsClient = null;
//        try {
//            String bucketName = hweiOBSConfig.getBucketName();
//            obsClient = hweiOBSConfig.getInstance();
//            ObsObject obsObject = obsClient.getObject(bucketName, objectKey);
//            return obsObject.getObjectContent();
//        } catch (ObsException e) {
//        } finally {
//            hweiOBSConfig.destroy(obsClient);
//        }
//        return null;
//    }
}

