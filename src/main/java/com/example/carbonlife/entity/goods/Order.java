package com.example.carbonlife.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("goods_order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("order_date")
    private String orderDate;

    @TableField("good_id")
    private Integer goodId;

    @TableField("address_id")
    private Integer addressId;

    private Integer quantity;

    @TableField("user_id")
    private Integer userId;

}
