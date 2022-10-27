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
        //jpa 中 默认第一页是0开始
        Page<Role> page = dao.findAll(PageRequest.of(pageNum - 1, pageSize));
        return page;
    }

    //新增或修改角色
    public void saveOrUpdateRole(Role role) throws ServiceException {
        //如果提交的角色名为空或空字符串，提示用户参数无效。
        if (role.getName() == null || role.getName().equals("")) {
            throw new ServiceException(ResultCode.PARAM_IS_INVALID);
        }
        //2.如果提交角色名与数据库中角色名相同，提示用户数据已存在
        Role role1 = dao.findByName(role.getName());
//        if(role1==null){
//            dao.save(role);
//        }else {
//            throw new RuntimeException("数据已存在");
//        }

        //判断名字是否重复时，如果是修改的话，要求自己和自己的名字重复
        //新增时，判断查询到的用户不为null；在比较name
        if (role1 != null && role1.getId() != role.getId() && role1.getName().equals(role.getName())) {
            throw new ServiceException(ResultCode.DATA_EXISTED);
        }
        dao.save(role);
    }

    public void deleteRoleInBatch(List<Integer> ids) throws ServiceException {
        //当执行该方法，不需要是否存在删除ID
        dao.deleteAllById(ids);
        //当执行该方法，需要先进行逻辑判断是否存在删除id 否则用户提示错误
//        dao.deleteAllById(ids);
    }

    public Role findByRoleName(String roleName) throws ServiceException {
        Role role = dao.findByName(roleName);
        return role;
    }

}
