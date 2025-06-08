package com.example.carbonlife.entity.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecondCommentsDetail {

    private Integer sid;

    private String plContent;

    private Integer pls;

    private String ipAddress;

    private Integer fid;

    private String receiver;

    private String avatar;

    private String userName;

    private Integer userId;

    private String plDate;
}
