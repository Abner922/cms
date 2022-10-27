package com.briup.cms.service.impl;


import com.briup.cms.bean.Role;
import com.briup.cms.exception.ServiceException;
import com.briup.cms.service.IRoleService;
import com.briup.cms.dao.RoleDao;
import com.briup.cms.utils.Result;
import com.briup.cms.utils.ResultCode;
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
public class RoleServiceImpl implements IRoleService {

    @Autowired//使用jpa实现dao层功能
    private RoleDao dao;

    public Page<Role> findAll(Integer pageNum, Integer pageSize) throws ServiceException {
        //使用dao对象调用方法实现功能
        Page<Role> roles = dao.findAll(PageRequest.of(pageNum, pageSize));
        return roles;
    }

    //新增或修改角色
    public void saveOrUpdateRole(Role role) throws ServiceException {
        //如果提交的角色名为空或空字符串，提示用户参数无效。
        if (role.getName() == null || role.getName().equals("")) {
//            throw new ServiceException(ResultCode.PARAM_IS_INVALID);
        }
        //如果提交角色名与数据库中角色名相同，提示用户数据已存在
        Role role1 = dao.findByName(role.getName());
        if(role1==null){
            dao.save(role);
        }else {
            throw new RuntimeException("数据已存在");
        }
    }

    public void deleteRoleInBatch(List<Integer> ids) throws ServiceException {
        dao.deleteAllById(ids);
    }

    public Role findByRoleName(String roleName) throws ServiceException {
        Role role = dao.findByName(roleName);
        return role;
    }

}
