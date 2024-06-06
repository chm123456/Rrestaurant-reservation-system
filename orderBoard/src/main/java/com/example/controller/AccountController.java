package com.example.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.ResultCode;
import com.example.entity.*;
import com.example.service.*;
import com.example.exception.CustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.*;

/**
 *  System user operations related interfaces
 */
@RestController
public class AccountController {

	@Resource
	private GlyService glyService;
	@Resource
	private UserService userService;

	@Resource
    private RolePermissionRelService rolePermissionRelService;
	@Resource
    private PermissionService permissionService;

    /**
     * User login interface
     */

    @PostMapping("/login")
    public Result login(@RequestBody Account account, HttpServletRequest request) {
        Integer role = account.getRole();
        Account loginUser = new Account();
        // Check the role and perform respective login operation
        if (1 == role) {
            // Role 1 is for glyService login
            loginUser = glyService.login(account.getYhm(), account.getMm());
        }
        if (2 == role) {
            // Role 1 is for glyService login
            loginUser = userService.login(account.getYhm(), account.getMm());
        }
        // Set the logged-in user in the session
        request.getSession().setAttribute("user", loginUser);
        // Return a success result with the logged-in user
        return Result.success(loginUser);
    }

    /**
     * User registration interface
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        // Extract the role from the received 'Account' object
        Integer role = account.getRole();
        // Create a new 'Account' object for login
        Account login = new Account();
        if (1 == role) {
            // If the role is '1', create a 'Gly' object and copy properties from 'account' to 'info'
            Gly info = new Gly();
            BeanUtils.copyProperties(account, info);
            // Add the 'Gly' object to the 'glyService' and assign the returned object to 'login'
            login = glyService.add(info);
        }
        // Check if the role is '2'
        if (2 == role) {
            // If the role is '2', create a 'User' object and copy properties from 'account' to 'info'
            User info = new User();
            BeanUtils.copyProperties(account, info);
            // Add the 'User' object to the 'userService' and assign the returned object to 'login'
            login = userService.add(info);
        }
        // Return a 'Result' object containing the 'login' object
        return Result.success(login);
    }

    /**
     * User Logout interface
     */
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        return Result.success();
    }

    /**
     * Interface for determining login status
     */
    @GetMapping("/auth")
    public Result getAuth(HttpServletRequest request) {
        Object user = request.getSession().getAttribute("user");
        if(user == null) {
            return Result.error("401", "未登录");
        }
        return Result.success(user);
    }

    /**
     * Obtain information about the current login user
     */
    @GetMapping("/getUser")
    public Result getAccountInfo(HttpServletRequest request) {
        Account loginUser = (Account) request.getSession().getAttribute("user");
        if (loginUser == null) {
            return Result.success(new Object());
        }
        Integer role = loginUser.getRole();
		if (1 == role) {
			return Result.success(glyService.findById(loginUser.getId()));
		}
		if (2 == role) {
			return Result.success(userService.findById(loginUser.getId()));
		}

        return Result.success(new Object());
    }

    /**
    * Gets the current user's menu permissions and operation permissions
    */
    @GetMapping("/authority/{modelId}")
    public Result getAuthorityInfo(@PathVariable Integer modelId, HttpServletRequest request) {
        Account user = (Account) request.getSession().getAttribute("user");
        if (user == null) {
            return Result.success(new ArrayList<>());
        }
        Map<String, Object> map = new HashMap<>();
        List<Integer> authorityList = new ArrayList<>();
        List<Integer> permissionList = new ArrayList<>();
        // 1. 从用户权限中间表，获取到该用户角色的所有权限ID
        List<RolePermissionRel> relList = rolePermissionRelService.findByRole(user.getRole());
        // 2. 根据权限ID，从权限表中查询所有权限，一级和二级菜单封装到authority里，控件权限封装到permission里，返回给前端
        for (RolePermissionRel rolePermissionRel : relList) {
            // 根据permissionId，从权限表里查出对应的权限
            Permission permission = permissionService.findById(rolePermissionRel.getPermissionId());
            if ("3".equals(permission.getType()) && modelId.equals(permission.getpId())) {
                // type = 3 表示控件权限，加到permission里
                permissionList.add(permission.getId());
            } else {
                // type = 1 或 2 表示一级和二级菜单
                authorityList.add(permission.getId());
            }
        }
        // 找到该模块的父菜单的id，用于控制父菜单是否open
        Permission permission = permissionService.findById(modelId);
        Permission pPermission = permissionService.findById(permission.getpId());
        map.put("authority", authorityList);
        map.put("permission", permissionList);
        map.put("openId", pPermission.getId());
        return Result.success(map);
    }

    /**
    * Update password interface
    */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account info, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("user");
        if (account == null) {
            return Result.error(ResultCode.USER_NOT_EXIST_ERROR.code, ResultCode.USER_NOT_EXIST_ERROR.msg);
        }
        String oldPassword = info.getMm();
        if (!oldPassword.equals(account.getMm())) {
            return Result.error(ResultCode.PARAM_PASSWORD_ERROR.code, ResultCode.PARAM_PASSWORD_ERROR.msg);
        }
        Integer role = account.getRole();
		if (1 == role) {
			Gly gly = new Gly();
			BeanUtils.copyProperties(account, gly);
			gly.setMm(info.getNmm());
			glyService.update(gly);
		}
		if (2 == role) {
			User user = new User();
			BeanUtils.copyProperties(account, user);
			user.setMm(info.getNmm());
			userService.update(user);
		}

        // 清空session，让用户重新登录
        request.getSession().setAttribute("user", null);
        return Result.success();
    }

    /**
     * Password reset interface
     */
    @PostMapping("/resetPassword")
    public Result resetPassword(@RequestBody Account account) {
        Integer role = account.getRole();
		if (1 == role) {
			Gly info = glyService.findByYhm(account.getYhm());
			if (info == null) {
				return Result.error(ResultCode.USER_NOT_EXIST_ERROR);
			}
			info.setMm("123456");
			glyService.update(info);
		}
		if (2 == role) {
			User info = userService.findByYhm(account.getYhm());
			if (info == null) {
				return Result.error(ResultCode.USER_NOT_EXIST_ERROR);
			}
			info.setMm("123456");
			userService.update(info);
		}

        return Result.success();
    }
}
