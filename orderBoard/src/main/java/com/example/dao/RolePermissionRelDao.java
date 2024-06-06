package com.example.dao;

import com.example.entity.RolePermissionRel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RolePermissionRelDao extends Mapper<RolePermissionRel> {


    @Select("select * from role_permission_rel where role = #{role}")
    List<RolePermissionRel> findByRole(@Param("role") Integer role);
}
