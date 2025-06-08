package com.example.carbonlife.entity.community;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("upvote")
@NoArgsConstructor
@AllArgsConstructor
public class Upvote {

    @TableField("upvote_id")
    private Integer upvoteId;

    @TableField("type_id")
    private Integer typeId;

    @TableField("user_id")
    private Integer userId;
}
