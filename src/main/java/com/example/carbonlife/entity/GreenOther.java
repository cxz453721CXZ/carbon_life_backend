package com.example.carbonlife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import kotlin.jvm.internal.RepeatableContainer;
import lombok.Data;

@Data
@TableName("green_other")
public class GreenOther {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String photo;

    @TableField("green_id")
    private Integer greenId;

}
