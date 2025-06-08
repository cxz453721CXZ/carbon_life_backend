package com.example.carbonlife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.Questionnaire;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.mapper.SubmitQuestionMapper;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.service.SubmitQuestionService;
import com.example.carbonlife.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SubmitQuestionServiceImpl extends ServiceImpl<SubmitQuestionMapper, Questionnaire> implements SubmitQuestionService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private SubmitQuestionMapper submitQuestionMapper;

    /**
     * 提交调查问卷
     * @param questionnaire
     */
    @Override
    public boolean dealQuestionForm(Questionnaire questionnaire) {
        this.save(questionnaire);
        Integer userId = questionnaire.getUserId();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        user.setPoints(user.getPoints() + 5);
        userService.saveOrUpdate(user);
        return true;
    }


    /**
     * 查询用户是否提交过调查问卷
     * @Param typeId
     * @param userId
     */
    @Override
    public boolean selectIsSubmitQuestion(Integer typeId, Integer userId) {
        return submitQuestionMapper.selectOne(new QueryWrapper<Questionnaire>()
                .eq("user_id", userId)
                .eq("type_id", typeId)) != null;
    }
}
