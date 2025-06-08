package com.example.carbonlife.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sign")
public class Sign {

    @TableField("sign_date")
    private String signDate;

    @TableField("user_id")
    private Integer userId;

    @TableField("sign_record")
    private String signRecord;
}
