package com.stock.dao;


import com.stock.bean.SDepart;
import com.stock.mapper.DepartMapperMapper;
import com.stock.mapper.UserMapper;
import com.stock.util.MybatisUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import com.stock.util.MybatisUtils;
import com.stock.mapper.*;
import java.util.LinkedHashMap;
import java.util.List;


public class CommodityMapperDao {


    CommodityMapper mapper = MybatisUtils.sqlSession.getMapper(CommodityMapper.class);


    /**
     * 查询所有
     * @return
     */
    public List<LinkedHashMap<String,String>> selectAllCommodity(){
        return mapper.selectAllCommodity();
    }

    public List<LinkedHashMap<String,String>> selectAllStock(){
        return mapper.selectAllStock();
    }

    public List<LinkedHashMap<String,Object>> getStock(String pId, String cId){
        return mapper.getStock(pId,cId);
    }
    public List<LinkedHashMap<String,Object>> getStockOne(String pId, String cId){
       return  mapper.getStockOne(pId, cId);
    }

    public int addRecord(String pId, String cId,String aNumber,String aType){
        return mapper.addRecord(pId,cId,aNumber,aType);
    }


    public List<LinkedHashMap<String,String>> getCount(String one,String two){
        return mapper.getCount(one,two);
    }

    public  int getWl(@Param("pId") String pId, @Param("sAccount") String sAccount, @Param("aNumber") String aNumber){

        return mapper.getWl(pId,sAccount,aNumber);
    }



    public List<LinkedHashMap<String, Object>> getCommodity(@Param("sAccount") String sAccount){
        return mapper.getCommodity(sAccount);
    }

}
