package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.ResultCode;
import com.example.dao.*;
import com.example.entity.*;
import com.example.exception.CustomException;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public User add(User user) {
        // 唯一校验
        User info = userDao.findByYhm(user.getYhm());
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if (StringUtils.isEmpty(user.getMm())) {
            // origin code 123456
            user.setMm("123456");
        }
        if (StringUtils.isEmpty(user.getTx())) {
            user.setTx("http://localhost:8080/files/qy-default.png");
        }
        userDao.insertSelective(user);
        return user;
    }

    public void delete(Integer id) {
        userDao.deleteByPrimaryKey(id);
    }

    public void update(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    public User findById(Integer id) {
        return userDao.selectByPrimaryKey(id);
    }

    public List<User> findAll() {
        return userDao.findBySearch(null);
    }

    public PageInfo<User> findPage(User search, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> all = findByCondition(request, search);

        return PageInfo.of(all);
    }

    public List<User> findByCondition(HttpServletRequest request, User search) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }
		return userDao.findBySearch(search);
    }

    public User findByYhm(String yhm) {
        return userDao.findByYhm(yhm);
    }

    public User login(String yhm, String mm) {
        User user = userDao.findByYhm(yhm);
        if (user == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!mm.equalsIgnoreCase(user.getMm())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return user;
    }

}
