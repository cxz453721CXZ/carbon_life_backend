package com.example.carbonlife.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecycleDto {

    private Integer id;

    private Integer type;

    private String weight;

    private Integer cycleAddressId;

    private String cycleDate;

    private Integer cycleReward;

    private Integer userId;

    private Integer isAccess;

    private String region;

    private String detailAddress;
}
