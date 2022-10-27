package com.briup.cms.web.controller;


import com.briup.cms.bean.Role;
import com.briup.cms.bean.User;
import com.briup.cms.service.IUserService;
import com.briup.cms.service.impl.UserServiceImpl;
import com.briup.cms.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

/**
 * @author SDX
 * @create 2022-10-26 14:14
 */
@Api(tags = "登录模块")
@RestController// 前后端分离
@RequestMapping
public class LoginController {

    @Autowired
    private IUserService service;

    @ApiOperation("用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @PostMapping(value = "/login",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Result Login(String username, String password){
        String token = service.login(username, password);
        return Result.success(token);
    }
}
