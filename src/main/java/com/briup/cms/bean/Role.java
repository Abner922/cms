package com.briup.cms.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * jpa 实体类对应的数据库表cms_role
 * @Author SDX
 * @Date 2022/10/25
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


    //当删除角色时，对应的外键值信息设置为null
    @OneToMany
    @JoinColumn(name = "role_id")
    @JsonIgnore// 转换为json字符串时，忽略掉不需要的属性
    private List<User> users;
}
