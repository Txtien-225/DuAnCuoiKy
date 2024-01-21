package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class KhachHang implements Serializable {
	private String maKhachHang;
	private String tenKhachHang;
	private Tinh queQuan;
	private String ngaySinh;
	private String chuThich;
	private String goiData, hanSuDung,dungluongData, tienCuoc;
	private String soDT;
	

	public KhachHang(String maKhachHang, String tenKhachHang, Tinh queQuan, String ngaySinh, String chuThich,
			String goiData, String hanSuDung, String dungluongData, String tienCuoc, String soDT) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.queQuan = queQuan;
		this.ngaySinh = ngaySinh;
		this.chuThich = chuThich;
		this.goiData = goiData;
		this.hanSuDung = hanSuDung;
		this.dungluongData = dungluongData;
		this.tienCuoc = tienCuoc;
		this.soDT = soDT;
	}
	
	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}
	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}
	public Tinh getQueQuan() {
		return queQuan;
	}
	public void setQueQuan(Tinh queQuan) {
		this.queQuan = queQuan;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	
	public String getChuThich() {
		return chuThich;
	}
	public void setChuThich(String chuThich) {
		this.chuThich = chuThich;
	}
	public String getGoiData() {
		return goiData;
	}
	public void setGoiData(String goiData) {
		this.goiData = goiData;
	}
	public String getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(String hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public String getDungluongData() {
		return dungluongData;
	}
	public void setDungluongData(String dungluongData) {
		this.dungluongData = dungluongData;
	}
	public String getTienCuoc() {
		return tienCuoc;
	}
	public void setTienCuoc(String tienCuoc) {
		this.tienCuoc = tienCuoc;
	}
	
	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chuThich, dungluongData, goiData, hanSuDung, maKhachHang, ngaySinh, queQuan, soDT,
				tenKhachHang, tienCuoc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(chuThich, other.chuThich) && Objects.equals(dungluongData, other.dungluongData)
				&& Objects.equals(goiData, other.goiData) && Objects.equals(hanSuDung, other.hanSuDung)
				&& Objects.equals(maKhachHang, other.maKhachHang) && Objects.equals(ngaySinh, other.ngaySinh)
				&& Objects.equals(queQuan, other.queQuan) && Objects.equals(soDT, other.soDT)
				&& Objects.equals(tenKhachHang, other.tenKhachHang) && Objects.equals(tienCuoc, other.tienCuoc);
	}
	
}
