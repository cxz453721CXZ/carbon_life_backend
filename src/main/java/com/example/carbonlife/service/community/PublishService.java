package com.example.carbonlife.service.community;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.Dto.PublishDetail;
import com.example.carbonlife.entity.community.Publish;

import java.util.List;

public interface PublishService extends IService<Publish> {

    /**
     * 社区发帖功能
     * @param publish
     * @return
     */
    boolean publishRecord(Publish publish);


    /**
     * 查询社区所有的帖子
     * @return
     */
    List<PublishDetail> selectPublishRecord();


    /**
     * 根据条件查询用户发布的帖子
     * @param isNew
     * @param isHot
     * @param firTitle
     * @param secTitle
     * @param contentText
     * @return
     */
    List<PublishDetail> getFilterPublishRecord(boolean isNew, boolean isHot, boolean firTitle, boolean secTitle, String contentText);
}
