package com.stock.mapper;

import org.apache.ibatis.annotations.*;

import java.util.LinkedHashMap;
import java.util.List;

public interface MaterialMapper {



    @Delete("DELETE FROM s_commodity where p_id=#{pId}")
    int deleteByPId(@Param("pId") String pId);


    @Update("UPDATE s_commodity set p_id=#{pId},p_name=#{pName} where p_id=#{pIda}")
    int updateCom(@Param("pId") String pId,@Param("pName") String pName,@Param("pIda") String pIda);

    @Select("select * from s_commodity ORDER BY p_class")
    List<LinkedHashMap<String, Object>> findAll();


    @Select("select * from s_commodity where p_id=#{pId}")
    LinkedHashMap<String, Object> findAllById(@Param("pId") String pId);

    @Insert("INSERT INTO s_commodity (p_id, p_name, p_class) VALUES (#{pId}, #{pName}, #{pClass})")
    int installCom(@Param("pId")String pId, @Param("pName") String pName, @Param("pClass") String pClass);



    @Select("getPro #{one},#{two}")
    List<LinkedHashMap<String, Object>> getPro(@Param("one") String one, @Param("two") String two);

    @Select("getProP #{one},#{two}")
    List<LinkedHashMap<String, Object>> getProP(@Param("one") String one, @Param("two") String two);

    @Select("getProI #{one},#{two}")
    List<LinkedHashMap<String, Object>> getProI(@Param("one") String one, @Param("two") String two);


}
