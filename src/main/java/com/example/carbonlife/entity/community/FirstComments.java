package com.example.carbonlife.entity.community;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.xiaoymin.knife4j.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@TableName("first_comments")
@NoArgsConstructor
@AllArgsConstructor
public class FirstComments {

    @TableId(type = IdType.AUTO)
    private Integer fid;

    @TableField("pl_content")
    private String plContent;

    @TableField("ip_address")
    private String ipAddress;

    @TableField("pl_date")
    private String plDate;

    private Integer upvote;

    private Integer pls;

    @TableField("pub_id")
    private Integer pubId;

    @TableField("user_id")
    private Integer userId;

}
