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
@TableName("second_comments")
public class SecondComments {

    @TableId(type = IdType.AUTO)
    private Integer sid;

    @TableField("pl_content")
    private String plContent;

    private Integer pls;

    @TableField("ip_address")
    private String ipAddress;

    private Integer fid;

    private String receiver;

    @TableField("user_id")
    private Integer userId;

    @TableField("pl_date")
    private String plDate;
}
