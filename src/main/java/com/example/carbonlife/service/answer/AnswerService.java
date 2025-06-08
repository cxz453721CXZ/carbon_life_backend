package com.example.carbonlife.service.answer;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.Dto.AnswerInfo;
import com.example.carbonlife.entity.answer.Answer;
import org.springframework.stereotype.Service;

public interface AnswerService extends IService<Answer> {


    /**
     * 用户提交答题记录
     * @param answer
     */
    boolean saveAnswerRecord(Answer answer);

    /**
     * 用户当天答对题数, 答题总数, 累计天数
     * @param userId
     * @param answerDate
     */
    int [] getUserAnswerInfo(Integer userId, String answerDate);

    /**
     * 用户当天各个类型答题总数和累计答题
     * @param userId
     * @param answerDate
     */
    AnswerInfo getUserTypeAnswerInfo(Integer userId, String answerDate);

    /**
     * 用户当天答题次数
     * @param userId
     * @param answerDate
     */
    int getUserAnswerTimesDay(Integer userId, String answerDate, Integer typeId);


    /**
     * 查询用户今日各个类型答题次数
     * @param userId
     * @param answerDate
     */
    int [] getUserTodayTypeAnswerCnt(Integer userId, String answerDate);


    /**
     * 查询用户当天总共获得多少积分
     * @param userId
     * @param answerDate
     */
    int getUserTodayAllPoints(Integer userId, String answerDate);
}
