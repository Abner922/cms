package com.briup.cms.web.controller;


import com.briup.cms.bean.Article;
import com.briup.cms.bean.Comment;
import com.briup.cms.service.ICommentService;
import com.briup.cms.utils.ObjectUtils;
import com.briup.cms.utils.Result;
import com.briup.cms.web.vm.CommentVM;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author SDX
 * @create 2022-10-28 15:41
 */

@Api(tags = "评论模块")
@RestController
@RequestMapping("/auth/comment")
public class CommentController {
    @Autowired
    private ICommentService service;
    @ApiOperation("新增评论")
    @PostMapping
    public Result addComment(@RequestBody CommentVM vm){
        //1.将vm对象转化为bean对象 只复制了内容和时间属性
        Comment comment = ObjectUtils.vm2Bean(vm, Comment.class);
        //复制评论文章id
//        Article article = new Article();
//        article.setId(vm.getArticleId());
//        comment.setArticle(article);
        //简化上述代码
        Article article = Article.builder().id(vm.getArticleId()).build();
        comment.setArticle(article);
        //复制上级id
//        Comment parent = new Comment();
//        parent.setId(vm.getParentId());
//        comment.setParent(parent);
        if(vm.getParentId() != null){
            Comment parent = Comment.builder().id(vm.getParentId()).build();
            comment.setParent(parent);
        }
        service.saveOrUpdateComment(comment);
        return Result.success();
    }

    @ApiOperation("修改评论")
    @PutMapping
    public Result updateComment(@RequestBody CommentVM vm){
        Comment comment = ObjectUtils.vm2Bean(vm, Comment.class);
        Article article = Article.builder().id(vm.getArticleId()).build();
        comment.setArticle(article);
        if(vm.getParentId() != null){
            Comment parent = Comment.builder().id(vm.getParentId()).build();
            comment.setParent(parent);
        }
        service.saveOrUpdateComment(comment);
        return Result.success();
    }

    @ApiOperation(value = "批量删除评论", notes = "删除效果：只删除该评论信息，对应的评论信息不会被删除，取消parent_id的值")
    @DeleteMapping
    public Result deleteBybatch(@RequestBody List<Integer> ids){
        service.deleteCommentInBatch(ids);
        return Result.success();
    }
    @ApiOperation(value = "分页查询评论信息")
    @GetMapping
    public Result findByPage(Integer pageNum, Integer pageSize){
        Page<Comment> page = service.findAll(pageNum, pageSize);
        return Result.success(page.getContent());
    }
}
