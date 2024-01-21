package model;

import java.util.ArrayList;
import java.util.Objects;

public class QLKHModel {
	private ArrayList <KhachHang> dsKhachHang;
	private String luaChon;
	private String tenFile;

	public QLKHModel() {
		this.dsKhachHang = new ArrayList();
		this.luaChon = "";
		this.tenFile = "";
	}

	public QLKHModel(ArrayList dsKhachHang) {
		this.dsKhachHang = dsKhachHang;
	}
	
	public ArrayList<KhachHang> getDsKhachHang() {
		return dsKhachHang;
	}

	public void setDsKhachHang(ArrayList<KhachHang> dsKhachHang) {
		this.dsKhachHang = dsKhachHang;
	}

	public void insert(KhachHang khachHang) {
		this.dsKhachHang.add(khachHang);
	}
	
	public void delete(KhachHang khachHang) {
		this.dsKhachHang.remove(khachHang);
	}
	
	public void update(KhachHang khachHang) {
		this.dsKhachHang.remove(khachHang);
		this.dsKhachHang.add(khachHang);
	}

	public String getLuaChon() {
		return luaChon;
	}

	public void setLuaChon(String luaChon) {
		this.luaChon = luaChon;
	}

	public String getTenFile() {
		return tenFile;
	}

	public void setTenFile(String tenFile) {
		this.tenFile = tenFile;
	}

	public boolean kiemTraTonTai(KhachHang kh) {
		for(KhachHang khachHang: dsKhachHang) {
			if(khachHang.getMaKhachHang() == kh.getMaKhachHang())
				return true;
			
		}
		return false;
	}

}

