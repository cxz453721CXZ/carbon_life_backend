package com.example.carbonlife.controller;

import com.example.carbonlife.common.Result;
import com.example.carbonlife.entity.GreenChallenge;
import com.example.carbonlife.entity.GreenOther;
import com.example.carbonlife.service.GreenChallengeService;
import com.example.carbonlife.service.GreenOtherService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenge")
public class GreenChallengeController {

    @Resource
    private GreenChallengeService greenChallengeService;


    @Resource
    private GreenOtherService greenOtherService;


    /**
     * 用户提交骑行记录
     * @param greenChallenge
     * @return Result
     */
    @PostMapping("/insertBike")
    public Result insertBikeInfo (@RequestBody GreenChallenge greenChallenge){
        return Result.success(greenChallengeService.insertBikeInfo(greenChallenge));
    }


    /**
     * 查询用户提交记录
     * @param id
     * @return Result
     */
    @GetMapping("/selectById/{id}")
    public Result selectById (@PathVariable Integer id){
        return Result.success(greenChallengeService.selectByTypeId(id));
    }


    /**
     *  垃圾分类和循环利用提交记录
     * @param greenOther
     * @return Result
     */
    @PostMapping("/insertOtherForm")
    public Result insertOtherForm(@RequestBody GreenOther greenOther){
        return Result.success(greenOtherService.dealOtherPhoto(greenOther));
    }


    /**
     * 查询用户提交的第二张照片
     * @param ids
     * @return Result
     */
    @GetMapping("/selectNext")
    public Result selectNext(@RequestParam List<Integer> ids){
        return Result.success(greenOtherService.selectNext(ids));
    }


    /**
     * 查询用户某一活动当天的提交次数
     * @param currentDate 当前日期
     * @param typeId 类别索引
     */

    @GetMapping("/selectCntByDate")
    public Result selectCntByDate(@RequestParam String currentDate, @RequestParam Integer typeId, @RequestParam Integer userId){
        return Result.success(greenChallengeService.selectCntByDate(currentDate, typeId, userId));
    }


    /**
     * 显示用户每项活动一周的挑战次数
     * @param userId
     */
    @GetMapping("/selectCntEveryWeek")
    public Result selectCntEveryWeek(@RequestParam Integer userId){
        return Result.success(greenChallengeService.selectCntByWeek(userId));
    }


    /**
     * 查询用户每项活动挑战的总次数
     * @param userId
     */
    @GetMapping("/selectAllCnt")
    public Result selectAllCnt(@RequestParam Integer userId){
        return Result.success(greenChallengeService.selectAllCnt(userId));
    }


    /**
     * 查询用户每周六种活动的挑战次数
     * @param
     */
    @GetMapping("/selectAllCntByWeek")
    public Result selectAllCntByWeek(@RequestParam Integer userId){
        return Result.success(greenChallengeService.selectAllCntByWeek(userId));
    }


}

