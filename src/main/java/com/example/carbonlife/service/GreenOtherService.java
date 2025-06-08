package com.example.carbonlife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.GreenOther;

import java.util.List;


public interface GreenOtherService extends IService<GreenOther> {

    /***
     * 处理第二张图片上传
     * @param greenOther
     * @return boolean
     */
    boolean dealOtherPhoto(GreenOther greenOther);

    /**
     * 查询用户提交的第二张照片
     * @return boolean
     */
    List<GreenOther> selectNext(List<Integer> ids);

}
