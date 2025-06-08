package com.example.carbonlife.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.Dto.GoodsOrderDto;
import com.example.carbonlife.entity.goods.Order;

import java.util.List;

public interface OrderService extends IService<Order> {


    /**
     * 用户进行积分兑换商品
     * @param order
     * @return
     */
    boolean pointsChangeGoods(Order order);

    /**
     * 查询用户订单
     * @param id
     * @return
     */
    List<GoodsOrderDto> queryUserOrders(Integer id);
}
