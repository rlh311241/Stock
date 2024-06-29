package com.stock.view;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	/**
	 * 根据输入的序号从Map中获取对应的键值对。
	 *
	 * @param map     输入的Map，建议使用LinkedHashMap以保证遍历顺序与插入顺序一致。
	 * @param index   要获取的键值对的序号，序号从0开始。
	 * @return        序号对应的键值对，如果序号超出范围，则返回null。
	 */
	public static Map.Entry<String, String> getEntryByIndex(Map<String, String> map, int index) {


		List<Map.Entry<String, String>> entries = new ArrayList<>(map.entrySet());

		if (index >= 0 && index < entries.size()) {
			return entries.get(index);
		} else {
			System.out.println("序号超出Map的大小。");
			return null;
		}
	}

	public static Map.Entry<String, Object> getEntryByIndexA(Map<String,Object> map, int index) {


		List<Map.Entry<String, Object>> entries = new ArrayList<>(map.entrySet());

		if (index >= 0 && index < entries.size()) {
			return entries.get(index);
		} else {
			System.out.println("序号超出Map的大小。");
			return null;
		}
	}
	public static String[] setCom(List<LinkedHashMap<String, String>> list, JComboBox com, String stock[]) {

		com.removeAllItems();
		int a=0;
		for (Map<String, String> stringStringMap : list) {
			a++;

			String aa=getEntryByIndex(stringStringMap,0).toString();
			aa=aa.split("=")[1];

			String b=getEntryByIndex(stringStringMap,1).toString();
			b=b.split("=")[1];
			com.addItem(aa+b);
		}
		stock=new String[a];
		int j=0;
		for (Map<String, String> stringStringMap : list) {

			String aa=getEntryByIndex(stringStringMap,0).toString();
			aa=aa.split("=")[1];
			stock[j]=aa;
			j++;
		}
		return stock;
	}

}
