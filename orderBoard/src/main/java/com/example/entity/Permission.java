package com.example.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 权限实体
 */
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 权限名称 */
	@Column(name = "name")
	private String name;
	/** 权限类型 */
	@Column(name = "type")
	private String type;
	/** 父级id */
	@Column(name = "pId")
	private Integer pId;

	@Transient
	private List<Permission> list;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public List<Permission> getList() {
		return list;
	}

	public void setList(List<Permission> list) {
		this.list = list;
	}
}
