package com.stock.dao;
import com.stock.bean.SAdmin;
import com.stock.mapper.UserMapper;
import com.stock.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class UserMapperDao {

    SqlSession sqlSession = MybatisUtils.getSqlSession();
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);


    /**
     * 查询账号
     * @param sAdmin
     * @return
     */
    public SAdmin getUserByAccount(SAdmin sAdmin){
        return mapper.getUserByAccount(sAdmin);
    }


    /**
     * 实现添加功能
     * @param sAdmin
     * @return
     */
    public int insertSAdmin(SAdmin sAdmin){
        return mapper.insertSAdmin(sAdmin);
    }

    /**
     * 查询所有部门
     * @return
     */
    public List< Map<String,String>> getDepartAllList(){
        return mapper.getDepartAllList();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public int delUserById(String id){
        return mapper.delUserById(id);
    }

    /**
     * 更改
     * @param map
     * @return
     */
    public int updateAdmin(Map<String,String> map){
        return mapper.updateAdmin(map);
    }

    public List<Map<String,String>>  getUserByName(String id){
        return mapper.getUserByName(id);
    }




}

