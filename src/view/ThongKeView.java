package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.QLKHModel;
import model.KhachHang;
import model.QLKHModel;
import model.Tinh;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Taskbar.State;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.QLKHController;

import javax.swing.JRadioButton;
import java.awt.Color;

public class ThongKeView extends JFrame {

	private JPanel contentPane;
	public QLKHModel model;
	private JButton btnQLTT;
	private JButton btnThongKe;
	private JButton btnDangxuat;
	private JButton btnNewButton;

	public ThongKeView() {
		setTitle("Thống Kê");
		this.model = new QLKHModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 818);
		URL urlIconManagement = QLKHView.class.getResource("icon_management.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIconManagement);
		this.setIconImage(img);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuFile);

		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuOpen);

		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuSave);

		JSeparator separator = new JSeparator();
		menuFile.add(separator);

		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuExit);

		JMenu menuAbout = new JMenu("About");
		menuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuAbout);

		JMenuItem menuAboutMe = new JMenuItem("About Me");
		menuAboutMe.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuAbout.add(menuAboutMe);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(253, 245, 230));
		panel.setBackground(new Color(255, 240, 245));
		panel.setBounds(430, 133, 814, 622);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Doanh Thu:");
		lblNewLabel.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_doanhthu.png"))));
		lblNewLabel.setForeground(new Color(148, 0, 211));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(24, 73, 780, 51);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1_1 = new JLabel(" VND ");
		lblNewLabel_1_1.setForeground(new Color(138, 43, 226));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1_1.setBounds(254, 73, 499, 51);
		panel.add(lblNewLabel_1_1);

		try {
		    int totalRevenue = 0;
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "");
		    PreparedStatement ps1 = null;
		    ResultSet rs11 = null;

		    String sql = "SELECT SUM(Tien_cuoc) AS total_revenue FROM khachhang";
		    ps1 = connection.prepareStatement(sql);
		    rs11 = ps1.executeQuery();

		    if (rs11.next()) {
		        totalRevenue = rs11.getInt("total_revenue");
		        lblNewLabel_1_1.setText(" " + totalRevenue + " VND");
		    }
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}
		
		JLabel lblGiDatac = new JLabel("Gói Data Được Đăng Kí Nhiều Nhất:");
		lblGiDatac.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_top1.png"))));
		lblGiDatac.setForeground(new Color(205, 133, 63));
		lblGiDatac.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblGiDatac.setBounds(29, 267, 757, 51);
		panel.add(lblGiDatac);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(new Color(210, 105, 30));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setBounds(622, 267, 164, 51);
		panel.add(lblNewLabel_1);

		try {
		    String goi_data = null;
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "");
		    PreparedStatement ps1 = null;
		    ResultSet rs11 = null;

		    String sql = "SELECT Goi_data, COUNT(*) AS occurrence_count FROM khachhang GROUP BY Goi_data ORDER BY occurrence_count DESC LIMIT 1";
		    ps1 = connection.prepareStatement(sql);
		    rs11 = ps1.executeQuery();

		    if (rs11.next()) {
		        goi_data = rs11.getString("Goi_data");
		        lblNewLabel_1.setText(goi_data);
		    }
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}

		
		JLabel lblKhchHngng = new JLabel("Khách Hàng Đăng Kí Nhiều Nhất :");
		lblKhchHngng.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_top1.png"))));
		lblKhchHngng.setForeground(new Color(50, 205, 50));
		lblKhchHngng.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblKhchHngng.setBounds(30, 466, 756, 51);
		panel.add(lblKhchHngng);
		
		JLabel lblNewLabel_1_2 = new JLabel("");
		lblNewLabel_1_2.setForeground(new Color(50, 205, 50));
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1_2.setBounds(600, 466, 164, 51);
		panel.add(lblNewLabel_1_2);
		
		try {
		    String ten_khach_hang = null;
		    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "");
		    PreparedStatement ps1 = null;
		    ResultSet rs11 = null;

		    String sql = "SELECT Ten_khach_hang, COUNT(*) AS occurrence_count FROM khachhang GROUP BY Ten_khach_hang ORDER BY occurrence_count DESC LIMIT 1";
		    ps1 = connection.prepareStatement(sql);
		    rs11 = ps1.executeQuery();

		    if (rs11.next()) {
		    	ten_khach_hang = rs11.getString("Ten_khach_hang");
		        lblNewLabel_1_2.setText(ten_khach_hang);
		    }
		} catch (SQLException e1) {
		    e1.printStackTrace();
		}

		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 181, 804, 2);
		panel.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 384, 804, 2);
		panel.add(scrollPane_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(135, 206, 235));
		panel_3.setBounds(420, 62, 813, 60);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(30, 144, 255));
		panel_4.setBounds(2, 1, 416, 121);
		contentPane.add(panel_4);
		panel_4.setLayout(null);

		JLabel label_hoten_1 = new JLabel("MANAGEMENT INTERNET");
		label_hoten_1.setForeground(new Color(240, 255, 255));
		label_hoten_1.setBounds(10, 31, 396, 54);
		panel_4.add(label_hoten_1);
		label_hoten_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(30, 144, 255));
		panel_5.setBounds(421, 1, 813, 60);
		contentPane.add(panel_5);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(169, 169, 169));
		panel_6.setBounds(2, 124, 415, 622);
		contentPane.add(panel_6);
		panel_6.setLayout(null);

		btnQLTT = new JButton("Quản Lí Thông Tin");
		btnQLTT.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_quanly.png"))));
		btnQLTT.setForeground(new Color(105, 105, 105));
		btnQLTT.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnQLTT.setBackground(new Color(169, 169, 169));
		btnQLTT.setBounds(0, 57, 415, 130);
		panel_6.add(btnQLTT);

		btnThongKe = new JButton("Thống Kê");
		btnThongKe.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_thongke.png"))));
		btnThongKe.setForeground(new Color(105, 105, 105));
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThongKe.setBackground(new Color(169, 169, 169));
		btnThongKe.setBounds(0, 249, 415, 130);
		panel_6.add(btnThongKe);

		btnDangxuat = new JButton("Đăng Xuất");
		btnDangxuat.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_dangxuat.png"))));
		btnDangxuat.setForeground(new Color(105, 105, 105));
		btnDangxuat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDangxuat.setBackground(new Color(169, 169, 169));
		btnDangxuat.setBounds(0, 442, 415, 130);
		btnDangxuat.addActionListener(null);
		panel_6.add(btnDangxuat);

		this.setVisible(true);
		btnDangxuat.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int dangxuat = JOptionPane.showConfirmDialog(
		                null, // Use null or provide the reference to the parent component
		                "Bạn có muốn đăng xuất ?",
		                "Đăng Xuất",
		                JOptionPane.YES_NO_OPTION);
		        if (dangxuat == JOptionPane.YES_OPTION) {
		            TrangDangNhap tdn = new TrangDangNhap();
		            dispose();
		        }
		    }
		});

		btnQLTT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QLKHView qlkh = new QLKHView();
				dispose();

			}
		});
	}

}