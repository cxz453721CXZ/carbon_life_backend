package com.example.carbonlife.controller;

import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.example.carbonlife.common.Result;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.service.UserService;
import com.example.carbonlife.service.impl.AliAuthServiceImp;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
@RequestMapping("/test")
public class AliAuthController {
    @Resource
    private AliAuthServiceImp aliAuthServiceImp;

    @Resource
    private UserService userService;


    @GetMapping("/auth")
    public AlipayUserInfoShareResponse auth(@RequestParam String authCode, HttpServletRequest request) {
        AlipayUserInfoShareResponse auth = aliAuthServiceImp.auth(authCode, request);
        return auth;
    }


    /**
     * 根据openId查询用户信息
     * @param openId
     */
    @GetMapping("/selectUserByOpenId")
    public Result selectUserByOpenId(@RequestParam String openId){
        return Result.success(userService.getUserInfo(openId));
    }

    /**
     * 根据Session获取用户信息
     */
    @GetMapping("/queryUserInfo")
    public Result queryUserInfo(@RequestParam String openId){
        User user = aliAuthServiceImp.getUserInfoBySession(openId);
        return Result.success(user);
    }

}
