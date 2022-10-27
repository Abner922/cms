package com.briup.cms.dao;


import com.briup.cms.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author SDX
 * @create 2022-10-26 14:25
 */

public interface LoginDao extends JpaRepository<User,Integer> {

}
