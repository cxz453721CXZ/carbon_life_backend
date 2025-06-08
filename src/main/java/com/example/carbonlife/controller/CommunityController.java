package com.example.carbonlife.controller;


import com.example.carbonlife.common.Result;
import com.example.carbonlife.entity.community.FirstComments;
import com.example.carbonlife.entity.community.Publish;
import com.example.carbonlife.entity.community.SecondComments;
import com.example.carbonlife.entity.community.Upvote;
import com.example.carbonlife.service.community.FirstCommentsService;
import com.example.carbonlife.service.community.PublishService;
import com.example.carbonlife.service.community.SecondCommentsService;
import com.example.carbonlife.service.community.UpvoteService;
import jakarta.annotation.Resource;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community")
public class CommunityController {

    @Resource
    private PublishService publishService;


    @Resource
    private FirstCommentsService firstCommentsService;


    @Resource
    private SecondCommentsService secondCommentsService;


    @Resource
    private UpvoteService upvoteService;


    /**
     * 社区发帖功能
     * @param publish
     * @return
     */
    @PostMapping("/publish")
    public Result publishRecord(@RequestBody Publish publish){
        return Result.success("发布成功", publishService.publishRecord(publish));
    }

    /**
     * 查询社区所有的帖子
     * @return
     */
    @GetMapping("/selectPublishRecord")
    public Result getPublishRecord(){
        return Result.success(publishService.selectPublishRecord());
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
    @GetMapping("/filterPublishRecord")
    public Result getFilterPublishRecord(@RequestParam boolean isNew,
                                         @RequestParam boolean isHot,
                                         @RequestParam boolean firTitle,
                                         @RequestParam boolean secTitle,
                                         @RequestParam(defaultValue = "") String contentText){
        return Result.success(publishService.getFilterPublishRecord(isNew, isHot, firTitle, secTitle, contentText));
    }

    /**
     * 用户发表一级评论
     * @param firstComments
     * @return
     */
    @PostMapping("/publishFirstComments")
    public Result publishFirstComments(@RequestBody FirstComments firstComments){
        return Result.success("发布成功", firstCommentsService.publishFirstComments(firstComments));
    }


    /**
     * 根据帖子id查询一级评论
     * @param pubId
     * @return
     */
    @GetMapping("/selectFirstCommentsByPubId")
    public Result getFirstCommentsByPubId(@RequestParam Integer pubId){
        return Result.success(firstCommentsService.getFirstCommentsByPubId(pubId));
    }

    /**
     * 用户发表二级评论
     * @param secondComments
     * @return
     */
    @PostMapping("/publishSecondComments")
    public Result publishSecondComments(@RequestBody SecondComments secondComments){
        return Result.success("发布成功", secondCommentsService.publishSecondComments(secondComments));
    }

    /**
     * 用户回复二级评论
     * @param sid
     * @param secondComments
     * @return
     */
    @PostMapping("/answerSecondComments")
    public Result answerSecondComments(@RequestParam Integer sid, @RequestBody SecondComments secondComments){
        return Result.success("发布成功", secondCommentsService.answerSecondComments(sid, secondComments));
    }


    /**
     * 用户给帖子或评论点赞
     * @param upvote
     * @return
     */
    @PostMapping("/upvoteToCommentsOrPublish")
    public Result upvoteToCommentsOrPublish(@RequestBody Upvote upvote){
        return Result.success("点赞成功", upvoteService.upvoteToCommentsOrPublish(upvote));
    }


    /**
     * 查询所有被当前用户点赞过的帖子或评论
     * @param userId
     * @param typeId
     * @return
     */
    @GetMapping("/queryAllUpvotePublish")
    public Result queryAllUpvotePublish(@RequestParam Integer userId, @RequestParam Integer typeId){
        return Result.success(upvoteService.queryAllUpvotePublish(userId, typeId));
    }

}
