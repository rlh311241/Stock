package com.stock.dao;

import com.stock.mapper.AdminMapper;
import com.stock.mapper.CommodityMapper;
import com.stock.util.MybatisUtils;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;
import java.util.List;

public class AdminMapperDao {

    AdminMapper mapper = MybatisUtils.sqlSession.getMapper(AdminMapper.class);


    public List<LinkedHashMap<String,Object>> getAllAdmins(){
        return mapper.getAllAdminsAc();
    }

    public List<LinkedHashMap<String,Object>> getAllAdmins(String sAccount){
        return mapper.getAllAdmins(sAccount);
    }

    /**
     * 获取所有字段
     * @param sAccount
     * @return
     */
    public List<LinkedHashMap<String,Object>> getAllAdminsA(String sAccount){
        return mapper.getAllAdminsA(sAccount);
    }

    public int insertStock(@Param("cId") String cId, @Param("cName") String cName){

        try {
            return  mapper.insertStockA(cId,cName);
        }catch (Exception e){
            return 0;
        }

    }

   public int delStock(@Param("cId") String cId){
        return mapper.insertStock(cId);
    }

    public int updateStock(@Param("cId") String cId, @Param("cName") String cName,@Param("cIda") String cIda){
        return  mapper.updateStock(cId,cName,cIda);
    }

    public LinkedHashMap<String,Object> getStock(@Param("cId") String cId){
        return mapper.getStock(cId);
    }
}
