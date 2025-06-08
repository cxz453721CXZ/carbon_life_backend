package com.example.carbonlife.service.answer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.Dto.TodayPointsUser;
import com.example.carbonlife.entity.answer.TodayPoints;

import java.util.List;

public interface TodayPointsService extends IService<TodayPoints> {


    /**
     * 更新用户日积分
     * @param todayPoints
     */
    boolean registerUserTodayPoints(TodayPoints todayPoints);


    /**
     * 查询所有用户日积分
     * @param todayDate
     */
    List<TodayPointsUser> getAllUsersTodayPoints(String todayDate);

    /**
     * 查询用户积分日榜排名
     * @param userId
     * @param todayDate
     */
    int getUserTodayPointsRank(Integer userId, String todayDate);


    /**
     * 预处理所有积分日榜用户
     * @param todayDate
     */
    boolean dealAllUserTodayPoints(String todayDate);
}
