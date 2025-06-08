package com.example.carbonlife.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.common.Constants;
import com.example.carbonlife.entity.Admin;
import com.example.carbonlife.exception.ServiceException;
import com.example.carbonlife.mapper.AdminMapper;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.service.AdminManageService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 管理员登录注册
 */

@Service
public class AdminManageServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminManageService {


    @Resource
    private AdminMapper adminMapper;


    @Resource
    private UserMapper userMapper;

    /**
     * @param admin 管理员注册信息
     * @return boolean
     */
    @Override
    public boolean register(Admin admin) {
        checkAdmin(admin);
        Admin adminUser = adminMapper.selectOne(new QueryWrapper<Admin>().eq("adminname", admin.getUsername()));
        if(adminUser != null) throw new ServiceException(Constants.CODE_500, "用户名已存在");
        int cnt = adminMapper.insert(admin);
        if(cnt != 1) throw new ServiceException(Constants.CODE_500, "注册失败");
        return true;
    }

    /**
     * @param admin 管理员登录信息
     * @return boolean
     */
    @Override
    public boolean login(Admin admin) {
        checkAdmin(admin);
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("adminname", admin.getUsername()).eq("password", admin.getPassword());
        Admin user = adminMapper.selectOne(queryWrapper);
        if(user == null) throw new ServiceException(Constants.CODE_500, "用户名或密码错误");
        return true;
    }




    private void checkAdmin(Admin admin){
        if(admin == null) throw new ServiceException(Constants.CODE_500, "系统错误");
        String username = admin.getUsername();
        String password = admin.getPassword();
        if(username == null || password == null || StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw new ServiceException(Constants.CODE_500, "用户名或密码不能为空");
    }




}
