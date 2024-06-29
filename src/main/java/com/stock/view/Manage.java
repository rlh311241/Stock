package com.stock.view;

import com.stock.bean.SAdmin;
import com.stock.dao.UserMapperDao;
import com.stock.util.Mysqld;
import com.stock.util.Table;
import com.stock.util.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manage {

	public JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the application.
	 */
	public Manage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("管理界面");
		frame.setBounds(100, 100, 868, 646);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBounds(0, 0, 854, 599);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("用户管理", null, panel, "");
		panel.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.setBounds(10, 10, 829, 60);
		panel.add(panel_4);
		
		JLabel lblNewLabel = new JLabel("账号");
		panel_4.add(lblNewLabel);
		
		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		panel_4.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		panel_4.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("姓名");
		panel_4.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		panel_4.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("部门");
		panel_4.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"--请选择部门--"}));
		panel_4.add(comboBox);
		
		JButton btnNewButton = new JButton("增加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(textField.getText().equals("")) {
					Tools.messageWindows("请输入账号");
				}else if(passwordField.getText().equals("")) {
					Tools.messageWindows("请输入密码");
				}else if(textField_1.getText().equals("")) {
					Tools.messageWindows("请输姓名");
				}else if(comboBox.getSelectedIndex()==0) {
					Tools.messageWindows("请选择部门");
				}else {


					SAdmin jg = new SAdmin(textField.getText(), passwordField.getText(), "2", textField_1.getText(), (String) comboBox.getSelectedItem());
					int a=new UserMapperDao().insertSAdmin(jg);
					//int a=Mysqld.upDate("INSERT INTO s_admin (s_account,s_password,s_pow,s_name,d_depart) VALUES(?,?,?,?,?);", data);
					if(a==1) {
						Tools.messageWindows("添加成功");
					}else {
						Tools.messageWindows("添加失败");
					}
			
					
					
				}
				
			}
		});
		panel_4.add(btnNewButton);
		
		JPanel panel_4_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_4_1.setBounds(10, 82, 829, 60);
		panel.add(panel_4_1);
		
		JLabel lblNewLabel_4 = new JLabel("账号");
		panel_4_1.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panel_4_1.add(textField_2);
		
		Object columns[] ={"账号","名字","部门"};//创建表格
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		//JS.setPreferredSize(new Dimension( 697-80, 448-200));//设置整个滚动条窗口的大小
		JS.setBounds(10, 152, 829, 408);
		panel.add(JS);
	
		
		
		
		JButton btnNewButton_1 = new JButton("删除");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(textField_2.getText().equals("")) {
					Tools.messageWindows("账号");
				}else {
					
					//String data[]= {textField_2.getText()};

					int a=new UserMapperDao().delUserById(textField_2.getText());
					//int a=Mysqld.upDate("DELETE FROM s_admin where s_account=?", data);
					if(a==1) {
						Tools.messageWindows("删除成功");
					}else {
						Tools.messageWindows("删除失败");
					}
				}
				
			}
		});
		panel_4_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("更改");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField_2.getText().equals("")) {
					Tools.messageWindows("请输入账号");
				}else
				
				if(textField.getText().equals("")) {
					Tools.messageWindows("请输入账号");
				}else if(passwordField.getText().equals("")) {
					Tools.messageWindows("请输入密码");
				}else if(textField_1.getText().equals("")) {
					Tools.messageWindows("请输姓名");
				}else if(comboBox.getSelectedIndex()==0) {
					Tools.messageWindows("请选择部门");
				}else {
					
					String data[]= {
							textField.getText(),
							passwordField.getText(),
						
							textField_1.getText(),
							(String)comboBox.getSelectedItem(),
							textField_2.getText()
							
							
							
							
					};




					Map<String,String> map=new HashMap<String,String>();
					map.put("sAccount",data[0]);
					map.put("sPassword",data[1]);
					map.put("sName",data[2]);
					map.put("dDepart",data[3]);
					map.put("sAccountW",data[4]);
					int a=new UserMapperDao().updateAdmin(map);

					if(a==1) {
						Tools.messageWindows("更改成功");
					}else {
						Tools.messageWindows("更改失败");
					}
			
					
					
				}
				
				
				
				
			}
		});
		panel_4_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("查找");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField_2.getText().equals("")) {
					//查找全部
					//Tools.messageWindows("账号");
	
					EasyCode.showAllData("SELECT s_account,s_name,d_depart from s_admin", 3, model);
				}else {
					//查找单个
				String data[]= {
					textField_2.getText()
				};

					ResultSet rs = Mysqld.QueryData("SELECT s_account,s_name,d_depart from s_admin where s_account=?",data);
					Tools.addDataTable(rs, model, 3);
					ResultSet rs1 = Mysqld.QueryData("SELECT *  from s_admin where s_account=?",data);
					try {
						String a="";
						if(rs1.next()) {
							textField.setText(rs1.getString(1));
							passwordField.setText(rs1.getString(2));
							textField_1.setText(rs1.getString(4));
							a=rs1.getString(5);
						}
						for(int i=0;i<comboBox.getItemCount();i++) {
							String c=(String )comboBox.getItemAt(i);
							if(c.equals(a)) {
								comboBox.setSelectedIndex(i);
							}
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
			}
		});
		panel_4_1.add(btnNewButton_3);
		

	
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("仓库管理", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_4_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_4_2.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_4_2.setBounds(10, 10, 829, 60);
		panel_1.add(panel_4_2);
		
		JLabel lblNewLabel_5 = new JLabel("仓库编号");
		panel_4_2.add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panel_4_2.add(textField_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("仓库名字");
		panel_4_2.add(lblNewLabel_2_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		panel_4_2.add(textField_4);
		
		JButton btnNewButton_4 = new JButton("增加");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(textField_3.getText().equals("")) {
					Tools.messageWindows("请输入仓库编号");
				}else if(textField_4.getText().equals("")) {
					Tools.messageWindows("请输入仓库名称");
				}else {
					
					JTextField te[]= {
							
							textField_3,
							textField_4
							
					};
				
					String data[]= {
							textField_3.getText(),
							textField_4.getText()
					};
					int a=Mysqld.upDate("insert into s_stock VALUES(?,?)", data);
					if(a==1) {
						Tools.messageWindows("添加成功");
					}else {
						Tools.messageWindows("添加失败");
					}
				}
				
				
				
				
			}
		});
		panel_4_2.add(btnNewButton_4);
		
		JButton btnNewButton_7 = new JButton("物料管理");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Material window = new Material();
				window.frame.setVisible(true);
				
				
			}
		});
		panel_4_2.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("查看数据");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowDade window = new ShowDade();
				window.frame.setVisible(true);
			}
		});
		panel_4_2.add(btnNewButton_8);
		
		JPanel panel_4_1_1 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_4_1_1.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panel_4_1_1.setBounds(10, 80, 829, 60);
		panel_1.add(panel_4_1_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("仓库编号");
		panel_4_1_1.add(lblNewLabel_4_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		panel_4_1_1.add(textField_5);
		
		JButton btnNewButton_1_1 = new JButton("删除");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JTextField data[]= {
						textField_5	
				};
				EasyCode.deleteDate(data, "DELETE FROM s_stock where c_id=?", 1, "请输入仓库编码");
				
			}
		});
		panel_4_1_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("更改");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField data[]= {
						textField_3,
						textField_4,
						textField_5	
				};
				
				EasyCode.upData(data, "UPDATE s_stock set c_id=? ,c_name=? where c_id=?", 3, "请输入仓库编码");
			}
		});
		panel_4_1_1.add(btnNewButton_2_1);
		
		Object columns1[] ={"仓库编码","仓库名字"};//创建表格
		Table t1Table1=new Table(columns1);
		JTable table1 = t1Table1.getTables();
		JScrollPane JS1 = t1Table1.getJScrollPane();
		DefaultTableModel model1 = t1Table1.getModel();
		//JS.setPreferredSize(new Dimension( 697-80, 448-200));//设置整个滚动条窗口的大小
		JS1.setBounds(10, 152, 829, 408);
		panel_1.add(JS1);
		
		
		
		JButton btnNewButton_3_1 = new JButton("查找");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JTextField data[]= {
						textField_5	
				};
				JTextField data1[]= {
						textField_3,
						textField_4
				};
				if(textField_5.getText().equals("")) {
					
					EasyCode.showAllData("select * from s_stock", 2, model1);
				}else {
					int dat[]= {1,2};
					EasyCode.showOneData(data, data1, "select * from s_stock where c_id=?", 2, model1, dat);
				}
			}
		});
		panel_4_1_1.add(btnNewButton_3_1);
		
		JButton btnNewButton_5 = new JButton("进库入库");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InOuStockMan window = new InOuStockMan();
				window.frame.setVisible(true);
			}
		});
		panel_4_1_1.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("部门管理");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Depart window = new Depart();
				window.frame.setVisible(true);
			}
		});
		panel_4_1_1.add(btnNewButton_6);


		List< Map<String,String>> list = new UserMapperDao().getDepartAllList();

		comboBox.removeAllItems();
		comboBox.addItem("--请选择部门--");

		for (Map<String, String> stringStringMap : list) {
			comboBox.addItem(stringStringMap.get("d_depart"));
		}



		
		//给整体加监听
		tabbedPane.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				if(tabbedPane.getSelectedIndex()==0) {
					comboBox.removeAllItems();
					comboBox.addItem("--请选择部门--");
					List< Map<String,String>> list = new UserMapperDao().getDepartAllList();
					for (Map<String, String> stringStringMap : list) {
						comboBox.addItem(stringStringMap.get("d_depart"));
					}

				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
}
