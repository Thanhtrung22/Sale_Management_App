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

import ngo.tuan.androidbanhang.R;
import ngo.tuan.model.SanPham;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    Activity context;
    int resource;
    ArrayList<SanPham> objects;

   
    public SanPhamAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<SanPham> objects) {
        // Calling the constructor of the super class.
        super(context, resource, objects);// super để kế thừa từ lớp cha
        // Assigning the value of the parameter `context` to the field `this.context`.
        this.context=context;//this để biết đấy là thuộc tính chính của đối tượng
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       // Inflating the layout.
        LayoutInflater inflater = this.context.getLayoutInflater();
       // Inflating the layout.
        View row = inflater.inflate(this.resource, null);

        ImageView imgSanPhamLV = row.findViewById(R.id.imgSanPhamLV);
        TextView txtTenSanPhamLV = row.findViewById(R.id.txtTenSanPhamLV);
        TextView txtConHangLV = row.findViewById(R.id.txtConHangLV);

        SanPham sp = this.objects.get(position); // objects là ds sp, get lấy đối tượng trong danh sách tại 1 pos
        txtTenSanPhamLV.setText(sp.getTenSanPham());
        if(sp.getSoLuong()>0){
            txtConHangLV.setText("Còn hàng");
        }
        else {
            txtConHangLV.setText("Hết hàng");
        }
        byte[] decodedString = Base64.decode(sp.getHinhAnhSanPham(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgSanPhamLV.setImageBitmap(decodedByte);

        return row;
    }
}
