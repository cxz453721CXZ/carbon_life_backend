package com.example.carbonlife.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.community.SecondComments;

public interface SecondCommentsService extends IService<SecondComments> {

    /**
     * 用户发表二级评论
     * @param secondComments
     * @return
     */
    boolean publishSecondComments(SecondComments secondComments);

    /**
     * 用户回复二级评论
     * @param sid
     * @param secondComments
     * @return
     */
    boolean answerSecondComments(Integer sid, SecondComments secondComments);
}
