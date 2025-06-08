package com.example.carbonlife.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.GreenChallenge;
import com.example.carbonlife.entity.GreenOther;
import com.example.carbonlife.mapper.GreenChallengeMapper;
import com.example.carbonlife.mapper.GreenOtherMapper;
import com.example.carbonlife.service.GreenOtherService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GreenOtherServiceImpl extends ServiceImpl<GreenOtherMapper, GreenOther> implements GreenOtherService {


    @Resource
    private GreenChallengeMapper greenChallengeMapper;


    @Resource
    private GreenOtherMapper greenOtherMapper;

    /***
     * 处理第二张图片上传
     * @param greenOther
     * @return boolean
     */
    @Override
    public boolean dealOtherPhoto(GreenOther greenOther) {
        List<GreenChallenge> greenChallenges = greenChallengeMapper.selectList(new QueryWrapper<GreenChallenge>());
        GreenChallenge greenChallenge = greenChallenges.get(greenChallenges.size() - 1);
        Integer id = greenChallenge.getId();
        greenOther.setGreenId(id);
        this.save(greenOther);
        return true;
    }

    /**
     * 查询用户提交的第二张照片
     * @return boolean
     */
    @Override
    public List<GreenOther> selectNext(List<Integer> ids) {
        List<GreenOther> others = new ArrayList<>();
        ids.forEach(id -> {
            GreenOther greenOther = greenOtherMapper.selectOne(new QueryWrapper<GreenOther>().eq("green_id", id));
            others.add(greenOther);
        });
        return others;
    }
}
