package com.example.dao;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserDao extends Mapper<User> {

	@Select("select * from user where `yhm` = #{name}") 	// Find a user by username
	User findByYhm(@Param("name") String yhm);

	List<User> findBySearch(@Param("search") User search);	// Find users based on search criteria

}

