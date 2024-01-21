package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TrangDangNhap extends JFrame {

	private JPanel contentPane;
	private JTextField textField_taikhoan;
	private JTextField textField_matkhau;
	private JButton btnDangNhap;
	private JButton btnTaoTaiKhoan;

	public TrangDangNhap() {
		setTitle("Trang Đăng Nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		panel.setBounds(0, 283, 495, 356);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnDangNhap = new JButton("Đăng Nhập");
		btnDangNhap.setForeground(new Color(147, 112, 219));
		btnDangNhap.setBackground(new Color(30, 144, 255));
		btnDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDangNhap.setBounds(31, 266, 145, 40);
		btnDangNhap.addActionListener(null);
		panel.add(btnDangNhap);
		
		btnTaoTaiKhoan = new JButton("Tạo Tài Khoản");
		btnTaoTaiKhoan.setBackground(new Color(30, 144, 255));
		btnTaoTaiKhoan.setForeground(new Color(147, 112, 219));
		btnTaoTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTaoTaiKhoan.setBounds(301, 266, 163, 40);
		panel.add(btnTaoTaiKhoan);
		
		JLabel lbltaikhoan = new JLabel("Tài Khoản");
		lbltaikhoan.setForeground(new Color(148, 0, 211));
		lbltaikhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltaikhoan.setBounds(31, 74, 139, 45);
		panel.add(lbltaikhoan);
		
		JLabel lblmatkhau = new JLabel("Mật Khẩu");
		lblmatkhau.setForeground(new Color(148, 0, 211));
		lblmatkhau.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblmatkhau.setBounds(31, 157, 111, 40);
		panel.add(lblmatkhau);
		
		textField_taikhoan = new JTextField();
		textField_taikhoan.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_taikhoan.setBounds(180, 78, 139, 45);
		panel.add(textField_taikhoan);
		textField_taikhoan.setColumns(10);
		
		textField_matkhau = new JTextField();
		textField_matkhau.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_matkhau.setBounds(180, 157, 139, 40);
		panel.add(textField_matkhau);
		textField_matkhau.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(100, 149, 237));
		panel_1.setBounds(0, 0, 502, 180);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MANAGEMENT INTERNET");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(10, 55, 471, 73);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(175, 238, 238));
		panel_2.setBounds(0, 179, 495, 101);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblHello = new JLabel("HELLO YOU");
		lblHello.setBackground(new Color(255, 255, 255));
		lblHello.setBounds(155, 29, 196, 37);
		lblHello.setForeground(new Color(119, 136, 153));
		lblHello.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		panel_2.add(lblHello);
		
		this.setVisible(true);
		
		btnDangNhap.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String taikhoan = textField_taikhoan.getText();
			String matkhau = textField_matkhau.getText();
			boolean found = false;
			
			try {
				
				if (taikhoan.length() > 0 && matkhau.length() > 0) {
					found = true;
					
					if (found) {
                	Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/swing_demo", "root", "");
      	            Statement st = null;
      				try {
      					st = connection.createStatement();
      					String sql2 = "SELECT * FROM dangnhap WHERE taikhoan = '" + textField_taikhoan.getText() + "' AND matkhau = '" + textField_matkhau.getText() + "'";

      					 ResultSet rs = st.executeQuery(sql2);
      					if (rs.next()) {
      					    QLKHView QLKH = new QLKHView();
      					    dispose();
      					}else {
      						JOptionPane.showMessageDialog(null, "Tài khoản hoặc mất khẩu không đúng!",
      	                            "Thông báo", JOptionPane.ERROR_MESSAGE);
      					}
      					
      				} catch (SQLException e1) {
      					// TODO Auto-generated catch block
      					e1.printStackTrace();
      				} 
      				
                	}
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ tài khoản và mật khẩu!",
                        "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        		}
			}
		});
		btnTaoTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrangTaoTaiKhoan taikhoan = new TrangTaoTaiKhoan();
				dispose();
			
			}
		});
	}
}
