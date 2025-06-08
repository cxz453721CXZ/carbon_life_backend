package com.example.carbonlife.service.impl.recycle;

import com.alipay.api.domain.CallDetailDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.entity.Dto.RecycleDto;
import com.example.carbonlife.entity.goods.Address;
import com.example.carbonlife.entity.recycle.Recycle;
import com.example.carbonlife.mapper.goods.AddressMapper;
import com.example.carbonlife.mapper.recycle.RecycleMapper;
import com.example.carbonlife.service.recycle.RecycleService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecycleServiceImpl extends ServiceImpl<RecycleMapper, Recycle> implements RecycleService {


    @Resource
    private RecycleMapper recycleMapper;


    @Resource
    private AddressMapper addressMapper;

    /**
     * 用户提交回收预约
     * @param recycle
     * @return
     */
    @Override
    public boolean insertRecycleRecord(Recycle recycle) {
        this.save(recycle);
        return true;
    }


    /**
     * 查询用户所有回收记录
     * @param userId
     * @return
     */
    @Override
    public List<RecycleDto> queryAllRecycleRecord(Integer userId) {
        List<Recycle> recycles = recycleMapper.selectList(new QueryWrapper<Recycle>().eq("user_id", userId));
        Recycle recycle = null;
        List<RecycleDto> recycleDtoList = new ArrayList<>();
        for (int i = 0; i < recycles.size(); i ++ ){
            recycle = recycles.get(i);
            RecycleDto recycleDto = new RecycleDto();
            BeanUtils.copyProperties(recycle, recycleDto);
            Integer cycleAddressId = recycle.getCycleAddressId();
            Address address = addressMapper.selectOne(new QueryWrapper<Address>().eq("id", cycleAddressId));
            recycleDto.setRegion(address.getRegion());
            recycleDto.setDetailAddress(address.getDetailAddress());
            recycleDtoList.add(recycleDto);
        }
        Collections.reverse(recycleDtoList);
        return recycleDtoList;
    }

}
