package com.example.entity;

import cn.hutool.core.annotation.Alias;
import javax.persistence.*;
import java.util.List;

/**
 * BoardManage
 */
@Table(name = "boardmanage")
public class Boardmanage  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	/** boardCode */
	@Column(name = "boardcode")
	private String boardcode;
	/** boardName */
	@Column(name = "boardname")
	private String boardname;
	/** boardType */
	@Column(name = "boardtype")
	private String boardtype;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBoardcode() {
		return boardcode;
	}
	public void setBoardcode(String boardcode) {
		this.boardcode = boardcode;
	}
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}
	public String getBoardtype() {
		return boardtype;
	}
	public void setBoardtype(String boardtype) {
		this.boardtype = boardtype;
	}


}
