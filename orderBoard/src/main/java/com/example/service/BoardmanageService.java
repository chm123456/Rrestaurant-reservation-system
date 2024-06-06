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
public class BoardmanageService {

    @Resource
    private BoardmanageDao boardmanageDao;

    public Boardmanage add(Boardmanage boardmanage) {
        boardmanageDao.insertSelective(boardmanage);
        return boardmanage;
    }

    public void delete(Integer id) {
        boardmanageDao.deleteByPrimaryKey(id);
    }

    public void update(Boardmanage boardmanage) {
        boardmanageDao.updateByPrimaryKeySelective(boardmanage);
    }

    public Boardmanage findById(Integer id) {
        return boardmanageDao.selectByPrimaryKey(id);
    }

    public List<Boardmanage> findAll() {
        return boardmanageDao.findBySearch(null);
    }

    public PageInfo<Boardmanage> findPage(Boardmanage search, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<Boardmanage> all = findByCondition(request, search);

        return PageInfo.of(all);
    }

    public List<Boardmanage> findByCondition(HttpServletRequest request, Boardmanage search) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }
		return boardmanageDao.findBySearch(search);
    }
}