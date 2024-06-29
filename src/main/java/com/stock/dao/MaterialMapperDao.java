package com.stock.dao;

import com.stock.mapper.DepartMapperMapper;
import com.stock.mapper.MaterialMapper;
import com.stock.util.MybatisUtils;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

public class MaterialMapperDao {

    MaterialMapper mapper = MybatisUtils.sqlSession.getMapper(MaterialMapper.class);


    /**
     * 实现删除功能
     * @param pId
     * @return
     */
    public int deleteByPId(String pId){
        return  mapper.deleteByPId(pId);
    }

    public int updateCom(@Param("pId") String pId, @Param("pName") String pName, @Param("pIda") String pIda){
        return  mapper.updateCom(pId,pName,pIda);
    }

    public List<LinkedHashMap<String, Object>> findAll(){
        return mapper.findAll();
    }

    public LinkedHashMap<String, Object> findById(@Param("pId") String pId){
        return mapper.findAllById(pId);
    }

    public  int installCom(@Param("pId") String pId, @Param("pName") String pName, @Param("pClass") String pClass){
        return mapper.installCom(pId,pName,pClass);
    }
}
