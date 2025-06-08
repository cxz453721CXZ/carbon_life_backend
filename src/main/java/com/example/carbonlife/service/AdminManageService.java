package com.example.carbonlife.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.carbonlife.entity.Admin;

/**
 * 管理员登录注册
 */
public interface AdminManageService extends IService<Admin> {

    /**
     * 注册
     * @param admin 管理员注册信息
     */
    boolean register(Admin admin);


    /**
     * 登录
     * @param admin 管理员登录信息
     */
    boolean login(Admin admin);





}
