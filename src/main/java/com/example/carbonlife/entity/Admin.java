package com.example.carbonlife.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {

    /**
     * 管理员id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long aid;

    /**
     * 用户名
     */
    @TableField("adminname")
    private String username;

    /**
     * 密码
     */
    private String password;
}
