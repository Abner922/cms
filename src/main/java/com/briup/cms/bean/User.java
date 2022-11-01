package com.briup.cms.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * jpa 实体类对应的数据库表cms_user
 * @Author SDX
 * @Date 2022/10/25
 */
@Data
@Entity
@Table(name = "cms_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String status;
    private String phone;
    private String realname;
    private String gender;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")//指定字符串转换为时间对象字符串格式
    private Date birthday;
    @Column(name = "register_time")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date registerTime;
    private String image;
    /*
      角色 与 用户 的关系 1： N
        设置一个外键表示role_id
     */
    @ManyToOne //默认效果： 自动生成外键名role_id 选择Role类中主键id
    private Role role;
}
