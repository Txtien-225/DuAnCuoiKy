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
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
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

public class QLKHView<Int> extends JFrame {

	private JPanel contentPane;
	public QLKHModel model;
	public JTextField textField_MaKhachHang_TimKiem;
	public JTable table;
	public JTextField textField_ID;
	public JTextField textField_HoVaTen;
	public JTextField textField_NgaySinh;
	public JTextField textField_goiData;
	public JTextField textField_tienCuoc;
	public JTextField textField_soDT;
	public JTextField textField_dungluongData;
	public JTextField textField_ChuThich;

	public JComboBox comboBox_queQuan;
	public JTextField textField_hansd;
	private JButton btnLuu;
	private JTextField textField_hoTen_TimKiem;
	private JButton btnTim;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton btnlammoi;
	private DefaultTableModel model_table;
	private JButton btnQLTT;
	private JButton btnThongKe;
	private JButton btnDangxuat;
	private AbstractButton btnSua;
	private JButton btnCapNhat;

	public QLKHView() {
		setTitle("Trang Chủ");
		this.model = new QLKHModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 818);
		URL urlIconManagement = QLKHView.class.getResource("icon_management.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urlIconManagement);
		this.setIconImage(img);

		Action action = new QLKHController(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuFile);

		JSeparator separator = new JSeparator();
		menuFile.add(separator);

		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(action);
		menuExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuFile.add(menuExit);

		JMenu menuAbout = new JMenu("About");
		menuAbout.addActionListener(action);
		menuAbout.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		menuBar.add(menuAbout);

		JMenuItem menuAboutMe = new JMenuItem("About Me");
		menuAboutMe.addActionListener(action);
		menuAboutMe.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuAbout.add(menuAboutMe);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Box verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBounds(44, 144, -28, -35);
		contentPane.add(verticalBox_1);
		model_table = new DefaultTableModel(new Object[][] {}, new String[] { "Mã khách hàng", "Họ và tên", "Quê quán",
				"Ngày sinh", "Chú Thích", "Gói Data ", "Dung lượng Data", "Tiền cước", "Hạn sử dụng ", "Số ĐT" });

		try {
			// Bước 1 : Tạo kết nối CSDL
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "");

			// Bước 2 : tạo ra đối tượng statement

			String sql = "SELECT * FROM khachhang Where ma_khach_hang ";
			PreparedStatement pst = connection.prepareStatement(sql);

			// Bước 3 : thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();

			// Clear the table model outside the loop
			model_table.setRowCount(0); // Clear previous data
			model_table.fireTableDataChanged();

			// Bước 4 : Iterate through the ResultSet and add rows to the table model
			while (rs.next()) {
				String ma_khach_hang = rs.getString("ma_khach_hang");
				String ten_khach_hang = rs.getString("ten_khach_hang");
				String quequan = rs.getString("que_quan");
				String ngaysinh = rs.getString("ngay_sinh");
				String chu_thich = rs.getString("chu_Thich");
				String goi_data = rs.getString("goi_Data");
				String dung_luong_data = rs.getString("dung_luong_data");
				String tiencuoc = rs.getString("tien_cuoc");
				String han_su_dung = rs.getString("han_su_dung");
				String sodt = rs.getString("so_dt");

				// Add fetched data to the table model without resetting it
				model_table.addRow(new Object[] { ma_khach_hang, ten_khach_hang, quequan, ngaysinh, chu_thich, goi_data,
						dung_luong_data, tiencuoc, han_su_dung, sodt });
			}

			// Close ResultSet, Statement, and Connection when done
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(15, 628, 733, -22);
		separator_2.setForeground(Color.BLACK);
		contentPane.add(separator_2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		panel.setBounds(421, 668, 813, 78);
		contentPane.add(panel);
		panel.setLayout(null);

		btnLuu = new JButton("L\u01B0u");
		btnLuu.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_save.png"))));
		btnLuu.setForeground(new Color(0, 128, 0));
		btnLuu.setBackground(new Color(0, 100, 0));
		btnLuu.setBounds(350, 25, 122, 42);
		panel.add(btnLuu);
		btnLuu.addActionListener(action);
		btnLuu.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnSua = new JButton("Sửa");
		btnSua.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_suachua.png"))));
		btnSua.setForeground(new Color(0, 100, 0));
		btnSua.setBackground(new Color(0, 100, 0));
		btnSua.setBounds(520, 25, 122, 42);
		panel.add(btnSua);
		btnSua.addActionListener(action);
		btnSua.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnThem = new JButton("Th\u00EAm");
		btnThem.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_add.png"))));
		btnThem.setForeground(new Color(0, 100, 0));
		btnThem.setBackground(new Color(0, 100, 0));
		btnThem.setBounds(10, 25, 114, 42);
		panel.add(btnThem);
		btnThem.addActionListener(action);
		btnThem.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnXoa = new JButton("X\u00F3a");
		btnXoa.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_delete.png"))));
		btnXoa.setForeground(new Color(0, 100, 0));
		btnXoa.setBackground(new Color(0, 100, 0));
		btnXoa.setBounds(190, 25, 122, 42);
		panel.add(btnXoa);
		btnXoa.addActionListener(action);
		btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 18));

		btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_update.png"))));
		btnCapNhat.setForeground(new Color(0, 100, 0));
		btnCapNhat.setBackground(new Color(0, 100, 0));
		btnCapNhat.setBounds(681, 25, 122, 42);
		panel.add(btnCapNhat);
		btnCapNhat.addActionListener(action);
		btnCapNhat.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(421, 390, 813, 278);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		textField_goiData = new JTextField();
		textField_goiData.setBounds(593, 55, 166, 29);
		panel_1.add(textField_goiData);
		textField_goiData.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_goiData.setColumns(10);

		textField_dungluongData = new JTextField();
		textField_dungluongData.setBounds(593, 95, 166, 29);
		panel_1.add(textField_dungluongData);
		textField_dungluongData.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_dungluongData.setColumns(10);

		textField_tienCuoc = new JTextField();
		textField_tienCuoc.setBounds(593, 135, 166, 29);
		panel_1.add(textField_tienCuoc);
		textField_tienCuoc.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_tienCuoc.setColumns(10);

		textField_hansd = new JTextField();
		textField_hansd.setBounds(593, 175, 166, 29);
		panel_1.add(textField_hansd);
		textField_hansd.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_hansd.setColumns(10);

		textField_soDT = new JTextField();
		textField_soDT.setBounds(593, 215, 166, 29);
		panel_1.add(textField_soDT);
		textField_soDT.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_soDT.setColumns(10);

		JLabel lbl_GoiData = new JLabel("Gói Data");
		lbl_GoiData.setBounds(381, 55, 102, 29);
		panel_1.add(lbl_GoiData);
		lbl_GoiData.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel lbl_DLData = new JLabel("Dung Lượng Data");
		lbl_DLData.setBounds(381, 98, 166, 23);
		panel_1.add(lbl_DLData);
		lbl_DLData.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel lbl_TienCuoc = new JLabel("Tiền Cước");
		lbl_TienCuoc.setBounds(381, 138, 102, 23);
		panel_1.add(lbl_TienCuoc);
		lbl_TienCuoc.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel lbl_HanSuDung = new JLabel("Hạn Sử Dụng");
		lbl_HanSuDung.setBounds(381, 178, 123, 23);
		panel_1.add(lbl_HanSuDung);
		lbl_HanSuDung.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel lbl_SoDienThoai = new JLabel("Số Điện  Thoại");
		lbl_SoDienThoai.setBounds(381, 218, 144, 23);
		panel_1.add(lbl_SoDienThoai);
		lbl_SoDienThoai.setFont(new Font("Tahoma", Font.PLAIN, 19));

		textField_ID = new JTextField();
		textField_ID.setBounds(162, 55, 166, 29);
		panel_1.add(textField_ID);
		textField_ID.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_ID.setColumns(10);

		textField_HoVaTen = new JTextField();
		textField_HoVaTen.setBounds(162, 95, 166, 29);
		panel_1.add(textField_HoVaTen);
		textField_HoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_HoVaTen.setColumns(10);

		comboBox_queQuan = new JComboBox();
		comboBox_queQuan.setBounds(162, 135, 166, 29);
		comboBox_queQuan.addItem("");
		ArrayList<Tinh> listTinh = Tinh.getDSTinh();
		for (Tinh tinh : listTinh) {
			comboBox_queQuan.addItem(tinh.getTenTinh());
		}
		panel_1.add(comboBox_queQuan);

		textField_NgaySinh = new JTextField();
		textField_NgaySinh.setBounds(162, 175, 166, 29);
		panel_1.add(textField_NgaySinh);
		textField_NgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_NgaySinh.setColumns(10);

		textField_ChuThich = new JTextField();
		textField_ChuThich.setBounds(162, 216, 166, 26);
		panel_1.add(textField_ChuThich);
		textField_ChuThich.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_ChuThich.setColumns(10);

		JLabel lbl_maKhachhang = new JLabel("Mã khách hàng");
		lbl_maKhachhang.setBounds(10, 42, 144, 54);
		panel_1.add(lbl_maKhachhang);
		lbl_maKhachhang.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel label_HoVaTen = new JLabel("H\u1ECD v\u00E0 t\u00EAn");
		label_HoVaTen.setBounds(10, 82, 102, 54);
		panel_1.add(label_HoVaTen);
		label_HoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel label_HoVaTen_1 = new JLabel("Qu\u00EA qu\u00E1n");
		label_HoVaTen_1.setBounds(10, 122, 102, 54);
		panel_1.add(label_HoVaTen_1);
		label_HoVaTen_1.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel label_NgaySinh = new JLabel("Ng\u00E0y sinh");
		label_NgaySinh.setBounds(10, 162, 155, 54);
		panel_1.add(label_NgaySinh);
		label_NgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel label_ID_1 = new JLabel("Chú thích");
		label_ID_1.setBounds(10, 209, 102, 41);
		panel_1.add(label_ID_1);
		label_ID_1.setFont(new Font("Tahoma", Font.PLAIN, 19));

		JLabel lblThmKhchHng = new JLabel("THÊM KHÁCH HÀNG");
		lblThmKhchHng.setForeground(new Color(255, 0, 0));
		lblThmKhchHng.setBounds(321, 3, 226, 41);
		panel_1.add(lblThmKhchHng);
		lblThmKhchHng.setFont(new Font("Tahoma", Font.BOLD, 20));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(211, 211, 211));
		panel_2.setBounds(421, 124, 813, 265);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setModel(model_table);

		table.setRowHeight(25);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(18, 39, 787, 214);
		panel_2.add(scrollPane);

		JLabel lblDanhSchKhch = new JLabel("DANH SÁCH THÔNG TIN");
		lblDanhSchKhch.setForeground(new Color(255, 0, 0));
		lblDanhSchKhch.setBounds(311, 0, 271, 41);
		panel_2.add(lblDanhSchKhch);
		lblDanhSchKhch.setFont(new Font("Tahoma", Font.BOLD, 20));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(135, 206, 235));
		panel_3.setBounds(420, 62, 813, 60);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		btnTim = new JButton("T\u00ECm");
		btnTim.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_search.png"))));
		btnTim.setForeground(new Color(0, 100, 0));
		btnTim.setBackground(new Color(0, 100, 0));
		btnTim.setBounds(678, 5, 116, 54);
		panel_3.add(btnTim);
		btnTim.addActionListener(action);
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel label_hoten = new JLabel("Tên KH");
		label_hoten.setBounds(19, 3, 92, 54);
		panel_3.add(label_hoten);
		label_hoten.setFont(new Font("Tahoma", Font.PLAIN, 19));

		textField_hoTen_TimKiem = new JTextField();
		textField_hoTen_TimKiem.setForeground(new Color(0, 100, 0));
		textField_hoTen_TimKiem.setBounds(132, 9, 155, 48);
		panel_3.add(textField_hoTen_TimKiem);
		textField_hoTen_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_hoTen_TimKiem.setColumns(10);

		JLabel lbl_maKhachHang = new JLabel("Mã khách hàng");
		lbl_maKhachHang.setBounds(318, 2, 155, 54);
		panel_3.add(lbl_maKhachHang);
		lbl_maKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 19));

		textField_MaKhachHang_TimKiem = new JTextField();
		textField_MaKhachHang_TimKiem.setBounds(486, 6, 154, 49);
		panel_3.add(textField_MaKhachHang_TimKiem);
		textField_MaKhachHang_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_MaKhachHang_TimKiem.setColumns(10);

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
		btnQLTT.addActionListener(action);
		panel_6.add(btnQLTT);

		btnThongKe = new JButton("Thống Kê");
		btnThongKe.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_thongke.png"))));
		btnThongKe.setForeground(new Color(105, 105, 105));
		btnThongKe.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThongKe.setBackground(new Color(169, 169, 169));
		btnThongKe.setBounds(0, 249, 415, 130);
		btnThongKe.addActionListener(action);
		panel_6.add(btnThongKe);

		btnDangxuat = new JButton("Đăng Xuất");
		btnDangxuat.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QLKHView.class.getResource("icon_dangxuat.png"))));
		btnDangxuat.setForeground(new Color(105, 105, 105));
		btnDangxuat.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDangxuat.setBackground(new Color(169, 169, 169));
		btnDangxuat.setBounds(0, 442, 415, 130);
		btnDangxuat.addActionListener(action);
		panel_6.add(btnDangxuat);

		this.setVisible(true);
	}

	public void xoaForm() {
		textField_ID.setText("");
		textField_HoVaTen.setText("");
		textField_MaKhachHang_TimKiem.setText("");
		textField_NgaySinh.setText("");
		textField_goiData.setText("");
		textField_dungluongData.setText("");
		textField_tienCuoc.setText("");
		textField_soDT.setText("");
		comboBox_queQuan.setSelectedIndex(-1);
		textField_ChuThich.setText("");
	}

	public void themKhachHangVaoTable(KhachHang kh) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		model_table.addRow(new Object[] { kh.getMaKhachHang() + "", kh.getTenKhachHang(), kh.getQueQuan().getTenTinh(),
				kh.getNgaySinh(), kh.getChuThich(), kh.getGoiData(), kh.getDungluongData(), kh.getTienCuoc(),
				kh.getHanSuDung(), kh.getSoDT() + "", });
	}

	public void themHoacCapNhatKhachHang(KhachHang kh) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		if (!this.model.kiemTraTonTai(kh)) {
			this.model.insert(kh);
			this.themKhachHangVaoTable(kh);
			String MaKH = textField_ID.getText();
			String HoTen = textField_HoVaTen.getText();
			String que_quan = (String) comboBox_queQuan.getSelectedItem();
			String ngay_sinh = textField_NgaySinh.getText();
			String chu_Thich = textField_ChuThich.getText();
			String goi_Data = textField_goiData.getText();
			String dungluongdata = textField_dungluongData.getText();
			String tien_cuoc = textField_tienCuoc.getText();
			String han_sudung = textField_hansd.getText();
			String so_dt = textField_soDT.getText();
			int len = so_dt.length();
			if (len > 0) {
				JOptionPane.showMessageDialog(btnLuu, "Lưu thành công");
			}
			try {
				Connection connection = DriverManager.getConnection("jdbc:mySQL://localhost:3306/swing_demo", "root",
						"");

				Statement sta = connection.createStatement();

				String query = "INSERT INTO khachhang values('" + MaKH + "','" + HoTen + "','" + que_quan + "','"
						+ ngay_sinh + "','" + chu_Thich + "','" + goi_Data + "','" + dungluongdata + "','" + tien_cuoc
						+ "','" + han_sudung + "','" + so_dt + "')";

				sta.executeUpdate(query);
				connection.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else {
			this.model.update(kh);
			int soLuongDong = model_table.getRowCount();
			for (int i = 0; i < soLuongDong; i++) {
				String id = model_table.getValueAt(i, 0) + "";
				if (id.equals(kh.getMaKhachHang() + "")) {
					model_table.setValueAt(kh.getMaKhachHang() + "", i, 0);
					model_table.setValueAt(kh.getTenKhachHang() + "", i, 1);
					model_table.setValueAt(kh.getQueQuan().getTenTinh() + "", i, 2);
					model_table.setValueAt(kh.getNgaySinh() + "", i, 3);
					model_table.setValueAt(kh.getChuThich() + "", i, 4);
					model_table.setValueAt(kh.getGoiData() + "", i, 5);
					model_table.setValueAt(kh.getDungluongData() + "", i, 6);
					model_table.setValueAt(kh.getTienCuoc() + "", i, 7);
					model_table.setValueAt(kh.getHanSuDung() + "", i, 8);
					model_table.setValueAt(kh.getSoDT() + "", i, 9);
				}
			}
		}
	}

	public KhachHang getKhachHangDangChon() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();

		String maKhachHang = model_table.getValueAt(i_row, 0) + "";
		String tenKhachHang = model_table.getValueAt(i_row, 1) + "";
		Tinh tinh = Tinh.getTinhByTen(model_table.getValueAt(i_row, 2) + "");
		String ngaySinh = model_table.getValueAt(i_row, 3) + "";
		String chuThich = model_table.getValueAt(i_row, 4) + "";
		String goidata = model_table.getValueAt(i_row, 5) + "";
		String dungluongdata = model_table.getValueAt(i_row, 6) + "";
		String tiencuoc = model_table.getValueAt(i_row, 7) + "";
		String hansudung = model_table.getValueAt(i_row, 8) + "";
		String sodt = model_table.getValueAt(i_row, 9) + "";

		KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, tinh, ngaySinh, chuThich, goidata, dungluongdata,
				tiencuoc, hansudung, sodt);
		return kh;
	}

	public void hienThiThongTinKhachHangDaChon() {
		KhachHang kh = getKhachHangDangChon();

		this.textField_ID.setText(kh.getMaKhachHang() + "");
		this.textField_HoVaTen.setText(kh.getTenKhachHang() + "");
		this.comboBox_queQuan.setSelectedItem(kh.getQueQuan().getTenTinh());
		this.textField_NgaySinh.setText(kh.getNgaySinh() + "");
		this.textField_ChuThich.setText(kh.getChuThich());
		this.textField_goiData.setText(kh.getGoiData() + "");
		this.textField_dungluongData.setText(kh.getDungluongData() + "");
		this.textField_tienCuoc.setText(kh.getTienCuoc() + "");
		this.textField_hansd.setText(kh.getHanSuDung() + "");
		this.textField_soDT.setText(kh.getSoDT() + "");

	}

	public void thucHienXoa() {
	    DefaultTableModel model_table = (DefaultTableModel) table.getModel();
	    int i_row = table.getSelectedRow();

	    // Kiểm tra xem đã chọn dòng nào chưa
	    if (i_row == -1) {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa.");
	        return;
	    }

	    int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn xóa dòng đã chọn?");
	    if (luaChon == JOptionPane.YES_OPTION) {
	        try {
	            // Lấy thông tin từ dòng đã chọn
	            String maKhachHang = (String) model_table.getValueAt(i_row, 0);

	            // Kết nối CSDL
	            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "");

	            // Tạo Prepared Statement
	            String sql = "DELETE FROM khachhang WHERE ma_khach_hang = ?";
	            PreparedStatement st = connection.prepareStatement(sql);

	            // Thiết lập giá trị cho tham số
	            st.setString(1, maKhachHang);

	            // Thực hiện xóa dữ liệu
	            st.executeUpdate();

	            // Cập nhật model_table và bảng hiển thị sau khi xóa
	            model_table.removeRow(i_row);
	            JOptionPane.showMessageDialog(this, "Xóa dữ liệu thành công.");

	            // Đóng PreparedStatement
	            st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu: " + e.getMessage());
	        }
	    }
	}



	public void thucHienThemKhachHang() {
		// Get du lieu
		String maKhachHang = this.textField_ID.getText();
		String tenKhachHang = this.textField_HoVaTen.getText();
		int queQuan = this.comboBox_queQuan.getSelectedIndex() - 1;
		Tinh tinh = Tinh.getTinhById(queQuan);
		String ngaySinh = this.textField_NgaySinh.getText();
		String chuThich = this.textField_ChuThich.getText();
		String goidata = this.textField_goiData.getText();
		String dungluongdata = this.textField_dungluongData.getText();
		String tiencuoc = this.textField_tienCuoc.getText();
		String hansudung = this.textField_hansd.getText();
		String sodt = this.textField_soDT.getText();

		KhachHang kh = new KhachHang(maKhachHang, tenKhachHang, tinh, ngaySinh, chuThich, goidata, hansudung,
				dungluongdata, tiencuoc, sodt);
		this.themHoacCapNhatKhachHang(kh);
	}

	public void thucHienTim() {
	    model_table.setRowCount(0);
	    model_table.fireTableDataChanged();

	    try {
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "");

	        if (!textField_hoTen_TimKiem.getText().isEmpty() && !textField_MaKhachHang_TimKiem.getText().isEmpty()) {
	            try {
	                String sql1 = "SELECT * FROM khachhang WHERE ten_khach_hang = '" + textField_hoTen_TimKiem.getText() + "' AND ma_khach_hang = '" + textField_MaKhachHang_TimKiem.getText() + "';";
	                Statement st1 = connection.createStatement();
	                ResultSet rs = st1.executeQuery(sql1);
	                while (rs.next()) {
	                    String ma_khach_hang = rs.getString("ma_khach_hang");
	                    String ten_khach_hang = rs.getString("ten_khach_hang");
	                    String quequan = rs.getString("que_quan");
	                    String ngaysinh = rs.getString("ngay_sinh");
	                    String chu_thich = rs.getString("chu_Thich");
	                    String goi_data = rs.getString("goi_Data");
	                    String dung_luong_data = rs.getString("dung_luong_data");
	                    String tiencuoc = rs.getString("tien_cuoc");
	                    String han_su_dung = rs.getString("han_su_dung");
	                    String sodt = rs.getString("so_dt");

	                    // Add fetched data to the table model without resetting it
	                    model_table.addRow(new Object[] { ma_khach_hang, ten_khach_hang, quequan, ngaysinh, chu_thich, goi_data,
	                            dung_luong_data, tiencuoc, han_su_dung, sodt });
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	                System.out.println("Error executing SQL query for hoTen: " + e1.getMessage());
	            }
	        } else if (!textField_MaKhachHang_TimKiem.getText().isEmpty()) {
	            try {
	                String sql1 = "SELECT * FROM khachhang WHERE ma_khach_hang = '" + textField_MaKhachHang_TimKiem.getText() + "';";
	                Statement st1 = connection.createStatement();
	                ResultSet rs = st1.executeQuery(sql1);
	                while (rs.next()) {
	                    String ma_khach_hang = rs.getString("ma_khach_hang");
	                    String ten_khach_hang = rs.getString("ten_khach_hang");
	                    String quequan = rs.getString("que_quan");
	                    String ngaysinh = rs.getString("ngay_sinh");
	                    String chu_thich = rs.getString("chu_Thich");
	                    String goi_data = rs.getString("goi_Data");
	                    String dung_luong_data = rs.getString("dung_luong_data");
	                    String tiencuoc = rs.getString("tien_cuoc");
	                    String han_su_dung = rs.getString("han_su_dung");
	                    String sodt = rs.getString("so_dt");

	                    // Add fetched data to the table model without resetting it
	                    model_table.addRow(new Object[] { ma_khach_hang, ten_khach_hang, quequan, ngaysinh, chu_thich, goi_data,
	                            dung_luong_data, tiencuoc, han_su_dung, sodt });
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	                System.out.println("Error executing SQL query for MaKhachHang: " + e1.getMessage());
	            }
	        } else if (!textField_hoTen_TimKiem.getText().isEmpty()){
	            try {
	                String sql1 = "SELECT * FROM khachhang WHERE ten_khach_hang = '" + textField_hoTen_TimKiem.getText() + "';";
	                Statement st1 = connection.createStatement();
	                ResultSet rs = st1.executeQuery(sql1);
	                while (rs.next()) {
	                    String ma_khach_hang = rs.getString("ma_khach_hang");
	                    String ten_khach_hang = rs.getString("ten_khach_hang");
	                    String quequan = rs.getString("que_quan");
	                    String ngaysinh = rs.getString("ngay_sinh");
	                    String chu_thich = rs.getString("chu_Thich");
	                    String goi_data = rs.getString("goi_Data");
	                    String dung_luong_data = rs.getString("dung_luong_data");
	                    String tiencuoc = rs.getString("tien_cuoc");
	                    String han_su_dung = rs.getString("han_su_dung");
	                    String sodt = rs.getString("so_dt");

	                    // Add fetched data to the table model without resetting it
	                    model_table.addRow(new Object[] { ma_khach_hang, ten_khach_hang, quequan, ngaysinh, chu_thich, goi_data,
	                            dung_luong_data, tiencuoc, han_su_dung, sodt });
	                }
	            } catch (SQLException e1) {
	                e1.printStackTrace();
	                System.out.println("Error executing SQL query for else case: " + e1.getMessage());
	            }
	        }
	    } catch (SQLException e1) {
	        e1.printStackTrace();
	        System.out.println("Error connecting to the database: " + e1.getMessage());
	    }
	}

	public void hienThiAbout() {
		JOptionPane.showMessageDialog(this, "Phần mềm quản lý tiền truy cập Internet!");
	}

	public void thoatKhoiChuongTrinh() {
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát khỏi chương trình?", "Exit",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void thucHienDangXuat() {
		int dangxuat = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất ?", "Đăng Xuất",
				JOptionPane.YES_NO_OPTION);
		if (dangxuat == JOptionPane.YES_OPTION) {
			TrangDangNhap tdn = new TrangDangNhap();
			dispose();
		}

	}

	public void ThongKe() {
		ThongKeView tk = new ThongKeView();
		dispose();
	}

	public void thucHienHuyTim() {
		try {
			// Bước 1 : Tạo kết nối CSDL
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/swing_demo", "root", "");

			// Bước 2 : tạo ra đối tượng statement

			String sql = "SELECT * FROM khachhang Where ma_khach_hang ";
			PreparedStatement pst = connection.prepareStatement(sql);

			// Bước 3 : thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = pst.executeQuery();

			// Clear the table model outside the loop
			model_table.setRowCount(0); // Clear previous data
			model_table.fireTableDataChanged();

			// Bước 4 : Iterate through the ResultSet and add rows to the table model
			while (rs.next()) {
				String ma_khach_hang = rs.getString("ma_khach_hang");
				String ten_khach_hang = rs.getString("ten_khach_hang");
				String quequan = rs.getString("que_quan");
				String ngaysinh = rs.getString("ngay_sinh");
				String chu_thich = rs.getString("chu_Thich");
				String goi_data = rs.getString("goi_Data");
				String dung_luong_data = rs.getString("dung_luong_data");
				String tiencuoc = rs.getString("tien_cuoc");
				String han_su_dung = rs.getString("han_su_dung");
				String sodt = rs.getString("so_dt");

				// Add fetched data to the table model without resetting it
				model_table.addRow(new Object[] { ma_khach_hang, ten_khach_hang, quequan, ngaysinh, chu_thich, goi_data,
						dung_luong_data, tiencuoc, han_su_dung, sodt });
			}

			// Close ResultSet, Statement, and Connection when done
			rs.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}

}