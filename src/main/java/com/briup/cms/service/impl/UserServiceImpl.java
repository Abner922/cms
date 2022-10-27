package com.briup.cms.service.impl;


import com.briup.cms.bean.User;
import com.briup.cms.config.CmsInfo;
import com.briup.cms.dao.LoginDao;
import com.briup.cms.dao.UserDao;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.IUserService;
import com.briup.cms.utils.JwtUtil;
import com.briup.cms.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SDX
 * @create 2022-10-26 0:24
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDao dao;//获取spring自动创建的到层对象

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
        //2.方法失败，通过异常对象表示失败的原因，返回给web层
        User user = dao.findByUsername(username);
        if (user == null) {//表示用户不存在
            //为了防止暴力破解，提供统一的信息
            throw new ServiceException(ResultCode.USER_LOGIN_ERROR);
        }
        if (!password.equals(user.getPassword())) {
            throw new ServiceException(ResultCode.USER_LOGIN_ERROR);
        }

        //账号状态问题
        if (CmsInfo.USER_STATUS_NO.equals(user.getStatus())) {
            throw new ServiceException(ResultCode.USER_ACCOUNT_FORBIDDEN);
        }
        //1.方法执行成功，返回需要的token字符串
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("userId",user.getId());
        //put....根据其他实际情况进行设置
        String token = JwtUtil.sign(username, userInfo);
        return token;
    }

    @Override
    public User findUserByUsername(String username) throws ServiceException {
        User userByName = dao.findByUsername(username);
        return userByName;
    }
}
