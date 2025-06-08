package com.example.carbonlife.service.impl.communityImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.community.FirstComments;
import com.example.carbonlife.entity.community.Publish;
import com.example.carbonlife.entity.community.SecondComments;
import com.example.carbonlife.entity.community.Upvote;
import com.example.carbonlife.mapper.community.FirstCommentsMapper;
import com.example.carbonlife.mapper.community.PublishMapper;
import com.example.carbonlife.mapper.community.SecondCommentsMapper;
import com.example.carbonlife.mapper.community.UpvoteMapper;
import com.example.carbonlife.service.community.UpvoteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpvoteServiceImpl extends ServiceImpl<UpvoteMapper, Upvote> implements UpvoteService {


    @Resource
    private UpvoteMapper upvoteMapper;


    @Resource
    private PublishMapper publishMapper;


    @Resource
    private FirstCommentsMapper firstCommentsMapper;


    @Resource
    private SecondCommentsMapper secondCommentsMapper;


    /**
     * 用户给帖子或评论点赞
     * @param upvote
     * @return
     */
    @Override
    public boolean upvoteToCommentsOrPublish(Upvote upvote) {
        Integer upvoteId = upvote.getUpvoteId();
        Integer userId = upvote.getUserId();
        Integer typeId = upvote.getTypeId();
        Upvote upvoteOne = upvoteMapper.selectOne(new QueryWrapper<Upvote>()
                .eq("upvote_id", upvoteId)
                .eq("user_id", userId)
                .eq("type_id", typeId));
        if(upvoteOne != null) return false;
        this.save(upvote);
        if(typeId == 0){
            Publish publish = publishMapper.selectOne(new QueryWrapper<Publish>().eq("id", upvoteId));
            publish.setUpvote(publish.getUpvote() + 1);
            publishMapper.updateById(publish);
        }else {
            FirstComments firstComments = firstCommentsMapper.selectOne(new QueryWrapper<FirstComments>().eq("fid", upvoteId));
            firstComments.setUpvote(firstComments.getUpvote() + 1);
            firstCommentsMapper.updateById(firstComments);
        }
        return true;
    }

    /**
     * 查询所有被当前用户点赞过的帖子或评论
     * @param userId
     * @param typeId
     * @return
     */
    @Override
    public int [] queryAllUpvotePublish(Integer userId, Integer typeId) {
        List<Upvote> upvoteList = upvoteMapper.selectList(new QueryWrapper<Upvote>().eq("user_id", userId));
        List<Upvote> result = null;
        List<Publish> publishes = publishMapper.selectList(new QueryWrapper<>());
        List<FirstComments> firstComments = firstCommentsMapper.selectList(new QueryWrapper<>());
        int [] arr = null;
        int idx = 0;
        if(typeId == 0){
            arr = new int[publishes.size()];
            result = upvoteList.stream().filter((Upvote u) -> u.getTypeId() == 0).collect(Collectors.toList());
       }else{
            arr = new int[firstComments.size()];
            result = upvoteList.stream().filter((Upvote u) -> u.getTypeId() == 1).collect(Collectors.toList());
       }
       for (int i = 0; i < result.size(); i ++ ){
            Upvote upvote = result.get(i);
            Integer upvoteId = upvote.getUpvoteId();
            arr[idx ++] = upvoteId;
        }
        return arr;
    }
}
