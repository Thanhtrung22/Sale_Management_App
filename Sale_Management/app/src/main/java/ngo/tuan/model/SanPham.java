package ngo.tuan.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    int ID;
    String TenSanPham;
    String HinhAnhSanPham;
    int DanhMucID;
    int SoLuong;
    int GiaBan;
    String MoTa;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        TenSanPham = tenSanPham;
    }

    public String getHinhAnhSanPham() {
        return HinhAnhSanPham;
    }

    public void setHinhAnhSanPham(String hinhAnhSanPham) {
        HinhAnhSanPham = hinhAnhSanPham;
    }

    public int getDanhMucID() {
        return DanhMucID;
    }

    public void setDanhMucID(int danhMucID) {
        DanhMucID = danhMucID;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int giaBan) {
        GiaBan = giaBan;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    @Override
    public String toString() {
        return this.TenSanPham;
    }
}
