package com.example.carbonlife.config;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 微信开放平台配置
 *
 * @author 玄德
 */
@Data
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "wx.mini")
public class WxOpenConfig {

    private String appId;

    private String appSecret;

    private WxMaService wxMaService;

    /**
     * 单例模式
     */
    public WxMaService getWxMaService() {
        if (wxMaService != null) {
            return wxMaService;
        }
        synchronized (this) {
            if (wxMaService != null) {
                return wxMaService;
            }
            WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
            config.setAppid(appId);
            config.setSecret(appSecret);
            WxMaService service = new WxMaServiceImpl();
            service.setWxMaConfig(config);
            wxMaService = service;
            return wxMaService;
        }
    }

}