package com.stock.view;

import com.stock.util.Table;
import com.stock.util.Tools;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.stock.util.Mysqld;
public class InOuStockMan {

	JFrame frame;
	private JTextField textField_3;

	

	/**
	 * Create the application.
	 */
	public InOuStockMan() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	String stock[] = null;
	String stock1[] = null;
	String stock2[] = null;
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
		
		JLabel lblNewLabel_5 = new JLabel("商编编号");
		panel_4_2.add(lblNewLabel_5);
		
		JComboBox comboBox = new JComboBox();
		panel_4_2.add(comboBox);
		
		//添加商品s
		
		stock= Other.setCom("select * from s_commodity", comboBox, 1,stock);
		
		JLabel lblNewLabel_2_1 = new JLabel("仓库编号");
		panel_4_2.add(lblNewLabel_2_1);
		
		
		
		JComboBox comboBox_1 = new JComboBox();
		panel_4_2.add(comboBox_1);
		
		stock1= Other.setCom("select * from s_stock", comboBox_1, 1,stock1);
	
		

		
		JLabel lblNewLabel = new JLabel("商品数量");
		panel_4_2.add(lblNewLabel);
		
		textField_3 = new JTextField();
		panel_4_2.add(textField_3);
		textField_3.setColumns(10);
		textField_3.addKeyListener(new KeyAdapter() {
	            public void keyTyped(KeyEvent e) {
	                if(e.getKeyChar()<KeyEvent.VK_0||e.getKeyChar()>KeyEvent.VK_9) {
	                    e.consume();
	                }else {
	                    System.out.print(e.getKeyChar());
	                }
	            }

			
	        });
		
		
		
		
		
		
		
		
		
		JButton btnNewButton_4 = new JButton("入库");
		panel_4_2.add(btnNewButton_4);
		//增加
		btnNewButton_4.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
	if(textField_3.getText().equals("")) {
					
					return;
				}
				String date[]= {
						stock[comboBox.getSelectedIndex()],
						stock1[comboBox_1.getSelectedIndex()],
						textField_3.getText(),
						"入库"
						
						
				};
				int a=Mysqld.upDate("insert into s_rcode (p_id,c_id,a_number,a_type) VALUES(?,?,?,?)", date);
				if(a==1) {
					Tools.messageWindows("入库成功");
				}else {
					Tools.messageWindows("入库失败");
				}
				
				
				
				
				
			}
		});
		
		JButton btnNewButton = new JButton("出库");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
	if(textField_3.getText().equals("")) {
					
					return;
				}
				
				String date[]= {
						stock[comboBox.getSelectedIndex()],
						stock1[comboBox_1.getSelectedIndex()],
						textField_3.getText(),
						"出库"
						
						
				};
				//出库之前看看库存够不够
				String da[]= {
						stock[comboBox.getSelectedIndex()],
						stock1[comboBox_1.getSelectedIndex()],	
				};
				ResultSet s1 = Mysqld.QueryData("select sum(a_number) from s_rcode where p_id=? and c_id=? and a_type='入库' group by p_id", da);
				ResultSet s2 = Mysqld.QueryData("SELECT  ISNULL((select sum(a_number) from s_rcode where p_id=? and c_id=? and a_type='出库' group by p_id), 0)", da);
				
				try {
					
				int sum=0;
				if(s1.next()) {
					if(s2.next()) {
						int in=s1.getInt(1);
						int out=s2.getInt(1);
						sum=in-out;
						s1.close();
						s2.close();
					}
				}
					
					
				
					
					
					
					sum=sum-Integer.parseInt(textField_3.getText());
					if(sum<0) {
						Tools.messageWindows("库存不足");
					}else {
						
						int a=Mysqld.upDate("insert into s_rcode (p_id,c_id,a_number,a_type) VALUES(?,?,?,?)", date);
						if(a==1) {
							Tools.messageWindows("出库成功");
						}else {
							Tools.messageWindows("出库失败");
						}
						
						
						
					}
					
					
					
					
					
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
			
				
				
				
			}
		});
		panel_4_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("查询记录");
	
		panel_4_2.add(btnNewButton_1);
		
		
		Object columns[] ={"物料仓库","物料名字","物料数量"};//创建表格
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		//JS.setPreferredSize(new Dimension( 697-80, 448-200));//设置整个滚动条窗口的大小
		JS.setBounds(10, 152, 829, 408);
		panel_1.add(JS);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel.setBounds(10, 80, 829, 48);
		panel_1.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("目标仓库");
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox_2 = new JComboBox();
		panel.add(comboBox_2);
		stock2= Other.setCom("select * from s_stock", comboBox_2, 1,stock2);
		
		
		
		JButton btnNewButton_2 = new JButton("转仓库");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
	if(textField_3.getText().equals("")) {
					
					return;
				}
				//A 仓库 衣服  向 B仓库 转  100
				//A 仓库 50   B仓库   10
				//60
				//查询当前库存 
				String data[]= {
						stock[comboBox.getSelectedIndex()],
						stock1[comboBox_1.getSelectedIndex()]	
						
				};
				
				ResultSet rs2 = Mysqld.QueryData("ShowCount ?,?", data);
				try {
					int a;
					int c=0;
					while(rs2.next()) {
						
						a=rs2.getInt(1);
						int b=Integer.parseInt(textField_3.getText());
						if(a-b>=0) {
							//可以进行出库
							c=1;
							
							String date[]= {
									stock[comboBox.getSelectedIndex()],
									stock1[comboBox_1.getSelectedIndex()],
									textField_3.getText(),
									"出库"
									
									
							};
							
							
							
							String date11[]= {
									stock[comboBox.getSelectedIndex()],
									stock2[comboBox_2.getSelectedIndex()],
									textField_3.getText(),
									"入库"
									
									
							};
							
							a=Mysqld.upDate("insert into s_rcode (p_id,c_id,a_number,a_type) VALUES(?,?,?,?)", date);
							a=Mysqld.upDate("insert into s_rcode (p_id,c_id,a_number,a_type) VALUES(?,?,?,?)", date11);
							if(a==1) {
								Tools.messageWindows("转仓成功");
							}else {
								Tools.messageWindows("转仓失败");
							}
							
						}
					}
					if(c==0) {
						Tools.messageWindows("转仓失败");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		panel.add(btnNewButton_2);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//查询，所有仓库  的各种物料数量
				//A仓库  的B物料
				//A仓库  的C物料
				
				
				
				ResultSet rs = Mysqld.QueryData("SELECT * from s_stock", null);
				
				
				try {
					model.setRowCount(0);
					while(rs.next()) {
						
					
						ResultSet rs1 = Mysqld.QueryData("SELECT * from s_commodity", null);
						while(rs1.next()) {
							String dataa[]=new String[3];
							dataa[0]=rs.getString(2);
							String d[]= {
									rs1.getString(1),
									rs.getString(1)
									
									
							};
							
							
							dataa[1]=rs1.getString(2);
							ResultSet rs2 = Mysqld.QueryData("ShowCount ?,?", d);
							if(rs2.next()) {
								dataa[2]=rs2.getString(1);
								model.addRow(dataa);
				
								
							}
							rs2.close();
							
						}
						rs1.close();
						
					}
					rs.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
	}
}
