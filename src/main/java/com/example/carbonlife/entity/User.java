package com.example.carbonlife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class User {
    //用户ID
    @TableId(type = IdType.AUTO)
    private Integer id;

    //支付宝openid
    @TableField("open_id")
    private String openId;

    //用户昵称
    private String name;

    //积分数量
    private Integer points;

    //头像
    private String avatar;

    //登录日期
    @TableField("login_date")
    private String loginDate;

    public User(String name, Integer points, String avatar, Object obj) {
        this.name = name;
        this.points = points;
        this.avatar = avatar;
    }
}
