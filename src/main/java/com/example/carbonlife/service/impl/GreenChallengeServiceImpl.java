package com.example.carbonlife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.GreenChallenge;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.mapper.GreenChallengeMapper;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.service.GreenChallengeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 绿色挑战业务逻辑
 * @author 陈新知
 */
@Service
public class GreenChallengeServiceImpl extends ServiceImpl<GreenChallengeMapper, GreenChallenge> implements GreenChallengeService {


    /**
     * 绿色挑战增删改查接口
     */
    @Resource
    private GreenChallengeMapper greenChallengeMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 用户提交骑行记录
     * @param greenChallenge
     * @return
     */
    @Override
    public boolean insertBikeInfo(GreenChallenge greenChallenge) {
        System.out.println(greenChallenge);
        this.save(greenChallenge);
        return true;
    }

    /**
     * 用户查询提交记录
     * @param id
     * @return List
     */
    @Override
    public List<GreenChallenge> selectByTypeId(Integer id) {
        return greenChallengeMapper.selectList(new QueryWrapper<GreenChallenge>().eq("type_id", id));
    }

    /**
     * 查询用户某一活动当天的提交次数
     * @param currentDate 当前日期
     * @param typeId 类别索引
     */
    @Override
    public int selectCntByDate(String currentDate, Integer typeId, Integer userId) {
        return greenChallengeMapper.selectList(new QueryWrapper<GreenChallenge>().
                eq("type_id", typeId).
                eq("submit_time", currentDate).
                eq("user_id", userId)).size();
    }


    /**
     * 显示用户每项活动一周的挑战次数
     * @param userId
     */
    @Override
    public int [] selectCntByWeek(Integer userId) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        String loginTime = user.getLoginDate();

        List<GreenChallenge> greenChallenges = greenChallengeMapper.selectList(new QueryWrapper<GreenChallenge>()
                .eq("user_id", userId));
//                .eq("type_id", typeId));

        int [] arr = new int[8];
        Date loginDate = null;
        Date loginAfterDate = null;
        try {
            loginDate = plusDay(sdf.parse(loginTime), -1);
            loginAfterDate = plusDay(loginDate, 8);
//            System.out.println(loginDate);
//            System.out.println(loginAfterDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 8; i ++ ){
            int cnt = 0;
            for (int j = 0; j < greenChallenges.size(); j ++ ) {
                GreenChallenge greenChallenge = greenChallenges.get(j);
                String submitTime = greenChallenge.getSubmitTime();
                Integer typeId = greenChallenge.getTypeId();
                try {
                    Date submitDate = sdf.parse(submitTime);
//               System.out.println(submitDate);
                    if (submitDate.before(loginAfterDate) && submitDate.after(loginDate) && typeId == i) cnt++;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            arr[i - 1] = cnt;
        }
        return arr;

    }



    /**
     * 给当前日期加上指定天数
     */

    private Date plusDay(Date loginDate, Integer num){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(loginDate);
        calendar.add(Calendar.DATE, num);
        Date loginAfterDate = calendar.getTime();
        return loginAfterDate;
    }



    /**
     * 查询用户每项活动挑战的总次数
     * @param userId
     */
    @Override
    public int [] selectAllCnt(Integer userId) {
        List<GreenChallenge> list = greenChallengeMapper.selectList(new QueryWrapper<GreenChallenge>().eq("user_id", userId));
        int [] arr = new int[8];
        int [] newArr = new int [6];
        for (int i = 1; i <= 8; i ++ ){
            int cnt = 0;
            for (int j = 0; j < list.size(); j ++ ){
                GreenChallenge greenChallenge = list.get(j);
                Integer typeId = greenChallenge.getTypeId();
                if(typeId == i) cnt ++;
            }
            arr[i - 1] = cnt;
        }
        newArr[0] = arr[0] + arr[1];
        newArr[1] = arr[2] + arr[3];
        for (int i = 2; i < 6; i ++ ){
            newArr[i] = arr[i + 2];
        }
        return newArr;
    }


    @Override
    public int [] selectAllCntByWeek(Integer userId) {
        int [] arr = this.selectCntByWeek(userId);
        int [] newArr = new int[6];
        newArr[0] = arr[0] + arr[1];
        newArr[1] = arr[2] + arr[3];
        for (int i = 2; i < 6; i ++ ){
            newArr[i] = arr[i + 2];
        }
        return newArr;
    }
}
