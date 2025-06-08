package com.example.carbonlife.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.community.Upvote;

public interface UpvoteService extends IService<Upvote> {

    /**
     * 用户给帖子或评论点赞
     * @param upvote
     * @return
     */
    boolean upvoteToCommentsOrPublish(Upvote upvote);


    /**
     * 查询所有被当前用户点赞过的帖子或评论
     * @param userId
     * @param typeId
     * @return
     */
    int [] queryAllUpvotePublish(Integer userId, Integer typeId);
}
