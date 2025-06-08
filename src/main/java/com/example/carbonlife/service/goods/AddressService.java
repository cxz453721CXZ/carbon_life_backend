package com.example.carbonlife.service.goods;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.goods.Address;
import com.example.carbonlife.entity.goods.Order;

import java.util.List;


public interface AddressService extends IService<Address> {

    /**
     * 保存或修改收货地址
     * @param address
     * @return
     */
    boolean saveDeliveryAddress(Address address);


    /**
     * 查询用户地址
     * @param userId
     * @return
     */
    List<Address> queryUserAllAddress(Integer userId);


    /**
     * 根据id删除用户地址
     * @param id
     * @return
     */
    boolean deleteUserAddressById(Integer id);


    /**
     * 查询用户的默认地址
     * @param id
     * @return
     */
    Address queryUserDefaultAddress(Integer id);

}
