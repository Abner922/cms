package com.briup.cms.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 评论类 自关联  先实现用户模块 实现咨询模块
 * @Author SDX
 * @Date 2022/10/31
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cms_comment")
public class Comment {
    //基本信息

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String time;
    //上级评论

    @ManyToOne // 父评论
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private Comment parent;

    /*
     * @OneToMany
     *  1.使用双向 注解 表示实体关系
     *  如果不使用 mappedBy 导致生成一张中间表，无需要使用中间表
     *  2. 使用双向注解 并指定了mappedBy 可以实现外键约束，不会生成中间表
     *  3. 使用双向注解。单独只使用 @JoinColumn
     *    注意：mappedBy 不能与 @JoinColumn
     *    每次只选择期中一个
     *
     * @ManyToOne : 使用自动生成的外键名
     */
//    @OneToMany(mappedBy = "parent") // 子评论
    @OneToMany
    @JoinColumn(name = "parent_id")
    private List<Comment> children;
    //用户信息 user_id
    @ManyToOne//多个评论对应一个用户
    @JsonIgnore
    private User user;
    //文章信息 article_id
    @ManyToOne //多个评论对应一个文章
    @JsonIgnore
    private Article article;

}
