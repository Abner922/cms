package com.briup.cms.web.controller;


import com.briup.cms.bean.Role;
import com.briup.cms.service.impl.RoleServiceImpl;
import com.briup.cms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 权限管理模块：基于RESTful风格
 * 参考提供的接口文档
 * @author SDX
 * @create 2022-10-26 9:13
 */
@Api(tags = "角色模块")
@RestController
@RequestMapping("/auth/role")
public class RoleController {
    @Autowired
    private RoleServiceImpl service;

    //新增或查询
    @PostMapping
    @ApiOperation("新增或修改")
    public Result addOrUpdateRole(@RequestBody Role role){
        //调用service层的方法
        service.saveOrUpdateRole(role);
        return Result.success();
    }

    //分页查询
    @ApiOperation("分页查询")
    @GetMapping
    public Result findByPage(Integer pageNum, Integer pageSize){
        Page<Role> roles = service.findAll(pageNum, pageSize);
        return Result.success(roles.getContent());
    }

    //批量删除
    //根据接口文档定义的内容，创建对应的方法
    @ApiOperation("批量删除")
    @DeleteMapping
    public Result deleteById(@RequestParam List<Integer> ids){
        service.deleteRoleInBatch(ids);
        return Result.success();
    }

}