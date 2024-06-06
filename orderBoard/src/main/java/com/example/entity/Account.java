package com.example.entity;

import javax.persistence.*;

/**
 * Role User parent class
 */
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /** name */
    @Column(name = "yhm")
    private String yhm;
    /** password */
    @Column(name = "mm")
    private String mm;
    /** Role representation */
    @Column(name = "role")
    private Integer role;
    /** New password */
    @Transient
    private String nmm;
    /** avatar */
    @Column(name = "tx")
    private String tx;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getNmm() {
        return nmm;
    }

    public void setNmm(String nmm) {
        this.nmm = nmm;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }
}
