package com.example.carbonlife.entity.community;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("community_publish")
public class Publish {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    private String url;

    private Integer upvote;

    private Integer pls;

    private Integer transfer;

    @TableField("user_id")
    private Integer userId;

    @TableField("pub_date")
    private String pubDate;
}
