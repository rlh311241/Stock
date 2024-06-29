package com.stock.mapper;
import com.stock.bean.SAdmin;
import com.stock.bean.SDepart;

import java.util.List;
import java.util.Map;

public interface DepartMapperMapper {




    List<SDepart>findAllDepart();

    SDepart findById(int id);

    int insertDepart(String id);

    int updateDepart(SDepart depart);

    int deleteById(String id);

}

