package com.briup.cms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 文章
 * @Author SDX
 * @Date 2022/10/31
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cms_article")
public class Article {
    //基本信息
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(name = "publish_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT + 8")
    private Date publishTime;
    private Integer status;
    @Column(name = "read_times")
    private Integer readTimes;
    @Column(name = "thumbUp")
    private Integer thumb_up;
    @Column(name = "thumbDown")
    private Integer thumb_down;
    // 用户信息  fk
    @ManyToOne
    private User user;
    //目录信息  category_id
    @ManyToOne
    @JsonIgnore
    private Category category;

    /*
        为了实现删除资讯时，可以级联删除评论信息，座椅设置了文章类中评论属性
     */

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", category=" + category.getId() +
                '}';
    }
}
