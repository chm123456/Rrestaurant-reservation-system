package com.example.dao;

import com.example.entity.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface GlyDao extends Mapper<Gly> {

	@Select("select * from gly where `yhm` = #{name}")
	Gly findByYhm(@Param("name") String yhm);

	List<Gly> findBySearch(@Param("search") Gly search);

}
