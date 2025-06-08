package com.example.carbonlife.service.impl.goodServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.goods.Good;
import com.example.carbonlife.mapper.goods.GoodMapper;
import com.example.carbonlife.service.goods.GoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {

    @Resource
    private GoodMapper goodMapper;

    /**
     * 查询所有商品
     * @return
     */
    @Override
    public List<Good> queryAllGoods() {
        List<Good> goods = goodMapper.selectList(new QueryWrapper<>());
        return goods;
    }
}
