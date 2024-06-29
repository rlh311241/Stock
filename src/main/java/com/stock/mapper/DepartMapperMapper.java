package com.stock.mapper;

import com.stock.bean.SDepart;

import java.util.List;


public interface DepartMapperMapper {




    List<SDepart>findAllDepart();

    SDepart findById(String id);

    int insertDepart(String id);

    int updateDepart(SDepart depart);

    int deleteById(String id);

}

