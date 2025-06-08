package com.example.carbonlife.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("questionnaire")
public class Questionnaire {

    private String answer;

    private String advice;

    @TableField("type_id")
    private Integer typeId;

    @TableField("user_id")
    private Integer userId;

    @TableField("submit_date")
    private String submitDate;
}
