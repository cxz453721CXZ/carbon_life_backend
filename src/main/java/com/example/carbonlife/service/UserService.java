package com.example.carbonlife.service;


import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService extends IService<User> {

    /**
     * 根据openId查询用户信息
     * @param openId
     * @return User
     */
    User getUserInfo(String openId);

    /**
     * 根据时间更新每周挑战
     * @param currentTime
     * @param id
     */
    boolean updateDate(String currentTime, Integer id);

    /**
     * 查询用户积分
     * @param userId
     */
    User selectUserInfo(Integer userId);

    /**
     * 查询所有用户积分
     */
    List<User> selectAllUsers();

    /**
     * 查询用户积分排名
     * @param userId
     */
    int getUserRank(Integer userId);


    /**
     * 查询用户积分
     * @param userId
     */
    int queryUserPoints(Integer userId);


    /**
     * 微信小程序登录
     */
    User userLoginByMpOpen(WxMaJscode2SessionResult sessionInfo, HttpServletRequest request);


    /**
     * 获取当前登录用户
     */
    User getLoginUser(HttpServletRequest request);
}
