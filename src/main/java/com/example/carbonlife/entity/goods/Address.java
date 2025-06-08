package com.example.carbonlife.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String consignee;

    private String phone;

    private String region;

    @TableField("detail_address")
    private String detailAddress;

    @TableField("is_default")
    private Integer isDefault;

    @TableField("user_id")
    private Integer userId;
}
