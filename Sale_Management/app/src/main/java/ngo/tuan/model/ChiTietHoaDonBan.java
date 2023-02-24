package ngo.tuan.model;

import java.io.Serializable;

public class ChiTietHoaDonBan implements Serializable {
    private int ID;
    private int SanPhamID;
    private int HoaDonBanID;
    private int SoLuong;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSanPhamID() {
        return SanPhamID;
    }

    public void setSanPhamID(int sanPhamID) {
        SanPhamID = sanPhamID;
    }

    public int getHoaDonBanID() {
        return HoaDonBanID;
    }

    public void setHoaDonBanID(int hoaDonBanID) {
        HoaDonBanID = hoaDonBanID;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
