package Main;

import javax.swing.UIManager;

import view.TrangDangNhap;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new TrangDangNhap();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}