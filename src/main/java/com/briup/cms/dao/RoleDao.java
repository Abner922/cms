package com.briup.cms.dao;


import com.briup.cms.bean.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 使用jpa提供的一个泛型接口实现数据操作
 * 不需要实现该接口，jpa实现
 * 当默认提供的方法无法解决数据库操作sql，需要自定义sql或方法
 * @author SDX
 * @create 2022-10-26 9:06
 */

public interface RoleDao extends JpaRepository<Role,Integer> {
    Page<Role> findAll(Pageable pageable);
    Role findByName(String name);
}