package com.example.carbonlife.controller;


import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.example.carbonlife.common.Constants;
import com.example.carbonlife.common.Result;
import com.example.carbonlife.config.WxOpenConfig;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.exception.ServiceException;
import com.example.carbonlife.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import okhttp3.internal.http2.ErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @Resource
    private WxOpenConfig wxOpenConfig;

    /**
     * 用户登录（微信小程序）
     */
    @GetMapping("/login/wx_open")
    public Result userLoginByWxOpen(HttpServletRequest request, @RequestParam("code") String code) {
        WxMaJscode2SessionResult sessionInfo;
        try {
            WxMaService wxMaService = wxOpenConfig.getWxMaService();
            sessionInfo = wxMaService.jsCode2SessionInfo(code);
            String maOpenid = sessionInfo.getOpenid();
            if (StringUtils.isAnyBlank(maOpenid)) {
                throw new ServiceException(Constants.CODE_500, "登录失败，系统错误");
            }
            return Result.success(userService.userLoginByMpOpen(sessionInfo, request));
        } catch (Exception e) {
            log.error("userLoginByWxOpen error", e);
            throw new ServiceException(Constants.CODE_500, "登录失败，系统错误");
        }
    }


    @GetMapping("/get/login")
    public Result getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        return Result.success(user);
    }


    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public Result updateUserInfo(@RequestBody User user){
        return Result.success(userService.saveOrUpdate(user));
    }


    /**
     * 根据时间更新每周挑战
     * @param currentTime
     * @param id
     */
    @GetMapping("/updateDate")
    public Result updateDate(@RequestParam String currentTime, @RequestParam Integer id){
        return Result.success(userService.updateDate(currentTime, id));
    }




}
