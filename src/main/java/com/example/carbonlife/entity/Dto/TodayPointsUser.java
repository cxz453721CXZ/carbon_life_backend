package com.example.carbonlife.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodayPointsUser {

    private Integer todayPoints;

    private String name;

    private String avatar;
}
