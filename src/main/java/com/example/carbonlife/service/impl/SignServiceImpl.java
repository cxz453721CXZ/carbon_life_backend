package com.example.carbonlife.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.Sign;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.mapper.SignMapper;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.service.SignService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class SignServiceImpl extends ServiceImpl<SignMapper, Sign> implements SignService{


    @Resource
    private UserMapper userMapper;

    @Resource
    private SignMapper signMapper;

    /**
     * 用户签到
     * @param sign
     */
    @Override
    public boolean userDaySign(Sign sign) {
        this.save(sign);
        Integer userId = sign.getUserId();
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        user.setPoints(user.getPoints() + 1);
        userMapper.updateById(user);
        return true;
    }

    /**
     * 查询用户当天是否签到
     * @param sign
     */
    @Override
    public boolean selectIsSign(Sign sign) {
        return signMapper.selectOne(new QueryWrapper<Sign>()
                .eq("sign_date", sign.getSignDate())
                .eq("user_id", sign.getUserId())) != null;
    }

    /**
     * 查询用户六天的签到记录
     * @param userId
     */
    @Override
    public String saveSignRecord(Integer userId) {
        List<Sign> signList = signMapper.selectList(new QueryWrapper<Sign>().eq("user_id", userId));
        Sign sign = signList.get(signList.size() - 1);
        return sign.getSignRecord();
    }

    /**
     * 用户签到天数
     * @param userId
     */
    @Override
    public int selectSignDayCnt(Integer userId) {
        return signMapper.selectList(new QueryWrapper<Sign>().eq("user_id", userId)).size();
    }

    /**
     * 用户连续签到天数
     * @param userId
     */
    @Override
    public int selectConSignDayCnt(Integer userId) {
        List<Sign> signList = signMapper.selectList(new QueryWrapper<Sign>().eq("user_id", userId));
        int cnt = 0;
        for (int i = signList.size() - 1; i >= 1; i -- ){
            Sign sign1 = signList.get(i);
            String signDate1 = sign1.getSignDate();
            Date date1 = DateUtil.parse(signDate1);

            Sign sign2 = signList.get(i - 1);
            String signDate2 = sign2.getSignDate();
            Date date2 = DateUtil.parse(signDate2);

            long days = DateUtil.betweenDay(date1, date2, true);
            if(days == 1) cnt ++;
            else break;
        }
        return cnt + 1;
    }

}
