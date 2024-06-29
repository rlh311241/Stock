package com.stock.view;

import com.stock.dao.CommodityMapperDao;
import com.stock.util.Table;
import com.stock.util.Tools;
import com.stock.util.Mysqld;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LingSc {

	JFrame frame;
	private JTextField textField_3;


	/**
	 * Create the application.
	 */
	String account;
	public LingSc(String account) {
		this.account=account;
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

		List<LinkedHashMap<String, String>> stockList1 = new CommodityMapperDao().selectAllCommodity();
		stock= Other.setCom(stockList1, comboBox, stock);
		//stock= Other.setCom("select * from s_commodity", comboBox, 1,stock);
		
		JLabel lblNewLabel_2_1 = new JLabel("仓库编号");
		panel_4_2.add(lblNewLabel_2_1);
		
		
		
		JComboBox comboBox_1 = new JComboBox();
		panel_4_2.add(comboBox_1);
		
		//stock1= Other.setCom("select * from s_stock", comboBox_1, 1,stock1);
		List<LinkedHashMap<String, String>> stockList = new CommodityMapperDao().selectAllStock();
		stock1= Other.setCom(stockList, comboBox, stock1);
		

		
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
		
		JButton btnNewButton = new JButton("领取物料");
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
							
							String date1[]= {
									stock[comboBox.getSelectedIndex()],
									account,
									textField_3.getText(),
						
									
									
							};
							
							
							a=Mysqld.upDate("insert into s_lrcode(p_id,s_account,a_number) VALUES (?,?,?)", date1);
							Tools.messageWindows("领取成功");
						}else {
							Tools.messageWindows("领取失败");
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
		
		
		Object columns[] ={"物料名字","物料种类","物料数量","领取时间"};//创建表格
		Table t1Table=new Table(columns);
		JTable table = t1Table.getTables();
		JScrollPane JS = t1Table.getJScrollPane();
		DefaultTableModel model = t1Table.getModel();
		//JS.setPreferredSize(new Dimension( 697-80, 448-200));//设置整个滚动条窗口的大小
		JS.setBounds(10, 152, 829, 408);
		panel_1.add(JS);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//查询，所有仓库  的各种物料数量
				//A仓库  的B物料
				//A仓库  的C物料
				
				String d[]= {
					account
				};
				ResultSet rs2 = Mysqld.QueryData("select s_commodity.p_name, s_commodity.p_class,s_lrcode.a_number, s_lrcode.a_date  from s_lrcode,s_commodity where s_lrcode.p_id=s_commodity.p_id and s_lrcode.s_account=?", d);
				if(rs2==null) {
					
				}else {
					Tools.addDataTable(rs2, model, 4);
				}
				
				
				
				
				
				
			}
		});
		
	}
}
