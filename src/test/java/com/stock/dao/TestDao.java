package com.stock.dao;




import com.stock.bean.User;
import com.stock.util.MybatisUtils;
import com.stock.view.Manage;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestDao {


    @Test
    public void testInsert() {
        Manage window=new Manage();
        window.frame.setVisible(true);
    }
}

