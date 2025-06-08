package com.example.carbonlife.controller;

import com.example.carbonlife.common.Result;
import com.example.carbonlife.entity.Questionnaire;
import com.example.carbonlife.entity.Sign;
import com.example.carbonlife.entity.answer.Answer;
import com.example.carbonlife.entity.answer.TodayPoints;
import com.example.carbonlife.service.SignService;
import com.example.carbonlife.service.SubmitQuestionService;
import com.example.carbonlife.service.UserService;
import com.example.carbonlife.service.answer.AnswerService;
import com.example.carbonlife.service.answer.TodayPointsService;
import jakarta.annotation.Resource;
import okhttp3.Request;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeChallengeController {


    @Resource
    private SubmitQuestionService submitQuestionService;


    @Resource
    private SignService signService;


    @Resource
    private UserService userService;



    @Resource
    private AnswerService answerService;


    @Resource
    private TodayPointsService todayPointsService;

    /**
     * 提交调查问卷
     * @param questionnaire
     */
    @PostMapping("/submitQuestion")
    public Result submitQuestion(@RequestBody Questionnaire questionnaire){
        return Result.success(submitQuestionService.dealQuestionForm(questionnaire));
    }

    /**
     * 查询用户是否提交过调查问卷
     * @param typeId
     * @param userId
     */
    @GetMapping("/selectIsSubmitQuestion")
    public Result selectIsSubmitQuestion(@RequestParam Integer typeId, @RequestParam Integer userId){
        return Result.success(submitQuestionService.selectIsSubmitQuestion(typeId, userId));
    }

    /**
     * 用户签到
     * @param sign
     */
    @PostMapping("/daySign")
    public Result daySign(@RequestBody Sign sign){
        return Result.success(signService.userDaySign(sign));
    }

    /**
     * 查询用户当天是否签到
     * @param sign
     */
    @PostMapping("/selectIsSign")
    public Result selectIsSign(@RequestBody Sign sign){
        return Result.success(signService.selectIsSign(sign));
    }

    /**
     * 查询用户六天的签到记录
     * @param userId
     */
    @GetMapping("/selectSixDaySign")
    public Result selectSixDaySign(@RequestParam Integer userId){
        return Result.success(signService.saveSignRecord(userId));
    }

    /**
     * 用户签到天数
     * @param userId
     */
    @GetMapping("/selectSignDayCnt")
    public Result selectSignDayCnt(@RequestParam Integer userId){
        return Result.success(signService.selectSignDayCnt(userId));
    }


    /**
     * 用户连续签到天数
     * @param userId
     */
    @GetMapping("/selectConSignDayCnt")
    public Result selectConSignDayCnt(@RequestParam Integer userId){
        return Result.success(signService.selectConSignDayCnt(userId));
    }

    /**
     * 查询用户信息
     * @param userId
     */
    @GetMapping("/selectUser")
    public Result selectUserInfo(@RequestParam Integer userId){
        return Result.success(userService.selectUserInfo(userId));
    }

    /**
     * 查询所有用户积分
     */
    @GetMapping("/selectAllUsers")
    public Result selectAllUsers(){
        return Result.success(userService.selectAllUsers());
    }

    /**
     * 查询用户积分排名
     * @param userId
     */
    @GetMapping("/selectUserPointsRank")
    public Result selectUserPointsRank(@RequestParam Integer userId){
        return Result.success(userService.getUserRank(userId));
    }

    /**
     * 用户提交答题记录
     * @param answer
     */
    @PostMapping("/submitAnswerRecord")
    public Result submitAnswerRecord(@RequestBody Answer answer){
        return Result.success(answerService.saveAnswerRecord(answer));
    }

    /**
     * 用户当天答对题数, 答题总数, 累计天数
     * @param userId
     * @param answerDate
     */
    @GetMapping("/answerCorrectCnt")
    public Result getUserAnswerInfo(@RequestParam Integer userId, @RequestParam String answerDate){
        return Result.success(answerService.getUserAnswerInfo(userId, answerDate));
    }


    /**
     * 用户当天各个类型答题总数和累计答题
     * @param userId
     * @param answerDate
     */
    @GetMapping("/answerTypeCnt")
    public Result getUserTypeAnswerInfo(@RequestParam Integer userId, @RequestParam String answerDate){
        return Result.success(answerService.getUserTypeAnswerInfo(userId, answerDate));
    }

    /**
     * 用户当天答题次数
     * @param userId
     * @param answerDate
     */
    @GetMapping("/answerDayTimes")
    public Result getUserAnswerTimesDay(@RequestParam Integer userId, @RequestParam String answerDate, @RequestParam Integer typeId){
        return Result.success(answerService.getUserAnswerTimesDay(userId, answerDate, typeId));
    }


    /**
     * 查询用户今日各个类型答题次数
     * @param userId
     * @param answerDate
     */
    @GetMapping("/selectTodayTypeAnswerCnt")
    public Result getUserTodayTypeAnswerCnt(@RequestParam Integer userId, @RequestParam String answerDate){
        return Result.success(answerService.getUserTodayTypeAnswerCnt(userId, answerDate));
    }

    /**
     * 查询用户当天总共获得多少积分
     * @param userId
     * @param answerDate
     */
    @GetMapping("/selectUserTodayPoints")
    public Result getUserTodayAllPoints(@RequestParam Integer userId, @RequestParam String answerDate){
        return Result.success(answerService.getUserTodayAllPoints(userId, answerDate));
    }

    /**
     * 更新用户日积分
     * @param todayPoints
     */
    @PostMapping("/registerUserTodayPoints")
    public Result registerUserTodayPoints(@RequestBody TodayPoints todayPoints){
        return Result.success(todayPointsService.registerUserTodayPoints(todayPoints));
    }


    /**
     * 查询所有用户日积分
     * @param todayDate
     */
    @GetMapping("/selectAllUsersTodayPoints")
    public Result getAllUsersTodayPoints(@RequestParam String todayDate){
        return Result.success(todayPointsService.getAllUsersTodayPoints(todayDate));
    }

    /**
     * 查询用户积分日榜排名
     * @param userId
     * @param todayDate
     */
    @GetMapping("/selectUserTodayRank")
    public Result getUserTodayPointsRank(@RequestParam Integer userId, @RequestParam String todayDate){
        return Result.success(todayPointsService.getUserTodayPointsRank(userId, todayDate));
    }

    /**
     * 预处理所有积分日榜用户
     * @param todayDate
     */
    @GetMapping("/initAllUserTodayPoints")
    public Result dealAllUserTodayPoints(@RequestParam String todayDate){
        return Result.success(todayPointsService.dealAllUserTodayPoints(todayDate));
    }
}
