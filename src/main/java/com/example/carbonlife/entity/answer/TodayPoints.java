package com.example.carbonlife.entity.answer;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("today_points")
public class TodayPoints implements Serializable {

    @TableField("today_time")
    private String todayTime;

    @TableField("today_points")
    private Integer todayPoints;

    @TableField("user_id")
    private Integer userId;
}
