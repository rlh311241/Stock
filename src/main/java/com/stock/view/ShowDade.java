package com.stock.view;

import com.stock.dao.MaterialMapperDao;
import com.stock.util.Mysqld;
import com.stock.util.Table;
import com.stock.util.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;

public class ShowDade {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Create the application.
	 */
	public ShowDade() {
		//this.t1Table1 = null;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	Object columns1[] ={"姓名","部门","物料名字","物料种类","物料数量","领取时间"};//创建表格
	Table t1Table1=new Table(columns1);
	JTable table1 = t1Table1.getTables();
	JScrollPane JS1 = t1Table1.getJScrollPane();
	DefaultTableModel model1 = t1Table1.getModel();

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 851, 566);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBounds(10, 10, 817, 59);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("开始时间");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("结束时间");
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		
		
		
		
		//JS.setPreferredSize(new Dimension( 697-80, 448-200));//设置整个滚动条窗口的大小
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 77, 827, 452);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		JS1.setBounds(0, 0, 817, 429);
		
		
		
		
		JButton btnNewButton = new JButton("查询物料记录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(textField.getText().equals("")) {
					Tools.messageWindows("请输入开始时间");
				}else if(textField_1.getText().equals("")) {
					Tools.messageWindows("请输入结束时间");
				}else {
					panel_2.removeAll();
					SwingUtilities.updateComponentTreeUI(panel_2);//添加或删除组件
					Object columns1[] ={"姓名","部门","物料名字","物料种类","物料数量","领取时间"};//创建表格
					t1Table1=new Table(columns1);
					table1 = t1Table1.getTables();
					JS1 = t1Table1.getJScrollPane();
					model1 = t1Table1.getModel();
					JS1.setBounds(0, 0, 817, 429);
					panel_2.add(JS1);
					//格式2021 -5-6

					//ResultSet rs = Mysqld.QueryData("getPro ?,?  ", da);
					List<LinkedHashMap<String, Object>> res = new MaterialMapperDao().getPro(textField.getText(),
							textField_1.getText());
					EasyCode.showAllData(res,model1);
					
				}
				
				
				
			
				
				
				
		
			
				
			}
		});
		
		//t1Table2
		
		
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("查询出库");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals("")) {
					Tools.messageWindows("请输入开始时间");
				}else if(textField_1.getText().equals("")) {
					Tools.messageWindows("请输入结束时间");
				}else {
					panel_2.removeAll();
					SwingUtilities.updateComponentTreeUI(panel_2);//添加或删除组件
					Object columns1[] ={"物料名字","总数量","种类","仓库","数量","时间"};//创建表格
					t1Table1=new Table(columns1);
					table1 = t1Table1.getTables();
					JS1 = t1Table1.getJScrollPane();
					model1 = t1Table1.getModel();
					JS1.setBounds(0, 0, 817, 429);
					panel_2.add(JS1);
					//格式2021 -5-6

					//ResultSet rs = Mysqld.QueryData("getProP ?,?  ", da);
					
					//Tools.addDataTable(rs, model1, 6);


					List<LinkedHashMap<String, Object>> res = new MaterialMapperDao().getProP(	textField.getText(),
							textField_1.getText());
					EasyCode.showAllData(res,model1);
					
					
				}
				
				
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("查询入库");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textField.getText().equals("")) {
					Tools.messageWindows("请输入开始时间");
				}else if(textField_1.getText().equals("")) {
					Tools.messageWindows("请输入结束时间");
				}else {
					panel_2.removeAll();
					SwingUtilities.updateComponentTreeUI(panel_2);//添加或删除组件
					Object columns1[] ={"物料名字","总数量","种类","仓库","数量","时间"};//创建表格
					t1Table1=new Table(columns1);
					table1 = t1Table1.getTables();
					JS1 = t1Table1.getJScrollPane();
					model1 = t1Table1.getModel();
					JS1.setBounds(0, 0, 817, 429);
					panel_2.add(JS1);
					//格式2021 -5-6

					List<LinkedHashMap<String, Object>> res = new MaterialMapperDao().getProI(textField.getText(),
							textField_1.getText());
					EasyCode.showAllData(res,model1);
					
					
				}
			}
		});
		panel.add(btnNewButton_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 90, 817, 429);
		
		
		panel_2.add(JS1);
		

	}
}
