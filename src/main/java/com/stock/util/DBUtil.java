package com.stock.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;





public class DBUtil {

	//定义一个可以调用连接
	public static Connection con=null;
	
	
	//连接数据库
	//账号  密码  数据库 名字 
	public DBUtil(String account,String password,String datbasName) {//数据库的账号，数据库的密码 ，数据库的名字
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("加载驱动成功");

			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("加载驱动失败");
		
			e.printStackTrace();
		}
		//jdbc:sqlserver://localhost:1433;databasename=xd
		String url="jdbc:sqlserver://localhost:1433;databasename="+datbasName;
		
		//连接数据库
		try {
			con=DriverManager.getConnection(url,account, password);
			
			System.out.println("连接数据库成功");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("连接数据库失败");
		
		}
		
		
		
	}
	
}
