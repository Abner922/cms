package com.briup.cms.service.impl;


import com.briup.cms.bean.User;
import com.briup.cms.dao.LoginDao;
import com.briup.cms.dao.UserDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author SDX
 * @create 2022-10-26 0:24
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao dao;
    @Override
    public Page<User> getAll(Integer pageNum, Integer pageSize) throws ServiceException {
        Page<User> userPage = dao.findAll(PageRequest.of(pageNum-1, pageSize));
        return userPage;
    }

    @Override
    public void saveOrUpdateUser(User user) throws ServiceException {
        dao.save(user);
    }

    @Override
    public void deleteUserInBatch(List<Integer> ids) throws ServiceException {
        dao.deleteAllByIdInBatch(ids);
    }

    @Override
    public void updateUserStatus(Integer id, String status) throws ServiceException {
        //自定义SQL语句实现更新状态

    }

    @Override
    public String login(String username, String password) throws ServiceException {

        //1.方法执行成功，返回需要的token字符串

        //2.方法失败，通过异常对象表示失败的原因，返回给web层
        return null;
    }

    @Override
    public User findUserByUsername(String username) throws ServiceException {
        User userByName = dao.findByUsername(username);
        return userByName;
    }
}
