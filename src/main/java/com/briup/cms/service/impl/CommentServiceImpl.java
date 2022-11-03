package com.briup.cms.service.impl;


import com.briup.cms.bean.Comment;
import com.briup.cms.bean.User;
import com.briup.cms.dao.CommentDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.ICommentService;
import com.briup.cms.utils.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sun.management.Agent;

import java.util.List;

/**
 * @author SDX
 * @create 2022-10-28 15:41
 */
@Service

public class CommentServiceImpl implements ICommentService {
    @Autowired
    private CommentDao dao;
    @Override
    public Page<Comment> findAll(Integer pageNum, Integer pageSize) throws ServiceException {
        //如果通过返回的结果中定义数据，需要大家编写对应的HQL语句
        Page<Comment> page = dao.findAll(PageRequest.of(pageNum - 1, pageSize));
        return page;
    }

    @Override
    public void saveOrUpdateComment(Comment comment) throws ServiceException {

        //无论新增或修改都需要提供评论用户的信息
        Integer userId = UserInfoUtil.getUserId();
        comment.setUser(User.builder().id(userId).build());
        dao.save(comment);
    }

    @Override
    public void deleteCommentInBatch(List<Integer> ids) throws ServiceException {
        /*
            问题：删除某个评论信息的时候，如果评论id值作为其他数据的外键值时，提示用户外键约束，无法删除的SQL错误
            解决方案：1.删除id=1的评论信息，将其他使用该id的数据信息也一起删除
                     2.删除id=1的评论信息，将其他使用该id的数据信息这只为null
                     3.提示用户该评论下有使用该评论的数据，无法删除

            代码实现：
            当使用@OneToMany+@JoinColum时
         */
        //遍历每个id，执行删除方法
//        ids.forEach(id->dao.deleteById(id));
        //如果执行批量删除操作，外键约束导致无法删除评论信息
        ids.forEach(id->{
            //取消外键约束
            dao.updateParentId(id);
        });

        dao.deleteAllByIdInBatch(ids);
    }

    @Override
    public Page<Comment> findAllByArticleId(Integer articleId, Integer pageNum, Integer pageSize) throws ServiceException {
        return null;
    }
}
