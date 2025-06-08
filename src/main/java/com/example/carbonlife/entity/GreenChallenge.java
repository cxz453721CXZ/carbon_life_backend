package com.example.carbonlife.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 绿色挑战提交记录实体类
 * @author 陈新知
 */

@Data
@TableName("green_challenge")
public class GreenChallenge {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String type;

    @TableField("type_id")
    private Integer typeId;

    private String photo;

    @TableField("submit_time")
    private String submitTime;

    @TableField("submit_data")
    private Integer submitData;

    private Integer reward;

    @TableField("is_access")
    private Integer isAccess;

    @TableField("user_id")
    private Integer userId;

    @TableField("sub_cnt")
    private Integer subCnt;
}
