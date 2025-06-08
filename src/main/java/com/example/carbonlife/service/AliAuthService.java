package com.example.carbonlife.service;

import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.example.carbonlife.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface AliAuthService {

    /**
     * 获取支付宝authcode，解析成用户对象
     * @param authcode 前端传来的校验码
     * @return 用户对象
     */
    AlipayUserInfoShareResponse auth(String authcode, HttpServletRequest request);


    /**
     * 获取用户登录信息
     */
    User getUserInfoBySession(String openId, HttpServletRequest request);
}
