package com.example.carbonlife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.Sign;

import java.util.List;

public interface SignService extends IService<Sign> {

    /**
     * 用户签到
     * @param sign
     */
    boolean userDaySign(Sign sign);


    /**
     * 查询用户当天是否签到
     * @param sign
     */
    boolean selectIsSign(Sign sign);

    /**
     * 查询用户六天的签到记录
     * @param userId
     */
    String saveSignRecord(Integer userId);

    /**
     * 用户签到天数
     * @param userId
     */
    int selectSignDayCnt(Integer userId);

    /**
     * 用户连续签到天数
     * @param userId
     */
    int selectConSignDayCnt(Integer userId);



}
