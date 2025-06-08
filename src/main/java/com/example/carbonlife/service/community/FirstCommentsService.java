package com.example.carbonlife.service.community;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.Dto.FirstCommentsDetail;
import com.example.carbonlife.entity.community.FirstComments;

import java.util.List;

public interface FirstCommentsService extends IService<FirstComments> {

    /**
     * 用户发表一级评论
     * @param firstComments
     * @return
     */
    boolean publishFirstComments(FirstComments firstComments);

    /**
     * 根据帖子id查询一级评论
     * @param pubId
     * @return
     */
    List<FirstCommentsDetail> getFirstCommentsByPubId(Integer pubId);
}
