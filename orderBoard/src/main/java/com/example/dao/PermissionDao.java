package com.example.dao;

import com.example.entity.Permission;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface PermissionDao extends Mapper<Permission> {

    
}
