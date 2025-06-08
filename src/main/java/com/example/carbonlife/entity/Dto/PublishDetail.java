package com.example.carbonlife.entity.Dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishDetail {

    private Integer id;

    private String title;

    private String content;

    private String url;

    private Integer upvote;

    private Integer pls;

    private Integer userId;

    private String userName;

    private String avatar;

    private String pubDate;

    private Integer transfer;
}
