package com.briup.cms.web.vm;


import lombok.Data;

/**
 * 接收前端发送的一个JSON字符串，分装成指定的一个对象
 * @author SDX
 * @create 2022-11-02 19:20
 */
@Data
public class CommentVM {
    private Integer id;
    private String content;
    private String time;
    private Integer articleId;
    private Integer parentId;//可选项 如果是评论文章 就没有，如果是回复评论，就要提供参数值

}
