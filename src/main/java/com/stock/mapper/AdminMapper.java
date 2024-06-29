package com.stock.mapper;

import org.apache.ibatis.annotations.*;

import java.util.LinkedHashMap;
import java.util.List;

public interface AdminMapper {



    @Select("SELECT s_account,s_name,d_depart from s_admin")
    List<LinkedHashMap<String,Object>> getAllAdminsAc();


    @Select("SELECT s_account,s_name,d_depart from s_admin where s_account=#{sAccount}")
    List<LinkedHashMap<String,Object>> getAllAdmins(@Param("sAccount") String sAccount);


    @Select("SELECT *  from s_admin where s_account=#{sAccount}")
    List<LinkedHashMap<String,Object>> getAllAdminsA(@Param("sAccount") String sAccount);


    @Insert("insert into s_stock VALUES(#{cId},#{cName})")
    int insertStockA(@Param("cId") String cId, @Param("cName") String cName);


    @Delete("DELETE FROM s_stock where c_id=#{cId}")
    int insertStock(@Param("cId") String cId);

    @Update("UPDATE s_stock set c_id=#{cId} ,c_name=#{cName} where c_id=#{cIda}")
    int updateStock(@Param("cId") String cId, @Param("cName") String cName,@Param("cIda") String cIda);

    @Select("select * from s_stock where c_id=#{cId}")
    LinkedHashMap<String,Object> getStock(@Param("cId") String cId);
}
