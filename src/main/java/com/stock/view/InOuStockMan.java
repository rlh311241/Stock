package com.stock.view;

import com.stock.dao.CommodityMapperDao;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		List<LinkedHashMap<String, String>> comodList = new CommodityMapperDao().selectAllCommodity();
		stock= Other.setCom(comodList, comboBox, stock);



		JLabel lblNewLabel_2_1 = new JLabel("仓库编号");
		panel_4_2.add(lblNewLabel_2_1);
		
		
		
		JComboBox comboBox_1 = new JComboBox();
		panel_4_2.add(comboBox_1);


		List<LinkedHashMap<String, String>> stockList = new CommodityMapperDao().selectAllStock();
		stock1= Other.setCom(stockList, comboBox_1, stock1);


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


			int a=new CommodityMapperDao().addRecord(stock[comboBox.getSelectedIndex()],
					stock1[comboBox_1.getSelectedIndex()],
					textField_3.getText(),
					"入库"
			);
			//int a=Mysqld.upDate("insert into s_rcode (p_id,c_id,a_number,a_type) VALUES(?,?,?,?)", date);
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
				

				//出库之前看看库存够不够

				List<LinkedHashMap<String, Object>> jg1 = new CommodityMapperDao().getStock(stock[comboBox.getSelectedIndex()], stock1[comboBox_1.getSelectedIndex()]);
				List<LinkedHashMap<String, Object>> jg11 = new CommodityMapperDao().getStockOne(stock[comboBox.getSelectedIndex()], stock1[comboBox_1.getSelectedIndex()]);

				//ResultSet s1 = Mysqld.QueryData("select sum(a_number) from s_rcode where p_id=? and c_id=? and a_type='入库' group by p_id", da);
				//ResultSet s2 = Mysqld.QueryData("SELECT  ISNULL((select sum(a_number) from s_rcode where p_id=? and c_id=? and a_type='出库' group by p_id), 0)", da);
				

					
				int sum=0;
				if(jg1.size()>0) {
					if(jg11.size()>0) {

						String aac =jg1.get(0).get("a").toString();
						int in=Integer.valueOf(aac);
						aac = jg11.get(0).get("a").toString();
						int out=Integer.valueOf(aac);
						sum=in-out;

					}
				}

					sum=sum-Integer.parseInt(textField_3.getText());
					if(sum<0) {
						Tools.messageWindows("库存不足");
					}else {

						int a=new CommodityMapperDao().addRecord(stock[comboBox.getSelectedIndex()],
								stock1[comboBox_1.getSelectedIndex()],
								textField_3.getText(),
								"出库"
								);
						//int a=Mysqld.upDate("insert into s_rcode (p_id,c_id,a_number,a_type) VALUES(?,?,?,?)", date);
						if(a==1) {
							Tools.messageWindows("出库成功");
						}else {
							Tools.messageWindows("出库失败");
						}
						
						
						
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


		List<LinkedHashMap<String, String>> stockList1 = new CommodityMapperDao().selectAllStock();
		stock2= Other.setCom(stockList1, comboBox_2, stock2);
		//stock2= Other.setCom("select * from s_stock", comboBox_2, 1,stock2);
		
		
		
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


				List<LinkedHashMap<String, String>> list = new CommodityMapperDao().getCount(stock[comboBox.getSelectedIndex()], stock1[comboBox_1.getSelectedIndex()]);

				//ResultSet rs2 = Mysqld.QueryData("ShowCount ?,?", data);
					int a;
					int c=0;
					for (Map<String, String> stringObjectMap : list) {
						String aa = Other.getEntryByIndex(stringObjectMap, 0).toString();
						aa = aa.split("=")[1];
						a = Integer.parseInt(aa);
						int b = Integer.parseInt(textField_3.getText());
						if (a - b >= 0) {
							//可以进行出库
							c = 1;


							a = new CommodityMapperDao().addRecord(stock[comboBox.getSelectedIndex()],
									stock1[comboBox_1.getSelectedIndex()],
									textField_3.getText(),
									"出库");
							a = new CommodityMapperDao().addRecord(stock[comboBox.getSelectedIndex()],
									stock2[comboBox_2.getSelectedIndex()],
									textField_3.getText(),
									"入库");
							if (a == 1) {
								Tools.messageWindows("转仓成功");
							} else {
								Tools.messageWindows("转仓失败");
							}
						}

						if (c == 0) {
							Tools.messageWindows("转仓失败");
						}

					}
				
				
			}
		});
		panel.add(btnNewButton_2);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//查询，所有仓库  的各种物料数量
				//A仓库  的B物料
				//A仓库  的C物料


				List<LinkedHashMap<String, String>> rs = new CommodityMapperDao().selectAllStock();
				model.setRowCount(0);

				for (Map<String, String> r : rs) {

					List<LinkedHashMap<String, String>> rs1 = new CommodityMapperDao().selectAllCommodity();
					for (Map<String, String> r1 : rs1) {
						String dataa[]=new String[3];
						dataa[0]= Other.getEntryByIndex(r, 1).toString().split("=")[1];

						String rr1=Other.getEntryByIndex(r1, 0).toString().split("=")[1];
						String rr0 =Other.getEntryByIndex(r, 0).toString().split("=")[1];
						dataa[1]=Other.getEntryByIndex(r1, 1).toString().split("=")[1];


						List<LinkedHashMap<String, String>> r2 = new CommodityMapperDao().getCount(rr1, rr0);
						for (Map<String, String> stringStringMap : r2) {
							dataa[2]=Other.getEntryByIndex(stringStringMap,0).toString().split("=")[1];
							model.addRow(dataa);
						}

					}
				}



				//ResultSet rs = Mysqld.QueryData("SELECT * from s_stock", null);






			}
		});
		
	}
}
