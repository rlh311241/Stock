package com.stock.view;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.stock.util.Mysqld;
public class Other {
	public static int rsIsNull(ResultSet rs) {
		int a=0;
		try {
			while(rs.next()) {
				a++;
				return a;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}
	//
	public static String[] setCom(String sql,JComboBox com,int i,String stock[]) {
		
		
		ResultSet rs = Mysqld.QueryData(sql, null);
		try {
			com.removeAllItems();
			int a=0;
			
			while(rs.next()) {
				a++;
				com.addItem(rs.getString(i)+rs.getString(i+1));
			}
		
			rs.close();
			rs = Mysqld.QueryData(sql, null);
			stock=new String[a];
			int j=0;
			while(rs.next()) {
				
				stock[j]=rs.getString(i);
				j++;
				
			}
			rs.close();
			return stock;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stock; 
		
	}

}
