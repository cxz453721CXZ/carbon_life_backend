package com.example.carbonlife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.Questionnaire;

public interface SubmitQuestionService extends IService<Questionnaire> {
    /**
     * 提交调查问卷
     * @param questionnaire
     */
    boolean dealQuestionForm(Questionnaire questionnaire);


    /**
     * 查询用户是否提交过调查问卷
     * @Param typeId
     * @param userId
     */
    boolean selectIsSubmitQuestion(Integer typeId, Integer userId);
}
