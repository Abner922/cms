package com.briup.cms.dao;


import com.briup.cms.bean.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SDX
 * @create 2022-10-28 15:24
 */

public interface ArticleDao extends JpaRepository<Article,Integer> {

    //自定义方法实现根据用户id查询资讯信息

    Page<Article> findByUserId(Integer id, Pageable pageable);
}
