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

import java.util.List;

import ngo.tuan.androidbanhang.R;
import ngo.tuan.model.DanhMuc;

public class DanhMucAdapter extends ArrayAdapter<DanhMuc> {

    Activity context;
    int resource;
    List<DanhMuc> objects;

    public DanhMucAdapter(@NonNull Activity context, int resource, @NonNull List<DanhMuc> objects) {
        super(context, resource, objects); // kế thừa từ lớp cha
        this.context=context; // chỉ 
        this.resource = resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        TextView txtTenDanhMuc = row.findViewById(R.id.txtTenDanhMuc);
        ImageView imgDanhMuc = row.findViewById(R.id.imgDanhMuc);

        DanhMuc dm =this.objects.get(position);
        txtTenDanhMuc.setText(dm.getTenDanhMuc());
        byte[] decodedString = Base64.decode(dm.getHinhAnhDanhMuc(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgDanhMuc.setImageBitmap(decodedByte);

        return row;
    }
}
