package com.example.carbonlife.service.recycle;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.Dto.RecycleDto;
import com.example.carbonlife.entity.recycle.Recycle;

import java.util.List;

public interface RecycleService extends IService<Recycle> {


    /**
     * 用户提交回收预约
     * @param recycle
     * @return
     */
    boolean insertRecycleRecord(Recycle recycle);


    /**
     * 查询用户所有回收记录
     * @param userId
     * @return
     */
    List<RecycleDto> queryAllRecycleRecord(Integer userId);
}
