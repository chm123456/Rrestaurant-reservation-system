package com.example.service;

import com.example.dao.RolePermissionRelDao;
import com.example.entity.RolePermissionRel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RolePermissionRelService {

    @Resource
    private RolePermissionRelDao rolePermissionRelDao;

    public List<RolePermissionRel> findByRole(Integer role) {
        return rolePermissionRelDao.findByRole(role);
    }
}
