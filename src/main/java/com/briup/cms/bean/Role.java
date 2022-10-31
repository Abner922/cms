package com.briup.cms.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * 实体类 对应cms_role
 * @author SDX
 * @create 2022-10-26 0:10
 */
@Data
@Entity
@Table(name = "cms_role")
@JsonIgnoreProperties({"users"})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;

    //(mappedBy = , fetch = , cascade = )
    @OneToMany
    @JoinColumn(name = "role_id")

    //解决栈溢出方式一
    //@JsonIgnore // 转换为JSON字符串时，忽略掉不需要的属性
    private List<User> users;
}
