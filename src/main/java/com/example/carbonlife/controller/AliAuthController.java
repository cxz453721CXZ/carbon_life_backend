package com.example.carbonlife.controller;

import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.example.carbonlife.common.Result;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.service.UserService;
import com.example.carbonlife.service.impl.AliAuthServiceImp;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

import static com.example.carbonlife.common.Constants.SESSION_TIMEOUT;
import static com.example.carbonlife.common.Constants.userLoginState;

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

    @GetMapping("/session")
    public Result testSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        AlipayUserInfoShareResponse userInfo = (AlipayUserInfoShareResponse) session.getAttribute(userLoginState);
        return selectUserByOpenId(userInfo.getOpenId());
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
    public Result queryUserInfo(@RequestParam String openId, HttpServletRequest request){
        User user = aliAuthServiceImp.getUserInfoBySession(openId, request);
        return Result.success(user);
    }

}
