package com.briup.cms.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinition;
import javax.persistence.*;
import java.util.Date;

/**
 * 实体类 对应cms_user
 *
 * @author SDX
 * @create 2022-10-26 0:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cms_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //补充剩下的列 注意1:1 1:N
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String phone;

    @Column(name = "realname")
    private String realName;

    private String gender;

    private Date birthday;

    @Column(name = "register_time")
    private Date registerTime;

    @Column(nullable = false)
    private String status;

    private String image;
/*
角色 与 用户的关系 1：N
设置一个外键
 */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

}
