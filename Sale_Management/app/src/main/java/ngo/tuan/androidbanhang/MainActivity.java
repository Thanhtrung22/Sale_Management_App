package ngo.tuan.androidbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ngo.tuan.adapter.DanhMucAdapter;
import ngo.tuan.model.Configuration;
import ngo.tuan.model.DanhMuc;
import ngo.tuan.model.HoaDonBan;
import ngo.tuan.model.KhachHang;

public class MainActivity extends AppCompatActivity {

    ImageButton btnTaiKhoan;
    ImageView imgKhuyenMai;
    ImageButton btnGioHang;
    ImageButton btnTrangChu;
    ImageButton btnGiamGia;
    
    GridView gvDanhMuc;
    ArrayList<DanhMuc> dsDanhMuc;
    DanhMucAdapter danhMucAdapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        gvDanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DanhMuc dm = danhMucAdapter.getItem(position);
                moDanhSachSanPhamTheoDanhMuc(dm);
            }
        });

        btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyChuyenManHinhNutTaiKhoan();
            }
        });

        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void xuLyChuyenManHinhNutTaiKhoan() {
        if(DangNhapActivity.KHACHHANG==null) {
            Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(MainActivity.this, TaiKhoanActivity.class);
            startActivity(intent);
        }
    }

    private void moDanhSachSanPhamTheoDanhMuc(DanhMuc dm) {
        Intent intent = new Intent(MainActivity.this, DanhSachSanPhamTheoDanhMucActivity.class);
        intent.putExtra("DANHMUC", dm);
        startActivity(intent);
    }

    private void addControls() {
        btnTaiKhoan = findViewById(R.id.btnTaiKhoan);
        btnTaiKhoan.refreshDrawableState();

        if(DangNhapActivity.KHACHHANG!=null){
            btnTaiKhoan.setImageResource(R.drawable.login);
        }
        else {
            btnTaiKhoan.setImageResource(R.drawable.logout);
        }
        btnTaiKhoan.refreshDrawableState();
        imgKhuyenMai = findViewById(R.id.imgKhuyenMai);
        imgKhuyenMai.setImageResource(R.drawable.khuyenmai);
        btnGioHang = findViewById(R.id.btnGioHang);
        btnGioHang.setImageResource(R.drawable.giohang);
        btnGiamGia = findViewById(R.id.btnGiamGia);
        btnGiamGia.setImageResource(R.drawable.giamgia);
        btnTrangChu = findViewById(R.id.btnTrangChu);
        btnTrangChu.setImageResource(R.drawable.trangchu);

        gvDanhMuc = findViewById(R.id.gvDanhMuc);

        progressDialog = new ProgressDialog(this);

        DanhSachDanhMucTask task = new DanhSachDanhMucTask();
        task.execute();
    }

    class DanhSachDanhMucTask extends AsyncTask<Void, Void, ArrayList<DanhMuc>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<DanhMuc> danhMucs) {
            super.onPostExecute(danhMucs);
            if(danhMucs!=null) {
                danhMucAdapter = new DanhMucAdapter(MainActivity.this, R.layout.danhmuc_item, danhMucs);
                gvDanhMuc.setAdapter(danhMucAdapter);
            }
            else {
                Toast.makeText(MainActivity.this, "Kiểm tra lại kết nối!", Toast.LENGTH_LONG).show();
            }
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<DanhMuc> doInBackground(Void... voids) {
            ArrayList<DanhMuc> dsDM = new ArrayList<>();
            try {
                URL url = new URL(Configuration.URL_SERVER+"danhmuc");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuilder builder = new StringBuilder();
                String line = null;
                while ((line=br.readLine())!=null){
                    builder.append(line);
                }
                br.close();
                JSONArray jsonArray = new JSONArray(builder.toString());
                for(int i=0; i< jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int madm = jsonObject.getInt("ID");
                    String tendm = jsonObject.getString("TenDanhMuc");
                    String hinhanhdm = jsonObject.getString("HinhAnhDanhMuc");
                    DanhMuc dm = new DanhMuc();
                    dm.setID(madm);
                    dm.setTenDanhMuc(tendm);
                    dm.setHinhAnhDanhMuc(hinhanhdm);
                    dsDM.add(dm);
                }
            }
            catch (Exception ex) {
                Log.e("LOI O DAY Nay:", ex.toString());
            }
            return dsDM;
        }
    }
}