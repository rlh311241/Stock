package com.stock.view;



import com.stock.bean.SAdmin;
import com.stock.dao.UserMapperDao;
import com.stock.util.Tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//DBUtil a=new DBUtil("sa","123456","db_stock");//数据库账号，数据库密码，数据库名称
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("仓库管理系统");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
	//	panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 414, 241);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("仓库管理系统");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 17));
		lblNewLabel.setBounds(151, 36, 112, 21);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("账号");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(75, 82, 54, 15);
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(151, 79, 126, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("密码");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setFont(new Font("宋体", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(75, 129, 54, 15);
		panel.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(151, 126, 126, 21);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(161, 173, 70, 23);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(textField.getText().equals("")) {
					Tools.messageWindows(" 请输入账号");
				}else if(passwordField.getText().equals("")) {
					Tools.messageWindows(" 请输入密码");
				}else {
					String data[]= {
							textField.getText(),
							passwordField.getText()
							
					};

					UserMapperDao userMapperDao=new UserMapperDao();

					SAdmin sAdmin=new SAdmin();
					sAdmin.setSAccount(textField.getText());
					sAdmin.setSPassword(passwordField.getText());
					sAdmin.setSPow("1");
					SAdmin jg = userMapperDao.getUserByAccount(sAdmin);
					if(jg!=null) {
						Manage window=new Manage();
						window.frame.setVisible(true);
						frame.dispose();
					}else{
						sAdmin.setSPow("2");
						jg = userMapperDao.getUserByAccount(sAdmin);
						if(jg!=null) {
							LingSc window = new LingSc(textField.getText());
							window.frame.setVisible(true);
							frame.dispose();
						}else{
							Tools.messageWindows("密码错误");
						}
					}

				}
			}
			
		});
	}
}
