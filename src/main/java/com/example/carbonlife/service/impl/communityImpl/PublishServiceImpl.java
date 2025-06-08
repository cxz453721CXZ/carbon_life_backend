package com.example.carbonlife.service.impl.communityImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.Dto.PublishDetail;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.entity.community.Publish;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.mapper.community.PublishMapper;
import com.example.carbonlife.service.community.PublishService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublishServiceImpl extends ServiceImpl<PublishMapper, Publish> implements PublishService {

    @Resource
    private PublishMapper publishMapper;


    @Resource
    private UserMapper userMapper;

    /**
     * 社区发帖功能
     * @param publish
     * @return
     */
    @Override
    public boolean publishRecord(Publish publish) {
        this.save(publish);
        return true;

    }

    /**
     * 查询社区所有的帖子
     * @return
     */
    @Override
    public List<PublishDetail> selectPublishRecord() {
        List<Publish> publishes = publishMapper.selectList(new QueryWrapper<>());
        List<PublishDetail> publishDetails = new ArrayList<>();
        Publish publish = null;
        PublishDetail publishDetail = null;
        for (int i = 0; i < publishes.size(); i ++ ){
            publish = publishes.get(i);
            publishDetail = new PublishDetail();
            Integer userId = publish.getUserId();
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
            BeanUtils.copyProperties(publish, publishDetail);
            publishDetail.setAvatar(user.getAvatar());
            publishDetail.setUserName(user.getName());
            publishDetails.add(publishDetail);
        }
        Collections.reverse(publishDetails);
        return publishDetails;
    }

    /**
     * 根据条件查询用户发布的帖子
     * @param isNew
     * @param isHot
     * @param firTitle
     * @param secTitle
     * @param contentText
     * @return
     */
    @Override
    public List<PublishDetail> getFilterPublishRecord(boolean isNew, boolean isHot, boolean firTitle, boolean secTitle, String contentText) {
        List<PublishDetail> publishDetails = this.selectPublishRecord();
        List<PublishDetail> publishDetailsNext = null;
        if(isNew) Collections.sort(publishDetails, (p1, p2) -> Integer.compare(p2.getId(), p1.getId()));
        if(isHot) Collections.sort(publishDetails, (p1, p2) -> Integer.compare(p2.getUpvote(), p1.getUpvote()));

        String firstText = "";
        String secondText = "";

        if(firTitle) firstText = "#绿色出行";
        if(secTitle) secondText = "#低碳生活";

        String finalFirstText = firstText;
        String finalSecondText = secondText;
        String finalContentText = contentText;

        publishDetailsNext = publishDetails.stream()
                .filter(p -> p.getContent().contains(finalFirstText) && p.getContent().contains(finalSecondText)  && p.getContent().contains(finalContentText)
                        || p.getTitle().contains(finalFirstText) && p.getTitle().contains(finalSecondText) && p.getTitle().contains(finalContentText))
                .collect(Collectors.toList());


        return publishDetailsNext;
    }
}
