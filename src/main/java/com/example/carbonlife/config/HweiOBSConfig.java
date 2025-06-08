package com.example.carbonlife.config;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Configuration
public class HweiOBSConfig {


    /**
     * 访问密钥AK
     */
    @Value("${hwyun.obs.accessKey}")
    private String accessKey;

    /**
     * 访问密钥SK
     */
    @Value("${hwyun.obs.securityKey}")
    private String securityKey;

    /**
     * 终端节点
     */
    @Value("${hwyun.obs.endPoint}")
    private String endPoint;

    /**
     * 桶
     */
    @Value("${hwyun.obs.bucketName}")
    private String bucketName;

    /**
     * @Description 获取OBS客户端实例
     * @return: com.obs.services.ObsClient
     */
    public ObsClient getInstance() {
        return new ObsClient(accessKey, securityKey, endPoint);
    }

    /**
     * @Description 销毁OBS客户端实例
     * @param: obsClient
     */
    public void destroy(ObsClient obsClient){
        try {
            obsClient.close();
        } catch (ObsException e) {
        } catch (Exception e) {
        }
    }

    /**
     * @Description 微服务文件存放路径
     * @return: java.lang.String
     */
    public static String getObjectKey() {
        // 项目或者服务名称 + 日期存储方式
        return "Hwei" + "/" + new SimpleDateFormat("yyyy-MM-dd").format(new Date() )+ "/";
    }
}

