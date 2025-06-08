package com.example.carbonlife.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsOrderDto {

    private String name;

    private String type;

    private Integer price;

    private String url;

    private Integer cnt;

    private String consignee;

    private String phone;

    private String region;

    private String detailAddress;

    private String orderDate;

}
