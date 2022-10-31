package com.briup.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.briup.cms.bean.Article;
import com.briup.cms.bean.Comment;
import com.briup.cms.exception.ServiceException;

public interface IArticleService {
	//发布资讯或者编辑资讯
	void saveOrUpdateArticle(Article article)throws ServiceException;
	//分页获取当前用户的所有资讯
	Page<Article> findAllByUser(Integer pageNum,Integer pageSize)throws ServiceException;
	//批量删除资讯
	void deleteArticleInBatch(List<Integer> ids)throws ServiceException;
	//管理员审核资讯
	void updateArticleStatus(Integer id,String status)throws ServiceException;
	//根据分类id查找对应的资讯信息，并分页显示
	Page<Article> findAllByCategoryId(Integer articleId,int pageNum,int pageSize)throws ServiceException;
}
