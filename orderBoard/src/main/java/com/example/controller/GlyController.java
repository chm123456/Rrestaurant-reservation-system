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
*  描述：管理员相关接口
*/
@RestController
@RequestMapping(value = "/gly")
public class GlyController {

    @Resource
    private GlyService glyService;

    /**
     * 描述：新增
     */
    @PostMapping
    public Result add(@RequestBody Gly gly, HttpServletRequest request) {

        glyService.add(gly);
        return Result.success(gly);
    }

    /**
     * 描述：根据ID删除
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        glyService.delete(id);
        return Result.success();
    }

    /**
     * 描述：更新
     */
    @PutMapping
    public Result update(@RequestBody Gly gly) {

        glyService.update(gly);
        return Result.success();
    }

    /**
     * 描述：根据ID查询
     */
    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        Gly gly = glyService.findById(id);
        return Result.success(gly);
    }

    /**
     * 描述：查询所有
     */
    @GetMapping
    public Result all() {
        return Result.success(glyService.findAll());
    }

    /**
     * 描述：分页查询
     */
    @PostMapping("/page")
    public Result page(@RequestBody Gly search,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "5") Integer pageSize,
                       HttpServletRequest request) {
        return Result.success(glyService.findPage(search, pageNum, pageSize, request));
    }




}
