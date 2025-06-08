package com.example.carbonlife.entity.goods;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("good")
@NoArgsConstructor
@AllArgsConstructor
public class Good {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private Integer stock;

    private String url;

    private Integer sales;

    private String detail;

    private Integer price;
}
