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
public class BoardorderService {

    @Resource
    private BoardorderDao boardorderDao;

    public Boardorder add(Boardorder boardorder) {
        boardorderDao.insertSelective(boardorder);
        return boardorder;
    }

    public void delete(Integer id) {
        boardorderDao.deleteByPrimaryKey(id);
    }

    public void update(Boardorder boardorder) {
        boardorderDao.updateByPrimaryKeySelective(boardorder);
    }

    public Boardorder findById(Integer id) {
        return boardorderDao.selectByPrimaryKey(id);
    }

    public List<Boardorder> findAll() {
        return boardorderDao.findBySearchCondition(null, null, null);
    }

    public PageInfo<Boardorder> findPage(Boardorder search, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<Boardorder> all = findByCondition(request, search);

        return PageInfo.of(all);
    }

    public List<Boardorder> findByCondition(HttpServletRequest request, Boardorder search) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }
		if (2 == user.getRole()) {
			return boardorderDao.findBySearchCondition(search, user.getId(), user.getRole());
		}
		return boardorderDao.findBySearchCondition(search, null, null);

    }
}