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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiImplicitParam()
    })
    @PostMapping("/login")
    public Result Login(String username, String password){
        return Result.success();
    }
}
