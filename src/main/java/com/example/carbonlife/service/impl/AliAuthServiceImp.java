package com.example.carbonlife.service.impl;

import cn.hutool.core.date.DateUtil;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.carbonlife.common.Constants;
import com.example.carbonlife.common.Result;
import com.example.carbonlife.config.AlipayConfig;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.exception.ServiceException;
import com.example.carbonlife.service.AliAuthService;
import com.example.carbonlife.service.UserService;
import com.example.carbonlife.service.answer.TodayPointsService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.yaml.snakeyaml.scanner.Constant;

import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 支付宝登录
 * @author 袁腾
 */
@Service
@Slf4j
public class AliAuthServiceImp implements AliAuthService {

    @Resource
    private AlipayConfig alipayConfig;

    @Resource
    private UserService userService;

    private HttpSession httpSession;

    @Override
    @SneakyThrows
    public AlipayUserInfoShareResponse auth(String authcode, HttpServletRequest requests) {
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGetway(),
                alipayConfig.getAppId(),
                alipayConfig.getPrivateKey(),
                "JSON","utf-8",alipayConfig.getPublicKey(),"RSA2");
        //获取UID和token
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        //值为authorization_code时，代表用code换取；值为refresh_token时，代表用refresh_token换取
        request.setRefreshToken("refresh_token");
        request.setGrantType("authorization_code");
        request.setCode(authcode);
        //获取accesss_token
        AlipaySystemOauthTokenResponse response = alipayClient.execute(request);
        System.out.println(response.getBody());
        //根据token获取open_id
        AlipayUserInfoShareRequest arequest = new AlipayUserInfoShareRequest();
        //返回用户对象（包含opneid，头像，昵称）
        AlipayUserInfoShareResponse UserInfo = alipayClient.execute(arequest,  response.getAccessToken());
        saveUser(UserInfo, requests);
        System.out.println(UserInfo.getOpenId() + "------");
        System.out.println(UserInfo);
        return UserInfo;
    }

    @Override
    public User getUserInfoBySession(String openId) {
        System.out.println("openId:" + openId);
        User user = (User) httpSession.getAttribute(openId);
        if(user == null) throw new ServiceException(Constants.CODE_500, "系统异常");
        return user;
    }

    /**
     * 存储用户信息
     * @param userInfo 支付宝用户对象
     */
    private void saveUser(AlipayUserInfoShareResponse userInfo, HttpServletRequest requests) {
        //查询用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("open_id",userInfo.getOpenId());
        User user = userService.getOne(userQueryWrapper);
        if(user == null) {
            user = new User();
            user.setName(userInfo.getNickName());
            user.setOpenId(userInfo.getOpenId());
            user.setAvatar(userInfo.getAvatar());
            user.setPoints(0);
            user.setLoginDate(getLoginDate());
            userService.save(user);
        }
        HttpSession session = requests.getSession();
        session.setAttribute(userInfo.getOpenId(), user);
        this.httpSession = session;
    }

    /**
     * 获取用户登录时间
     */
    private String getLoginDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String loginTime = sdf.format(date);
        return loginTime;
    }

}
