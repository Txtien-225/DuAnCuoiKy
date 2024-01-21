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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class TrangTaoTaiKhoan extends JFrame {

	private JPanel contentPane;
	private JTextField textField_matkhau;
	private JTextField textField_nhaplaimatkhau;
	private JButton btnTaoTaiKhoan;
	private JTextField textField_taikhoan;

	public TrangTaoTaiKhoan() {
		setTitle("Trang Tạo Tài Khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 737);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 240));
		panel.setBounds(0, 283, 495, 415);
		contentPane.add(panel);
		panel.setLayout(null);

		btnTaoTaiKhoan = new JButton("Tạo Tài Khoản");
		btnTaoTaiKhoan.setBackground(new Color(30, 144, 255));
		btnTaoTaiKhoan.setForeground(new Color(147, 112, 219));
		btnTaoTaiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTaoTaiKhoan.setBounds(160, 364, 163, 40);
		btnTaoTaiKhoan.addActionListener(null);
		panel.add(btnTaoTaiKhoan);

		JLabel lblNewLabel_1 = new JLabel("Tài Khoản");
		lblNewLabel_1.setForeground(new Color(148, 0, 211));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(31, 39, 139, 45);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Mật Khẩu");
		lblNewLabel_2.setForeground(new Color(148, 0, 211));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(31, 153, 111, 40);
		panel.add(lblNewLabel_2);

		textField_taikhoan = new JTextField();
		textField_taikhoan.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_taikhoan.setBounds(252, 43, 139, 45);
		panel.add(textField_taikhoan);
		textField_taikhoan.setColumns(19);

		textField_matkhau = new JTextField();
		textField_matkhau.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_matkhau.setBounds(252, 153, 139, 40);
		panel.add(textField_matkhau);
		textField_matkhau.setColumns(19);

		JLabel lblNewLabel_2_1 = new JLabel("Nhập Lại Mật Khẩu");
		lblNewLabel_2_1.setForeground(new Color(148, 0, 211));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2_1.setBounds(31, 251, 194, 40);
		panel.add(lblNewLabel_2_1);

		textField_nhaplaimatkhau = new JTextField();
		textField_nhaplaimatkhau.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_nhaplaimatkhau.setColumns(19);
		textField_nhaplaimatkhau.setBounds(252, 251, 139, 40);
		panel.add(textField_nhaplaimatkhau);

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

		btnTaoTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String taikhoan = textField_taikhoan.getText();
				String matkhau = textField_matkhau.getText();
				String nhaplaimatkhau = textField_nhaplaimatkhau.getText();
				boolean found = false;
				try {
					Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/swing_demo",
							"root", "");
					if (taikhoan.length() > 0 && matkhau.length() > 0 && nhaplaimatkhau.length() > 0) {
						try {
							Statement st1 = connection.createStatement();
							String sql1 = "SELECT * FROM dangnhap WHERE taikhoan='" + textField_taikhoan.getText()
									+ "' AND matkhau='" + textField_matkhau.getText() + "';";
							ResultSet rs1 = st1.executeQuery(sql1);
							if (rs1.next()) {
								found = true;
							}

						} catch (Exception e2) {
							e2.printStackTrace();
							// TODO: handle exception
						}

						if (found) {
							JOptionPane.showMessageDialog(null, "Tạo tài khoản thất bại, tài khoản đã tồn tại",
									"Thong bao", JOptionPane.ERROR_MESSAGE);
						} else {

							String sql = "INSERT INTO dangnhap (taikhoan , matkhau)" + " VALUES(?,?)";
							PreparedStatement pst = connection.prepareStatement(sql);

							pst.setString(1, textField_taikhoan.getText());
							pst.setString(2, textField_matkhau.getText());

							pst.executeUpdate();
							JOptionPane.showMessageDialog(null, "Tạo tài khoản thành công", "Thông báo",
									JOptionPane.INFORMATION_MESSAGE);
							TrangDangNhap tdn = new TrangDangNhap();
							dispose();
						}
					} else if (taikhoan.length() == 0 && matkhau.length() > 0 && nhaplaimatkhau.length() > 0) {

						JOptionPane.showMessageDialog(null, "Tạo tài khoản thất bại, vui lòng nhập tài khoản",
								"Thông báo", JOptionPane.ERROR_MESSAGE);

					} else if (taikhoan.length() == 0 && matkhau.length() == 0) {

						JOptionPane.showMessageDialog(null,
								"Tạo tài khoản thất bại , vui lòng nhập đầy đủ tài khoản và mật khẩu!", "Thông báo",
								JOptionPane.ERROR_MESSAGE);

					}

					else if (taikhoan.length() > 0 && matkhau.length() == 0 && nhaplaimatkhau.length() > 0) {

						JOptionPane.showMessageDialog(null, "Tạo tài khoản thất bại , vui lòng nhập mật khẩu!",
								"Thông báo", JOptionPane.ERROR_MESSAGE);

					} else if (taikhoan.length() > 0 && matkhau.length() > 0 && nhaplaimatkhau.length() == 0) {

						JOptionPane.showMessageDialog(null, "Tạo tài khoản thất bại , vui lòng xác nhận mật khẩu!",
								"Thông báo", JOptionPane.ERROR_MESSAGE);
					}

				} catch (Exception e2) {
					e2.printStackTrace();
					// TODO: handle exception
				}

			}
		});

	}
}
