package com.stock.mapper;
import com.stock.bean.SAdmin;
import com.stock.bean.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {


    /**
     * 查询用户
     * @param sAdmin
     * @return
     */
    SAdmin getUserByAccount(SAdmin sAdmin);


    int insertSAdmin(SAdmin sAdmin);


    /**
     * 查询部门
     * @return
     */
    List< Map<String,String>>  getDepartAllList();


    int delUserById(String id);

    int updateAdmin(Map<String,String> map);


    List<Map<String,String>>  getUserByName(String id);



}

