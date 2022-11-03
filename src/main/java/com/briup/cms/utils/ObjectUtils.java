package com.briup.cms.utils;


import org.springframework.beans.BeanUtils;

import java.lang.annotation.Target;

/**
 * @author SDX
 * @create 2022-11-02 15:24
 */

public class ObjectUtils {
    private ObjectUtils(){}

    /**
     *  将vm对象转化为bean对象
     * @param vm 前端接收到的参数的封装对象
     * @param c service中需要的bean类的字节码对象
     * @param <T>
     * @return  service中需要的bean对象
     */
    public static <T> T vm2Bean(Object vm, Class<T> c){
        //1.创建出service层中需要的bean对象
        T target = null;
        try {
            target = c.newInstance();
            //2.将vm对象中的属性值赋值给target对象
            BeanUtils.copyProperties(vm,target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.返回我们需要的bean对象
        return target;
    }
}
