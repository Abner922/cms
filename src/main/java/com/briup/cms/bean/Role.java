package com.briup.cms.bean;


import lombok.Data;

import javax.persistence.*;

/**
 * 实体类 对应cms_role
 * @author SDX
 * @create 2022-10-26 0:10
 */
@Data
@Entity
@Table(name = "cms_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    private String description;
}
