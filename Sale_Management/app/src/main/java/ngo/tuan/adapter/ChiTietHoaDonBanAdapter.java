package ngo.tuan.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ngo.tuan.androidbanhang.ChiTietSanPhamActivity;
import ngo.tuan.androidbanhang.R;
import ngo.tuan.model.ChiTietHoaDonBan;
import ngo.tuan.model.SanPham;

public class ChiTietHoaDonBanAdapter extends ArrayAdapter<ChiTietHoaDonBan> {
    Activity context;
    int resource;
    List<ChiTietHoaDonBan> objects;

    public ChiTietHoaDonBanAdapter(@NonNull Activity context, int resource, @NonNull List<ChiTietHoaDonBan> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        ImageView imgSanPhamCTHD = row.findViewById(R.id.imgSanPhamCTHT);
        TextView txtTenSPCTHD = row.findViewById(R.id.txtTenSPCTHD);
        TextView txtSoLuongCTHD = row.findViewById(R.id.txtSoLuongCTHD);
        TextView txtGiaCTHD = row.findViewById(R.id.txtGiaCTHD);

        ChiTietHoaDonBan cthd = this.objects.get(position);

        ArrayList<SanPham> dsSanPhamDatMua = ChiTietSanPhamActivity.dsSanPhamDatMua;

        for (SanPham sp : dsSanPhamDatMua) {
            if(cthd.getSanPhamID()==sp.getID()) {
                txtTenSPCTHD.setText(sp.getTenSanPham());
                byte[] decodedString = Base64.decode(sp.getHinhAnhSanPham(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imgSanPhamCTHD.setImageBitmap(decodedByte);
                txtSoLuongCTHD.setText("SỐ LƯỢNG: " + cthd.getSoLuong());
                txtGiaCTHD.setText("THÀNH TIỀN: " + (cthd.getSoLuong()*sp.getGiaBan()) + " Đ");
            }
        }

        return row;
    }
}
