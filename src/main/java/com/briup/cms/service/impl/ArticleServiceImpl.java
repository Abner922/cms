package com.briup.cms.service.impl;


import com.briup.cms.bean.Article;
import com.briup.cms.bean.User;
import com.briup.cms.config.CmsInfo;
import com.briup.cms.dao.ArticleDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.IArticleService;
import com.briup.cms.utils.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author SDX
 * @create 2022-10-28 14:50
 */
@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleDao dao;
    @Override
    public void saveOrUpdateArticle(Article article) throws ServiceException {
        //1.使用工具类获取同一个线程中的保存的用户信息
        Integer userId = UserInfoUtil.getUserId();
        System.out.println("发布资讯的用户id" + userId);

        if(article.getId() == null){//新增
            article.setPublishTime(new Date());
            article.setStatus(CmsInfo.ARTICLE_STATUS_INIT);
            article.setReadTimes(0);
            article.setThumb_up(0);
            article.setThumb_down(0);
            User user = new User();
            user.setId(userId);
            article.setUser(user);
        }else{
            //修改时，可以提供验证修改后的数据
        }
        dao.save(article);

    }

    @Override
    public Page<Article> findAllByUser(Integer pageNum, Integer pageSize) throws ServiceException {
        Integer userId = UserInfoUtil.getUserId();
        Page<Article> page = dao.findByUserId(userId, PageRequest.of(pageNum - 1, pageSize));
        return page;
    }

    @Override
    public void deleteArticleInBatch(List<Integer> ids) throws ServiceException {
        /*
            批量删除资讯信息，资讯信息的主键值，被评论信息中作为外键值
         */
//        for(Integer id : ids){
//            dao.deleteById(id);
//        }
        dao.deleteAllById(ids);
//        dao.deleteAllByIdInBatch(ids);
    }

    @Override
    public void updateArticleStatus(Integer id, String status) throws ServiceException {

    }

    @Override
    public Page<Article> findAllByCategoryId(Integer articleId, int pageNum, int pageSize) throws ServiceException {
        return null;
    }
}
