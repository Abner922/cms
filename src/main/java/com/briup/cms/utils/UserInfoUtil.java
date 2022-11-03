package com.briup.cms.utils;


import java.util.Map;

/**
 * @author SDX
 * @create 2022-11-02 14:36
 */

public class UserInfoUtil {
    //每个线程（每个用户发送的请求）可以拥有一个副本存储自己的用户信息
    private static ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<>();

    public static void setUserInfo(Map<String,Object> map){
        threadLocal.set(map);
    }

    public static Integer getUserId(){
        return (Integer) threadLocal.get().get("userId");
    }
}
