package com.example.carbonlife.service.impl.goodServiceImpl;

import com.alipay.api.domain.OrderGoodDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.common.Constants;
import com.example.carbonlife.entity.Dto.GoodsOrderDto;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.entity.goods.Address;
import com.example.carbonlife.entity.goods.Good;
import com.example.carbonlife.entity.goods.Order;
import com.example.carbonlife.exception.ServiceException;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.mapper.goods.AddressMapper;
import com.example.carbonlife.mapper.goods.GoodMapper;
import com.example.carbonlife.mapper.goods.OrderMapper;
import com.example.carbonlife.service.goods.OrderService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {


    @Resource
    private GoodMapper goodMapper;


    @Resource
    private UserMapper userMapper;


    @Resource
    private OrderMapper orderMapper;


    @Resource
    private AddressMapper addressMapper;


    /**
     * 用户进行积分兑换商品
     * @param order
     * @return
     */
    @Override
    public boolean pointsChangeGoods(Order order) {
        Integer quantity = order.getQuantity();
        Integer goodId = order.getGoodId();
        Integer userId = order.getUserId();
        Good good = goodMapper.selectOne(new QueryWrapper<Good>().eq("id", goodId));
        Integer price = good.getPrice();
        good.setSales(good.getSales() + quantity);
        good.setStock(good.getStock() - quantity);
        goodMapper.updateById(good);

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        Integer points = user.getPoints();
        if(points < price * quantity) throw new ServiceException(Constants.CODE_500, "积分不足");
        user.setPoints(user.getPoints() - price * quantity);
        userMapper.updateById(user);

        this.save(order);
        return true;
    }


    /**
     * 查询用户订单
     * @param id
     * @return
     */
    @Override
    public List<GoodsOrderDto> queryUserOrders(Integer id) {
        List<Order> orders = orderMapper.selectList(new QueryWrapper<Order>().eq("user_id", id));
        Collections.reverse(orders);
        List<GoodsOrderDto> goodsOrderDto = new ArrayList<>();
        Order order = null;
        GoodsOrderDto goodsOrderDtoOne = null;
        for (int i = 0; i < orders.size(); i ++ ){
            order = orders.get(i);
            goodsOrderDtoOne = new GoodsOrderDto();
            Integer goodId = order.getGoodId();
            Good goodOne = goodMapper.selectOne(new QueryWrapper<Good>().eq("id", goodId));
            BeanUtils.copyProperties(goodOne, goodsOrderDtoOne);
            goodsOrderDtoOne.setOrderDate(order.getOrderDate());
            goodsOrderDtoOne.setCnt(order.getQuantity());
            Integer addressId = order.getAddressId();
            Address address = addressMapper.selectOne(new QueryWrapper<Address>().eq("id", addressId));
            goodsOrderDtoOne.setConsignee(address.getConsignee());
            goodsOrderDtoOne.setPhone(address.getPhone());
            goodsOrderDtoOne.setRegion(address.getRegion());
            goodsOrderDtoOne.setDetailAddress(address.getDetailAddress());
            goodsOrderDto.add(goodsOrderDtoOne);
        }
        return goodsOrderDto;
    }
}
