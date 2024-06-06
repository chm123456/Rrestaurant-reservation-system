package com.example.entity;

import javax.persistence.*;

/**
 * 角色权限中间实体
 */
@Table(name = "role_permission_rel")
public class RolePermissionRel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 角色标识 */
	@Column(name = "role")
	private Integer role;
	/** 权限ID */
	@Column(name = "permissionId")
	private Integer permissionId;


	public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return this.id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
