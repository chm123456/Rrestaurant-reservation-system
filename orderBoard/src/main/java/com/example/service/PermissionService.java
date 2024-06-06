package com.example.service;

import com.example.dao.PermissionDao;
import com.example.entity.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PermissionService {

    @Resource
    private PermissionDao permissionDao;

    /**
     * 根据id查询权限信息
     */
    public Permission findById(Integer id) {
        return permissionDao.selectByPrimaryKey(id);
    }

}
