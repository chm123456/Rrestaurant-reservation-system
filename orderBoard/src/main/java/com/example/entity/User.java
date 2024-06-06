package com.example.entity;

import cn.hutool.core.annotation.Alias;
import javax.persistence.*;
import java.util.List;

/**
 * user
 */
@Table(name = "user")
public class User extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	/** username */
	@Column(name = "yhm")
	private String yhm;
	/** code */
	@Column(name = "mm")
	private String mm;
	/** avatar */
	@Column(name = "tx")
	private String tx;
	/** sex */
	@Column(name = "sex")
	private String sex;
	/** phone */
	@Column(name = "phone")
	private String phone;

	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getTx() {
		return tx;
	}
	public void setTx(String tx) {
		this.tx = tx;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


}
