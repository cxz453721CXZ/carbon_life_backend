package com.example.carbonlife.service.impl.goodServiceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.goods.Address;
import com.example.carbonlife.mapper.goods.AddressMapper;
import com.example.carbonlife.service.goods.AddressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {


    @Resource
    private AddressMapper addressMapper;


    /**
     * 保存或修改收货地址
     * @param address
     * @return
     */
    @Override
    public boolean saveDeliveryAddress(Address address) {
        Integer id = address.getId();
        Integer userId = address.getUserId();
        Address addressOne = addressMapper.selectOne(new QueryWrapper<Address>().eq("id", id));
        if(addressOne != null){
            this.saveOrUpdate(address);
            if(address.getIsDefault() == 1){
                List<Address> addresses = addressMapper.selectList(new QueryWrapper<Address>().eq("user_id", userId));
                for (int i = 0; i < addresses.size(); i ++ ){
                    if(addresses.get(i).getId() == id) continue;
                    addresses.get(i).setIsDefault(0);
                }
                this.updateBatchById(addresses);
            }
        }else{
            this.save(address);
            List<Address> addresses = addressMapper.selectList(new QueryWrapper<Address>().eq("user_id", userId));
            Address tempSaveAddress = addresses.get(addresses.size() - 1);
            if(tempSaveAddress.getIsDefault() == 1){
                for (int i = 0; i < addresses.size() - 1; i ++ ){
                    addresses.get(i).setIsDefault(0);
                }
                this.updateBatchById(addresses);
            }
        }
        return true;
    }


    /**
     * 查询用户地址
     * @param userId
     * @return
     */
    @Override
    public List<Address> queryUserAllAddress(Integer userId) {
        List<Address> addressList = addressMapper.selectList(new QueryWrapper<Address>().eq("user_id", userId));
        Collections.reverse(addressList);
        return addressList;
    }


    /**
     * 根据id删除用户地址
     * @param id
     * @return
     */
    @Override
    public boolean deleteUserAddressById(Integer id) {
        addressMapper.deleteById(id);
        return true;
    }


    /**
     * 查询用户的默认地址
     * @param id
     * @return
     */
    @Override
    public Address queryUserDefaultAddress(Integer id) {
        List<Address> addressList = addressMapper.selectList(new QueryWrapper<Address>().eq("user_id", id));
        Address address = null;
        for (int i = 0; i < addressList.size(); i ++ ){
            address = addressList.get(i);
            Integer isDefault = address.getIsDefault();
            if(isDefault == 1) return address;
        }
        return null;
    }


}
