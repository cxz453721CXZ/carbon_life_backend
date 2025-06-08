package com.example.carbonlife.service.impl.answerImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.Dto.TodayPointsUser;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.entity.answer.TodayPoints;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.mapper.answer.TodayPointsMapper;
import com.example.carbonlife.service.answer.TodayPointsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodayPointsServiceImpl extends ServiceImpl<TodayPointsMapper, TodayPoints> implements TodayPointsService {


    @Resource
    private UserMapper userMapper;


    @Resource
    private TodayPointsMapper todayPointsMapper;

    /**
     * 更新用户日积分
     * @param todayPoints
     */
    @Override
    public boolean registerUserTodayPoints(TodayPoints todayPoints) {
        Integer userId = todayPoints.getUserId();
        String todayTime = todayPoints.getTodayTime();
        TodayPoints user = todayPointsMapper.selectOne(new QueryWrapper<TodayPoints>()
                .eq("user_id", userId).eq("today_time", todayTime));
        todayPointsMapper.update(todayPoints, new QueryWrapper<TodayPoints>().eq("user_id", userId));
        return true;
    }

    /**
     * 查询所有用户日积分
     * @param todayDate
     */
    @Override
    public List<TodayPointsUser> getAllUsersTodayPoints(String todayDate) {
        List<TodayPoints> todayPointsList = todayPointsMapper.selectList(new QueryWrapper<TodayPoints>()
                .eq("today_time", todayDate)
                .orderByDesc("today_points"));

        List<TodayPointsUser> todayPointsUserList = new ArrayList<>();
        TodayPointsUser todayPointsUser = null;

        for (int i = 0; i < todayPointsList.size(); i ++ ){
            TodayPoints todayPoints = todayPointsList.get(i);
            Integer totalPoints = todayPoints.getTodayPoints();
            Integer userId = todayPoints.getUserId();
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
            if(user == null) continue;
            String avatar = user.getAvatar();
            String name = user.getName();
            todayPointsUser = new TodayPointsUser(totalPoints, name, avatar);
            todayPointsUserList.add(todayPointsUser);
        }
        return todayPointsUserList;
    }


    /**
     * 查询用户积分日榜排名
     * @param userId
     * @param todayDate
     */
    @Override
    public int getUserTodayPointsRank(Integer userId, String todayDate) {
        List<TodayPoints> todayPointsList = todayPointsMapper.selectList(new QueryWrapper<TodayPoints>()
                .eq("today_time", todayDate)
                .orderByDesc("today_points"));
        int rank = 0;
        for (int i = 0; i < todayPointsList.size(); i ++ ){
            TodayPoints todayPoints = todayPointsList.get(i);
            Integer everyUserId = todayPoints.getUserId();
            if(everyUserId == userId) {
                rank = i + 1;
            }
        }
        return rank;
    }

    /**
     * 预处理所有积分日榜用户
     * @param todayDate
     */
    @Override
    public boolean dealAllUserTodayPoints(String todayDate) {
        todayPointsMapper.delete(new QueryWrapper<>());
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        TodayPoints todayPointsUser = null;
        List<TodayPoints> todayPointsList = new ArrayList<>();
        for (int i = 0; i < users.size(); i ++ ){
            Integer id = users.get(i).getId();
            todayPointsUser = new TodayPoints(todayDate, 0, id);
            todayPointsList.add(todayPointsUser);
        }
        this.saveBatch(todayPointsList);
        return true;
    }
}
