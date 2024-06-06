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
public class GlyService {

    @Resource
    private GlyDao glyDao;

    public Gly add(Gly gly) {
        // 唯一校验
        Gly info = glyDao.findByYhm(gly.getYhm());
        if (ObjectUtil.isNotEmpty(info)) {
            throw new CustomException(ResultCode.USER_EXIST_ERROR);
        }
        if (StringUtils.isEmpty(gly.getMm())) {
            // 默认密码123456
            gly.setMm("123456");
        }
        if (StringUtils.isEmpty(gly.getTx())) {
            gly.setTx("http://localhost:8080/files/qy-default.png");
        }
        glyDao.insertSelective(gly);
        return gly;
    }

    public void delete(Integer id) {
        glyDao.deleteByPrimaryKey(id);
    }

    public void update(Gly gly) {
        glyDao.updateByPrimaryKeySelective(gly);
    }

    public Gly findById(Integer id) {
        return glyDao.selectByPrimaryKey(id);
    }

    public List<Gly> findAll() {
        return glyDao.findBySearch(null);
    }

    public PageInfo<Gly> findPage(Gly search, Integer pageNum, Integer pageSize, HttpServletRequest request) {
        PageHelper.startPage(pageNum, pageSize);
        List<Gly> all = findByCondition(request, search);

        return PageInfo.of(all);
    }

    public List<Gly> findByCondition(HttpServletRequest request, Gly search) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (ObjectUtil.isEmpty(user)) {
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }
		return glyDao.findBySearch(search);
    }

    public Gly findByYhm(String yhm) {
        return glyDao.findByYhm(yhm);
    }

    public Gly login(String yhm, String mm) {
        Gly gly = glyDao.findByYhm(yhm);
        if (gly == null) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        if (!mm.equalsIgnoreCase(gly.getMm())) {
            throw new CustomException(ResultCode.USER_ACCOUNT_ERROR);
        }
        return gly;
    }

}
