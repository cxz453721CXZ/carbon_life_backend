package com.example.carbonlife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.GreenChallenge;

import java.util.List;

/**
 * 绿色挑战
 * @author 陈新知
 */
public interface GreenChallengeService extends IService<GreenChallenge> {
    /**
     * 用户提交骑行记录
     * @param greenChallenge
     * @return
     */
    boolean insertBikeInfo(GreenChallenge greenChallenge);

    /**
     * 用户查询提交记录
     * @param id
     * @return List
     */
    List<GreenChallenge> selectByTypeId(Integer id);


    /**
     * 查询用户某一活动当天的提交次数
     * @param currentDate 当前日期
     * @param typeId 类别索引
     * @param userId 用户编号
     */
    int selectCntByDate(String currentDate, Integer typeId, Integer userId);

    /**
     * 显示用户每项活动一周的挑战次数
     * @param userId
     */
    int [] selectCntByWeek(Integer userId);

    /**
     * 查询用户每项活动挑战的总次数
     * @param userId
     */
    int [] selectAllCnt(Integer userId);


    /**
     * 查询用户每周六种活动的挑战次数
     * @param userId
     */
    int [] selectAllCntByWeek(Integer userId);
}
