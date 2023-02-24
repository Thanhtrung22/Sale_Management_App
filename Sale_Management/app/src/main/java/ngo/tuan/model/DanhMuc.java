package ngo.tuan.model;

import java.io.Serializable;

public class DanhMuc implements Serializable {
    int ID;
    String TenDanhMuc;
    String HinhAnhDanhMuc;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenDanhMuc() {
        return TenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        TenDanhMuc = tenDanhMuc;
    }

    public String getHinhAnhDanhMuc() {
        return HinhAnhDanhMuc;
    }

    public void setHinhAnhDanhMuc(String hinhAnhDanhMuc) {
        HinhAnhDanhMuc = hinhAnhDanhMuc;
    }

    @Override
    public String toString() {
        return this.TenDanhMuc;
    }
}
