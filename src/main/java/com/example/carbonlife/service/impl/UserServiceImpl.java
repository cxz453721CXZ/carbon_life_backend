package com.example.carbonlife.service.impl;

import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.carbonlife.common.Constants;
import com.example.carbonlife.entity.User;
import com.example.carbonlife.exception.ServiceException;
import com.example.carbonlife.mapper.UserMapper;
import com.example.carbonlife.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import okhttp3.internal.http2.ErrorCode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //居民用户数据层
    @Resource
    private UserMapper userMapper;


    /**
     * 根据openId查询用户信息
     * @param openId
     * @return User
     */
    @Override
    public User getUserInfo(String openId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
    }

    /**
     * 根据时间更新每周挑战
     * @param currentTime
     * @param id
     */
    @Override
    public boolean updateDate(String currentTime, Integer id) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", id));
        String loginTime = user.getLoginDate();
        long betweenDay = DateUtil.betweenDay(DateUtil.parse(currentTime), DateUtil.parse(loginTime), true);
        if(betweenDay >= 7) {
            user.setLoginDate(currentTime);
            this.saveOrUpdate(user);
            return true;
        }
        return false;
    }

    /**
     * 查询用户积分
     * @param userId
     */
    @Override
    public User selectUserInfo(Integer userId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
    }

    /**
     * 查询所有用户积分
     */
    @Override
    public List<User> selectAllUsers() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().orderByDesc("points"));
        List<User> filteredUser = users.stream().map(user -> new User(user.getName(),user.getPoints(), user.getAvatar(), null))
                .collect(Collectors.toList());
        return filteredUser;
    }

    /**
     * 查询用户积分排名
     * @param userId
     */
    @Override
    public int getUserRank(Integer userId) {
        int rank = 0;
        List<User> users = userMapper.selectList(new QueryWrapper<User>().orderByDesc("points"));
        for (int i = 0; i < users.size(); i ++ ){
            User user = users.get(i);
            if(user.getId() == userId) rank = i + 1;
        }
        return rank;
    }

    /**
     * 查询用户积分
     * @param userId
     */
    @Override
    public int queryUserPoints(Integer userId) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", userId));
        return user.getPoints();
    }


    /**
     * 微信小程序登录
     * @param sessionInfo
     * @param request
     * @return
     */
    @Override
    public User userLoginByMpOpen(WxMaJscode2SessionResult sessionInfo, HttpServletRequest request) {
        String miniOpenId = sessionInfo.getOpenid();
        User user = null;
        // 单机锁
        synchronized (miniOpenId.intern()) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("open_id", miniOpenId);
            user = this.getOne(queryWrapper);
            if (user == null) {
                user = new User();
                user.setOpenId(miniOpenId);
                user.setPoints(1000);
                user.setAvatar("https://carbon2.obs.cn-north-4.myhuaweicloud.com:443/feiyi%2Ftest%2F0%2FSaWDBjoT-%E7%A2%B3%E7%B4%A2%E7%94%9F%E6%B4%BB%E5%9B%BE%E6%A0%87.png");
                user.setName("碳行者");
                user.setLoginDate(DateUtil.formatDate(DateUtil.date()));
                boolean save = this.save(user);
                if (!save) {
                    throw new ServiceException(Constants.CODE_500, "系统错误");
                }
            }
            request.getSession().setAttribute("carbon", user);
        }
        return user;
    }

    /**
     * 获取当前登录用户
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("carbon");
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new ServiceException(Constants.CODE_500, "用户未登录");
        }
        //根据id进行查询
        Integer userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new ServiceException(Constants.CODE_500, "用户未登录");
        }
        return currentUser;
    }


}
