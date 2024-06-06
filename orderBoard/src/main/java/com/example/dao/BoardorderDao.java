package com.example.dao;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BoardorderDao extends Mapper<Boardorder> {

	List<Boardorder> findBySearchCondition(@Param("search") Boardorder search, @Param("userId") Integer userId, @Param("role") Integer role);

}
