package com.briup.cms.dao;


import com.briup.cms.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SDX
 * @create 2022-10-26 21:40
 */

public interface UserDao extends JpaRepository<User, Integer> {
    //根据用户名查询用户信息
    User findByUsername(String username);
}
