package com.example.carbonlife.service.impl.communityImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.Dto.FirstCommentsDetail;
import com.example.carbonlife.entity.Dto.SecondCommentsDetail;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.entity.community.FirstComments;
import com.example.carbonlife.entity.community.Publish;
import com.example.carbonlife.entity.community.SecondComments;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.mapper.community.FirstCommentsMapper;
import com.example.carbonlife.mapper.community.PublishMapper;
import com.example.carbonlife.mapper.community.SecondCommentsMapper;
import com.example.carbonlife.service.community.FirstCommentsService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FirstCommentsServiceImpl extends ServiceImpl<FirstCommentsMapper, FirstComments> implements FirstCommentsService {

    @Resource
    private FirstCommentsMapper firstCommentsMapper;


    @Resource
    private SecondCommentsMapper secondCommentsMapper;


    @Resource
    private PublishMapper publishMapper;


    @Resource
    private UserMapper userMapper;

    /**
     * 用户发表一级评论
     * @param firstComments
     * @return
     */
    @Override
    public boolean publishFirstComments(FirstComments firstComments) {
        Integer pubId = firstComments.getPubId();
        Publish publish = publishMapper.selectOne(new QueryWrapper<Publish>().eq("id", pubId));
        publish.setPls(publish.getPls() + 1);
        publishMapper.updateById(publish);
        this.save(firstComments);
        return true;
    }

    /**
     * 根据帖子id查询一级评论
     * @param pubId
     * @return
     */
    @Override
    public List<FirstCommentsDetail> getFirstCommentsByPubId(Integer pubId) {
        List<FirstComments> firstComments = firstCommentsMapper.selectList(new QueryWrapper<FirstComments>().eq("pub_id", pubId));
        List<FirstCommentsDetail> firstCommentsDetails = new ArrayList<>();
        for (int i = 0; i < firstComments.size(); i ++ ){
            FirstComments firstComment = firstComments.get(i);
            Integer userId = firstComment.getUserId();
            FirstCommentsDetail firstCommentsDetail = new FirstCommentsDetail();
            BeanUtils.copyProperties(firstComment, firstCommentsDetail);
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
            firstCommentsDetail.setAvatar(user.getAvatar());
            firstCommentsDetail.setUserName(user.getName());

            SecondComments secondComments = null;
            SecondCommentsDetail secondCommentsDetail = null;
            List<SecondCommentsDetail> secondCommentsDetails = new ArrayList<>();
            Integer fid = firstCommentsDetail.getFid();
            List<SecondComments> secondCommentsList = secondCommentsMapper.selectList(new QueryWrapper<SecondComments>().eq("fid", fid));
            for (int j = 0; j < secondCommentsList.size(); j ++ ){
                secondComments = secondCommentsList.get(j);
                secondCommentsDetail = new SecondCommentsDetail();
                BeanUtils.copyProperties(secondComments, secondCommentsDetail);
                Integer scId = secondComments.getUserId();
                User scUser = userMapper.selectOne(new QueryWrapper<User>().eq("id", scId));
                secondCommentsDetail.setAvatar(scUser.getAvatar());
                secondCommentsDetail.setUserName(scUser.getName());
                secondCommentsDetails.add(secondCommentsDetail);
            }
            firstCommentsDetail.setSecondCommentsDetails(secondCommentsDetails);
            firstCommentsDetails.add(firstCommentsDetail);
        }
        Collections.reverse(firstCommentsDetails);

        return firstCommentsDetails;
    }
}
