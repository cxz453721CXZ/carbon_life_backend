package com.example.carbonlife.service.impl.answerImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.Dto.AnswerInfo;
import com.example.carbonlife.entity.Questionnaire;
import com.example.carbonlife.entity.Sign;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.entity.answer.Answer;
import com.example.carbonlife.mapper.SignMapper;
import com.example.carbonlife.mapper.SubmitQuestionMapper;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.mapper.answer.AnswerMapper;
import com.example.carbonlife.mapper.answer.TodayPointsMapper;
import com.example.carbonlife.service.SubmitQuestionService;
import com.example.carbonlife.service.answer.AnswerService;
import com.example.carbonlife.service.answer.TodayPointsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {

    @Resource
    private AnswerMapper answerMapper;


    @Resource
    private UserMapper userMapper;

    @Resource
    private SignMapper signMapper;


    @Resource
    private SubmitQuestionMapper submitQuestionMapper;



    /**
     * 用户提交答题记录
     * @param answer
     */
    @Override
    public boolean saveAnswerRecord(Answer answer) {
        this.save(answer);
        Integer correctCnt = answer.getCorrectCnt();
        Integer userId = answer.getUserId();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        user.setPoints(user.getPoints() + correctCnt * 2);
        userMapper.updateById(user);
        return true;
    }


    /**
     * 用户当天答对题数, 答题总数, 累计天数
     * @param userId
     * @param answerDate
     */
    @Override
    public int [] getUserAnswerInfo(Integer userId, String answerDate) {
        List<Answer> answers = answerMapper.selectList(new QueryWrapper<Answer>()
                .eq("user_id", userId)
                .eq("answer_date", answerDate));
        int cnt = 0;
        int [] arr = new int[3];
        for (int i = 0; i < answers.size(); i ++ ){
            cnt += answers.get(i).getCorrectCnt();
        }
        arr[0] = cnt;


        List<Answer> answerList = answerMapper.selectList(new QueryWrapper<Answer>()
                .eq("user_id", userId));

        arr[1] = answerList.size() * 5;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < answerList.size(); i ++ ){
            if(!list.contains(answerList.get(i).getAnswerDate())){
                list.add(answerList.get(i).getAnswerDate());
            }
        }
        arr[2] = list.size();
        return arr;
    }

    /**
     * 用户当天各个类型答题总数和累计答题
     * @param userId
     * @param answerDate
     */
    @Override
    public AnswerInfo getUserTypeAnswerInfo(Integer userId, String answerDate) {
        List<Answer> answers1 = answerMapper.selectList(new QueryWrapper<Answer>()
                .eq("user_id", userId)
                .eq("answer_date", answerDate));

        List<Answer> answers2 = answerMapper.selectList(new QueryWrapper<Answer>()
                .eq("user_id", userId));

        int [] arr1 = getAnswerInfo(answers1);
        int [] arr2 = getAnswerInfo(answers2);
        AnswerInfo answerInfo = new AnswerInfo(arr1, arr2);

        return answerInfo;
    }


    private int [] getAnswerInfo(List<Answer> answers){
        int [] arr = new int[6];
        for (int i = 0; i < answers.size(); i ++ ){
            if(answers.get(i).getTypeId() == 1) arr[0] += 5;
            if(answers.get(i).getTypeId() == 2) arr[1] += 5;
            if(answers.get(i).getTypeId() == 3) arr[2] += 5;
            if(answers.get(i).getTypeId() == 4) arr[3] += 5;
            if(answers.get(i).getTypeId() == 5) arr[4] += 5;
            if(answers.get(i).getTypeId() == 6) arr[5] += 5;
        }
        return arr;
    }


    /**
     * 用户当天答题次数
     * @param userId
     * @param answerDate
     */
    @Override
    public int getUserAnswerTimesDay(Integer userId, String answerDate, Integer typeId) {
        List<Answer> answers = answerMapper.selectList(new QueryWrapper<Answer>()
                .eq("user_id", userId)
                .eq("answer_date", answerDate)
                .eq("type_id", typeId));
        return answers.size();
    }

    /**
     * 查询用户今日各个类型答题次数
     * @param userId
     * @param answerDate
     */
    @Override
    public int [] getUserTodayTypeAnswerCnt(Integer userId, String answerDate) {
        List<Answer> answers = answerMapper.selectList(new QueryWrapper<Answer>()
                .eq("user_id", userId)
                .eq("answer_date", answerDate));
        int [] arr = new int[6];
        for (int i = 0; i < answers.size(); i ++ ){
            Answer answer = answers.get(i);
            if(answer.getTypeId() == 1) arr[0] ++;
            if(answer.getTypeId() == 2) arr[1] ++;
            if(answer.getTypeId() == 3) arr[2] ++;
            if(answer.getTypeId() == 4) arr[3] ++;
            if(answer.getTypeId() == 5) arr[4] ++;
            if(answer.getTypeId() == 6) arr[5] ++;
        }
        return arr;
    }

    /**
     * 查询用户当天总共获得多少积分
     * @param userId
     * @param answerDate
     */
    @Override
    public int getUserTodayAllPoints(Integer userId, String answerDate) {
        int cnt = 0;
        Sign sign = signMapper.selectOne(new QueryWrapper<Sign>()
                .eq("user_id", userId)
                .eq("sign_date", answerDate));
        if(sign != null) cnt += 1;

        List<Questionnaire> questionnaires = submitQuestionMapper.selectList(new QueryWrapper<Questionnaire>()
                .eq("user_id", userId)
                .eq("submit_date", answerDate));
        if(questionnaires != null) cnt += questionnaires.size() * 5;

        List<Answer> answers = answerMapper.selectList(new QueryWrapper<Answer>()
                .eq("user_id", userId)
                .eq("answer_date", answerDate));

        for (int i = 0; i < answers.size(); i ++ ){
            Answer answer = answers.get(i);
            Integer correctCnt = answer.getCorrectCnt();
            cnt += correctCnt * 2;
        }
        return cnt;
    }
}
