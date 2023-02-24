package ngo.tuan.model;

import java.io.Serializable;
import java.sql.Date;

public class HoaDonBan implements Serializable {
    private int ID;
    private int KhachHangID;
    private String DiaChiNguoiMua;
    private String DiaChiNhanHang;
    private int TrangThaiHoaDonID;
    private Date NgayDatHang;
    private Date NgayGiaoHang;
    private int NVGiaoHangID;
    private int HinhThucThanhToanID;
    private String TrangThaiThanhToan;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getKhachHangID() {
        return KhachHangID;
    }

    public void setKhachHangID(int khachHangID) {
        KhachHangID = khachHangID;
    }

    public String getDiaChiNguoiMua() {
        return DiaChiNguoiMua;
    }

    public void setDiaChiNguoiMua(String diaChiNguoiMua) {
        DiaChiNguoiMua = diaChiNguoiMua;
    }

    public String getDiaChiNhanHang() {
        return DiaChiNhanHang;
    }

    public void setDiaChiNhanHang(String diaChiNhanHang) {
        DiaChiNhanHang = diaChiNhanHang;
    }

    public int getTrangThaiHoaDonID() {
        return TrangThaiHoaDonID;
    }

    public void setTrangThaiHoaDonID(int trangThaiHoaDonID) {
        TrangThaiHoaDonID = trangThaiHoaDonID;
    }

    public Date getNgayDatHang() {
        return NgayDatHang;
    }

    public void setNgayDatHang(Date ngayDatHang) {
        NgayDatHang = ngayDatHang;
    }

    public Date getNgayGiaoHang() {
        return NgayGiaoHang;
    }

    public void setNgayGiaoHang(Date ngayGiaoHang) {
        NgayGiaoHang = ngayGiaoHang;
    }

    public int getNVGiaoHangID() {
        return NVGiaoHangID;
    }

    public void setNVGiaoHangID(int NVGiaoHangID) {
        this.NVGiaoHangID = NVGiaoHangID;
    }

    public int getHinhThucThanhToanID() {
        return HinhThucThanhToanID;
    }

    public void setHinhThucThanhToanID(int hinhThucThanhToanID) {
        HinhThucThanhToanID = hinhThucThanhToanID;
    }

    public String getTrangThaiThanhToan() {
        return TrangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        TrangThaiThanhToan = trangThaiThanhToan;
    }
}
