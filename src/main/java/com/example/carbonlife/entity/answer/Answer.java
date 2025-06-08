package com.example.carbonlife.entity.answer;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("answer")
public class Answer {

    @TableField("answer_date")
    private String answerDate;

    @TableField("correct_cnt")
    private Integer correctCnt;

    @TableField("type_id")
    private Integer typeId;

    @TableField("user_id")
    private Integer userId;
}
