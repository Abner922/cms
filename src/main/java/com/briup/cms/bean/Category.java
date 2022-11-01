package com.briup.cms.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.List;

/**
 * 目录类型  一级目录 二级目录 ....
 * 上级目录  父目录  子目录 下级目录
 * 特点： 自关联的特点 ：将对应关系的注解信息定义在同一张表中。
 * @Author SDX
 * @Date 2022/10/31
 */
@Getter
@Setter
@Entity
@Table(name = "cms_category")
public class Category {
    @Id //pk约束
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false) //nn 约束
    private String name;
    private String description;
    private Integer no;
    /*
        如何实现外键约束？
        1. 1:1  teacher  student
        2. 1:N  teacher   cars
        3. 目录信息： 外键父级id
            1:N
            N:1
            1:1 1个目录只能一个父目录
     */
    //@OneToOne
    //@OneToMany
    //@ManyToOne
    //private Category parent_id;

    @OneToMany(mappedBy = "parent")
    private List<Category> children;//1对多

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="parent_id")
    @JsonIgnore //转换json字符串时，不进行序列化
    private Category parent;
}
