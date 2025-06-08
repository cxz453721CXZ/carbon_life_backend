package com.example.carbonlife.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.goods.Good;

import java.util.List;

public interface GoodService extends IService<Good> {

    /**
     * 查询所有商品
     * @return
     */
    List<Good> queryAllGoods();
}
