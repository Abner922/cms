package com.briup.cms.dao;


import com.briup.cms.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * @author SDX
 * @create 2022-10-26 21:40
 */

public interface UserDao extends JpaRepository<User, Integer> {
    //根据用户名查询用户信息
    User findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update User set status = ?2 where id = ?1")
    void updateStatusById(Integer id, String status);
}
