package com.example.carbonlife.controller;


import com.example.carbonlife.common.Result;
import com.example.carbonlife.entity.goods.Address;

import com.example.carbonlife.entity.goods.Order;
import com.example.carbonlife.service.UserService;
import com.example.carbonlife.service.goods.AddressService;
import com.example.carbonlife.service.goods.GoodService;
import com.example.carbonlife.service.goods.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/change")
public class PointsForGoodsController {

    @Resource
    private GoodService goodService;


    @Resource
    private UserService userService;


    @Resource
    private AddressService addressService;


    @Resource
    private OrderService orderService;


    /**
     * 查询所有商品
     * @return
     */
    @GetMapping("/queryAllGoods")
    public Result queryAllGoods(){
        return Result.success(goodService.queryAllGoods());
    }


    /**
     * 查询用户积分
     * @param userId
     */
    @GetMapping("/queryUserPoints")
    public Result queryUserPoints(@RequestParam Integer userId){
        return Result.success(userService.queryUserPoints(userId));
    }


    /**
     * 保存或修改收货地址
     * @param address
     * @return
     */
    @PostMapping("/saveDeliveryAddress")
    public Result saveDeliveryAddress(@RequestBody Address address){
        return Result.success(addressService.saveDeliveryAddress(address));
    }


    /**
     * 查询用户地址
     * @param userId
     * @return
     */
    @GetMapping("/queryUserAllAddress")
    public Result queryUserAllAddress(@RequestParam Integer userId){
        return Result.success(addressService.queryUserAllAddress(userId));
    }


    /**
     * 根据id删除用户地址
     * @param id
     * @return
     */
    @GetMapping("/deleteUserAddressById")
    public Result deleteUserAddressById(@RequestParam Integer id){
        return Result.success(addressService.deleteUserAddressById(id));
    }


    /**
     * 查询用户的默认地址
     * @param id
     * @return
     */
    @GetMapping("/queryUserDefaultAddress")
    public Result queryUserDefaultAddress(@RequestParam Integer id){
        return Result.success(addressService.queryUserDefaultAddress(id));
    }


    /**
     * 用户进行积分兑换商品
     * @param order
     * @return
     */
    @PostMapping("/pointsChangeGoods")
    public Result insertOrderRecord(@RequestBody Order order){
        return Result.success(orderService.pointsChangeGoods(order));
    }


    /**
     * 查询用户订单
     * @param id
     * @return
     */
    @GetMapping("/queryUserOrders")
    public Result queryUserOrders(@RequestParam Integer id){
        return Result.success(orderService.queryUserOrders(id));
    }


}
