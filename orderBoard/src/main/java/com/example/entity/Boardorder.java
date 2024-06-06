package com.example.entity;

import cn.hutool.core.annotation.Alias;
import javax.persistence.*;
import java.util.List;

/**
 * BoardOrder
 */
@Table(name = "boardorder")
public class Boardorder  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	/** code */
	@Transient
	private String code;
	/** name */
	@Transient
	private String name;
	/** phone */
	@Column(name = "phone")
	private String phone;
	/** time */
	@Column(name = "time")
	private String time;
	/** numOfDiners */
	@Column(name = "numofdiners")
	private String numofdiners;
	/** userId */
	@Column(name = "userId")
	private Integer userId;
	/** boardmanageId */
	@Column(name = "boardmanageId")
	private Integer boardmanageId;
	@Column(name = "operatetime")
	private String operatetime;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getNumofdiners() {
		return numofdiners;
	}
	public void setNumofdiners(String numofdiners) {
		this.numofdiners = numofdiners;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getBoardmanageId() {
		return boardmanageId;
	}
	public void setBoardmanageId(Integer boardmanageId) {
		this.boardmanageId = boardmanageId;
	}

	public String getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(String operatetime) {
		this.operatetime = operatetime;
	}
}
