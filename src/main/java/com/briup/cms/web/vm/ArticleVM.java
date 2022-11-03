package com.briup.cms.web.vm;


import com.briup.cms.bean.Category;
import lombok.Data;

/**
 * 新增资讯时的参数信息
 * @author SDX
 * @create 2022-11-02 15:19
 */
@Data // jackson 调用ser方法
public class ArticleVM {
    private String title;
    private String content;
    private Category category;
}
