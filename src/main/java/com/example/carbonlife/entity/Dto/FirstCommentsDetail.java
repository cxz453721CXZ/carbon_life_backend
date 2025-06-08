package com.example.carbonlife.entity.Dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.carbonlife.entity.community.SecondComments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FirstCommentsDetail {

    private Integer fid;

    private String plContent;

    private String ipAddress;

    private String plDate;

    private Integer upvote;

    private Integer pls;

    private Integer pubId;

    private Integer userId;

    private String userName;

    private String avatar;

    private List<SecondCommentsDetail> secondCommentsDetails;
}
