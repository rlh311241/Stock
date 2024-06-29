package com.stock.dao;

import com.stock.bean.SDepart;
import com.stock.mapper.DepartMapperMapper;
import com.stock.mapper.UserMapper;
import com.stock.util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DepartMapperDao {

    SqlSession sqlSession = MybatisUtils.getSqlSession();
    DepartMapperMapper mapper = sqlSession.getMapper(DepartMapperMapper.class);


    List<SDepart> findAllDepart(){
        return mapper.findAllDepart();
    }


    SDepart findById(int id){
        return mapper.findById(id);
    }

   public  int insertDepart(String id){
        return insertDepart(id);
    }

    public int updateDepart(SDepart depart){
        return mapper.updateDepart(depart);
    }

   public  int deleteById(String id){
        return mapper.deleteById(id);
   }

   public int insert(String depart){
        return mapper.insertDepart(depart);
   }

}
