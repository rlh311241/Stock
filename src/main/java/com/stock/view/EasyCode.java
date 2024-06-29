package com.stock.view;

import com.stock.util.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.stock.util.Mysqld;
public class EasyCode {
	


	//删除管理
	public static void deleteDate(JTextField textField[],String sql,int j,String mes) {
		String data[]=new String[textField.length];
		for(int i=0;i<textField.length;i++) {
			data[i]=textField[i].getText();
		}
		
		if(textField[j-1].getText().equals("")) {
			Tools.messageWindows(mes);
		}else {
			int a=Mysqld.upDate(sql, data);
			if(a==1) {
				Tools.messageWindows("删除成功");
			}else {
				Tools.messageWindows("删除失败");
			}
		}
		}
	//查找全部管理
	public static void showAllData(String sql,int rowacount,DefaultTableModel model) {

		ResultSet rs = Mysqld.QueryData(sql, null);
		if(rs==null) {
			Tools.messageWindows("没有数据");
		}else {
			Tools.addDataTable(rs, model, rowacount);
		}
	}
		//
		//查找全部管理
		public static void showOneData(JTextField textField[],JTextField stextField[],String sql,int rowacount,DefaultTableModel model,int adt[]) {
			String data[]=new String[textField.length];
			for(int i=0;i<textField.length;i++) {
				data[i]=textField[i].getText();
			}
			//将数据读取到data[]
			
			ResultSet rs = Mysqld.QueryData(sql, data);
			if(rs==null) {
				Tools.messageWindows("没有数据");
			}else {
				Tools.addDataTable(rs, model, rowacount);
				
				rs = Mysqld.QueryData(sql, data);
				//再次执行
				try {
					if(rs.next()) {
						for(int i=0;i<stextField.length;i++) {
							stextField[i].setText(rs.getString(adt[i]));
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		
		
		
	}
	//数据的更改
	public static void upData(JTextField textField[],String sql,int j,String mes) {
		
		
		String data[]=new String[textField.length];
		for(int i=0;i<textField.length;i++) {
			data[i]=textField[i].getText();
		}
		
		if(textField[j-1].getText().equals("")) {
			Tools.messageWindows(mes);
		}else {
			int a=Mysqld.upDate(sql, data);
			if(a==1) {
				Tools.messageWindows("更改成功");
			}else {
				Tools.messageWindows("更改失败");
			}
		}
		
	}
		
	


}
