package com.stock.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.LinkedHashMap;

public interface CommodityMapper {



    @Select("select * from s_commodity")
    List<LinkedHashMap<String, String>> selectAllCommodity();

    @Select("select * from s_stock")
    List<LinkedHashMap<String, String>> selectAllStock();

    @Select("select sum(a_number) a from s_rcode where p_id=#{pId} and c_id=#{cId} and a_type='入库' group by p_id")
    List<LinkedHashMap<String, Object>> getStock(@Param("pId") String pId, @Param("cId") String cId);


    @Select("SELECT  ISNULL((select sum(a_number)  from s_rcode where p_id=#{pId} and c_id=#{cId} and a_type='出库' group by p_id) , 0) a")
    List<LinkedHashMap<String, Object>> getStockOne(@Param("pId") String pId, @Param("cId") String cId);


    @Insert("insert into s_rcode (p_id,c_id,a_number,a_type) VALUES(#{pId},#{cId},#{aNumber},#{aType})")
    int addRecord(@Param("pId") String pId,@Param("cId") String cId,@Param("aNumber") String aNumber,@Param("aType") String aType);



    @Insert("insert into s_lrcode(p_id,s_account,a_number) VALUES(#{pId},#{sAccount},#{aNumber})")
    int getWl(@Param("pId") String pId,@Param("sAccount") String sAccount,@Param("aNumber") String aNumber);


    @Select("ShowCount #{one},#{two}")
    List<LinkedHashMap<String, String>> getCount(@Param("one") String one,@Param("two") String two);





    @Select("select s_commodity.p_name, s_commodity.p_class,s_lrcode.a_number, s_lrcode.a_date  from s_lrcode,s_commodity where s_lrcode.p_id=s_commodity.p_id and s_lrcode.s_account=#{sAccount}")
    List<LinkedHashMap<String, Object>> getCommodity(@Param("sAccount") String sAccount);


}
