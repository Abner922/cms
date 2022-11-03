package com.briup.cms.web.controller;


import com.briup.cms.bean.Article;
import com.briup.cms.service.IArticleService;
import com.briup.cms.utils.ObjectUtils;
import com.briup.cms.utils.Result;
import com.briup.cms.web.vm.ArticleVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SDX
 * @create 2022-11-01 11:18
 */
@Api(tags = "资讯模块")
@RestController
@RequestMapping("/auth/article")
public class ArticleController {

    @Autowired
    private IArticleService service;

    @ApiOperation("新增资讯")
    @PostMapping
    public Result addArticle(@RequestBody ArticleVM vm){
        //需要将vm对象转化成article对象
        service.saveOrUpdateArticle(ObjectUtils.vm2Bean(vm, Article.class));
        return Result.success();
    }

    @ApiOperation("修改资讯")
    @PutMapping
    public Result updateArticle(@RequestBody Article article){
        service.saveOrUpdateArticle(article);
        return Result.success();
    }

    @ApiOperation(value = "批量删除资讯信息", notes = "删除资讯信息，并且将对应的评论信息一起删除")
    @DeleteMapping
    public Result deleteByBatch(@RequestBody List<Integer> ids){
        service.deleteArticleInBatch(ids);
        return Result.success();
    }

    @ApiOperation("根据用户分页获取资讯信息")
    @GetMapping("/user")
    public Result findByUser(Integer pageSize, Integer pageNum){
        Page<Article> page = service.findAllByUser(pageNum, pageSize);
        return Result.success(page);
    }

}
