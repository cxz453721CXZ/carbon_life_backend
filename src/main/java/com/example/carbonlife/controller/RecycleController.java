package com.example.carbonlife.controller;


import com.example.carbonlife.common.Result;
import com.example.carbonlife.entity.recycle.Recycle;
import com.example.carbonlife.service.recycle.RecycleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recycle")
public class RecycleController {

    @Resource
    private RecycleService recycleService;


    /**
     * 用户提交回收预约
     * @param recycle
     * @return
     */
    @PostMapping("/insertRecycleRecord")
    public Result insertRecycleRecord(@RequestBody Recycle recycle){
        return Result.success(recycleService.insertRecycleRecord(recycle));
    }


    /**
     * 查询用户所有回收记录
     * @param userId
     * @return
     */
    @GetMapping("/queryAllRecycleRecord")
    public Result queryAllRecycleRecord(@RequestParam Integer userId){
        return Result.success(recycleService.queryAllRecycleRecord(userId));
    }

}
