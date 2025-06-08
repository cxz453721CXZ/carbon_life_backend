package com.example.carbonlife.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "alipay.open")
public class AlipayConfig {

    private String appId;

    private String getway;

    private String publicKey;

    private String privateKey;
}
