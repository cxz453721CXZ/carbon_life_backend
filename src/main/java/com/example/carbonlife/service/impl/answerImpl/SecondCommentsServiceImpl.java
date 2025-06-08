package com.example.carbonlife.service.impl.answerImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.community.FirstComments;
import com.example.carbonlife.entity.community.Publish;
import com.example.carbonlife.entity.community.SecondComments;
import com.example.carbonlife.mapper.community.FirstCommentsMapper;
import com.example.carbonlife.mapper.community.PublishMapper;
import com.example.carbonlife.mapper.community.SecondCommentsMapper;
import com.example.carbonlife.service.community.FirstCommentsService;
import com.example.carbonlife.service.community.SecondCommentsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SecondCommentsServiceImpl extends ServiceImpl<SecondCommentsMapper, SecondComments> implements SecondCommentsService {


    @Resource
    private SecondCommentsMapper secondCommentsMapper;


    @Resource
    private FirstCommentsMapper firstCommentsMapper;

    @Resource
    private PublishMapper publishMapper;


    /**
     * 用户发表二级评论
     * @param secondComments
     * @return
     */
    @Override
    public boolean publishSecondComments(SecondComments secondComments) {
        Integer fid = secondComments.getFid();
        FirstComments firstComments = firstCommentsMapper.selectOne(new QueryWrapper<FirstComments>().eq("fid", fid));
        firstComments.setPls(firstComments.getPls() + 1);
        firstCommentsMapper.updateById(firstComments);

        Integer pubId = firstComments.getPubId();
        Publish publish = publishMapper.selectOne(new QueryWrapper<Publish>().eq("id", pubId));
        publish.setPls(publish.getPls() + 1);
        publishMapper.updateById(publish);

        this.save(secondComments);

        return true;
    }

    /**
     * 用户回复二级评论
     * @param sid
     * @param secondComments
     * @return
     */
    @Override
    public boolean answerSecondComments(Integer sid, SecondComments secondComments) {
        this.publishSecondComments(secondComments);
        SecondComments sc = secondCommentsMapper.selectOne(new QueryWrapper<SecondComments>().eq("sid", sid));
        sc.setPls(sc.getPls() + 1);
        secondCommentsMapper.updateById(sc);
        return true;
    }
}
