package test;

import javax.swing.UIManager;

import view.QLKHView;
import view.TrangDangNhap;
public class Test {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new TrangDangNhap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}