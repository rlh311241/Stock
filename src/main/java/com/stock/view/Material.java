package com.stock.view;

import com.stock.util.Mysqld;
import com.stock.util.Table;
import com.stock.util.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Material {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the application.
	 */
	public Material() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("物料管理");
		frame.setBounds(100, 100,  868, 646);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 10, 849, 570);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_4_2 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4_2.setBounds(10, 10, 829, 60);
		panel_1.add(panel_4_2);
		
		JLabel lblNewLabel_5 = new JLabel("物料编码");
		panel_4_2.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_4_2.add(textField);
		
		JLabel lblNewLabel_2_1 = new JLabel("物料名字");
		panel_4_2.add(lblNewLabel_2_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_4_2.add(textField_1);
		
		JLabel lblNewLabel = new JLabel("种类");
		panel_4_2.add(lblNewLabel);
		
		textField_3 = new JTextField();
		panel_4_2.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("增加");
		panel_4_2.add(btnNewButton_4);
		
		JPanel panel_4_1_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4_1_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4_1_1.setBounds(10, 80, 829, 60);
		panel_1.add(panel_4_1_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("物料编号");
		panel_4_1_1.add(lblNewLabel_4_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_4_1_1.add(textField_2);
	
		
		JButton btnNewButton_1_1 = new JButton("删除");
		panel_4_1_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("更改");
		panel_4_1_1.add(btnNewButton_2_1);
		
		JButton btnNewButton_3_1 = new JButton("查找");
		panel_4_1_1.add(btnNewButton_3_1);
		
		
		Object columns[] ={"物料编号","物料名字","物料数量","物料种类"};//创建表格
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		//JS.setPreferredSize(new Dimension( 697-80, 448-200));//设置整个滚动条窗口的大小
		JS.setBounds(10, 152, 829, 408);
		panel_1.add(JS);
		
		//删除
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JTextField data[]= {
						textField_2	
				};
				EasyCode.deleteDate(data, "DELETE FROM s_commodity where p_id=?", 1, "请输入物料编码");
				
			}
		});
		//更改
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField data[]= {
						textField,
						textField_1,
						textField_2	
				};
				
				EasyCode.upData(data, "UPDATE s_commodity set p_id=?,p_name=? where p_id=?", 3, "请输入物料编码");
				
			}		});
		
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JTextField data[]= {
						textField_2
				};
				JTextField data1[]= {
						textField,
						textField_1,
						textField_3
				};
				if(textField_2.getText().equals("")) {
					
					EasyCode.showAllData("select * from s_commodity ORDER BY p_class", 4, model);
				}else {
					int dat[]= {1,2,4};
					EasyCode.showOneData(data, data1, "select * from s_commodity where p_id=?  ", 3, model, dat);
				}
			}
		});
		//增加
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField.getText().equals("")) {
					Tools.messageWindows("请输入物料编号");
				}else if(textField_1.getText().equals("")) {
					Tools.messageWindows("请输入物料名称");
				}else if(textField_3.getText().equals("")){
					Tools.messageWindows("请输入物料种类");
					
				}else{
					
					JTextField te[]= {
							
							textField,
							textField_1,
							textField_3
						
							
					};
				
					String data[]= {
							textField.getText(),
							textField_1.getText(),
							textField_3.getText()					};
					int a= Mysqld.upDate("insert into s_commodity (p_id,p_name,p_class)VALUES(?,?,?)", data);
					if(a==1) {
						Tools.messageWindows("添加成功");
					}else {
						Tools.messageWindows("添加失败");
					}
				}
				
				
				
				
			}
		});
		
		
		
	}
}
