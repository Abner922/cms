package com.briup.cms.web.controller;


import com.briup.cms.bean.User;
import com.briup.cms.service.impl.UserServiceImpl;
import com.briup.cms.utils.Result;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SDX
 * @create 2022-10-26 14:56
 */


@Api(tags = "用户管理模块")
@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @ApiOperation("添加用户")
    @PostMapping
    public Result addUser(@RequestBody User user){
        service.saveOrUpdateUser(user);
        return Result.success();
    }

    @ApiOperation("编辑用户")
    @PutMapping
    public Result UpdateUser(@RequestBody User user){
        service.saveOrUpdateUser(user);
        return Result.success();
    }

    @ApiOperation("分页查询用户信息")
    @GetMapping
    public Result findByPage(Integer pageNum, Integer pageSize){
        Page<User> all = service.getAll(pageNum, pageSize);
        return Result.success(all);
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public Result deteleByBanth(@RequestParam List<Integer> ids){
        service.deleteUserInBatch(ids);
        return Result.success();
    }

    @ApiOperation("获取当前用户登录信息")
    @GetMapping("/current")
    public Result GetUser(){
//        String username = user.getUsername();
//        User user = service.findUserByUsername(username);
        return Result.success();
    }

    @ApiOperation("禁用或解封用户信息（更新用户状态）")
    @GetMapping("/current")
    public Result GetStatus(@RequestBody Integer id, String status){
        service.updateUserStatus(id, status);
        return Result.success();
    }

}