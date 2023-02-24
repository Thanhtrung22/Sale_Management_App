package ngo.tuan.model;

import java.io.Serializable;
import java.sql.Date;

public class KhachHang implements Serializable {
    private int ID;
    private String HoTen;
    private String GioiTinh;
    private String Email;
    private int Phone;
    private String DiaChi;
    private String DiaChiMuaHang;
    private String DiaChiNhanHang;
    private Date NgaySinh;
    private Date NgayDangKy;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String hoTen) {
        HoTen = hoTen;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getDiaChiMuaHang() {
        return DiaChiMuaHang;
    }

    public void setDiaChiMuaHang(String diaChiMuaHang) {
        DiaChiMuaHang = diaChiMuaHang;
    }

    public String getDiaChiNhanHang() {
        return DiaChiNhanHang;
    }

    public void setDiaChiNhanHang(String diaChiNhanHang) {
        DiaChiNhanHang = diaChiNhanHang;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public Date getNgayDangKy() {
        return NgayDangKy;
    }

    public void setNgayDangKy(Date ngayDangKy) {
        NgayDangKy = ngayDangKy;
    }

//    @Override
//    public String toString() {
//        return this.HoTen;
//    }
}
