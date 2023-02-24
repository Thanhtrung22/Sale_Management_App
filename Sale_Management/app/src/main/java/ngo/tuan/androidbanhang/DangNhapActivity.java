package ngo.tuan.androidbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;

import ngo.tuan.model.Configuration;
import ngo.tuan.model.KhachHang;
import ngo.tuan.model.TaiKhoan;

public class DangNhapActivity extends AppCompatActivity {

    EditText edtTenDangNhap;
    EditText edtMatKhau;
    Button btnDangNhap;

    ProgressDialog progressDialog;

    public static TaiKhoan TAIKHOAN;
    public static KhachHang KHACHHANG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyDangNhap();
            }
        });
    }

    private void xuLyDangNhap() {
        TaiKhoan tk = new TaiKhoan();
        tk.setEmail(edtTenDangNhap.getText().toString());
        tk.setPassword(edtMatKhau.getText().toString());

        DangNhapTask task = new DangNhapTask();
        task.execute(tk);
    }

    private void addControls() {
        edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        edtMatKhau = findViewById(R.id.edtMatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);

        progressDialog = new ProgressDialog(this);
    }

    class DangNhapTask extends AsyncTask<TaiKhoan, Void, Boolean>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean.booleanValue()==true) {
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                TAIKHOAN = new TaiKhoan();
                TAIKHOAN.setEmail(edtTenDangNhap.getText().toString());
                TAIKHOAN.setPassword(edtMatKhau.getText().toString());

                LayKhachHangTheoEmailTask task = new LayKhachHangTheoEmailTask();
                task.execute(edtTenDangNhap.getText().toString());

                Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_LONG).show();
            }
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(TaiKhoan... taiKhoans) {
            TaiKhoan tk = new TaiKhoan();
            try {
                URL url = new URL(Configuration.URL_SERVER+"taikhoan?email="+URLEncoder.encode(taiKhoans[0].getEmail())+"&password="+URLEncoder.encode(taiKhoans[0].getPassword()));
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
                boolean kq = builder.toString().contains("true");
                return kq;

            }
            catch (Exception ex){
                Log.e("LOI O DAY", ex.toString());
            }
            return false;
        }
    }

    class LayKhachHangTheoEmailTask extends AsyncTask<String, Void, KhachHang>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(KhachHang khachHang) {
            super.onPostExecute(khachHang);
            if(khachHang!=null){
                KHACHHANG = khachHang;
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected KhachHang doInBackground(String... strings) {

            try {
                URL url = new URL(Configuration.URL_SERVER+"khachhang?email="+URLEncoder.encode(strings[0]));
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

                JSONObject jsonObject = new JSONObject(builder.toString());
                int makh = jsonObject.getInt("ID");
                String hoten = jsonObject.getString("HoTen");
                String gioitinh = jsonObject.getString("GioiTinh");
                int phone = jsonObject.getInt("Phone");
                String diachi = jsonObject.getString("DiaChi");
                String diachimuahang = jsonObject.getString("DiaChiMuaHang");
                String diachinhanhang = jsonObject.getString("DiaChiNhanHang");
//                Date ngaysinh = Date.valueOf(jsonObject.getString("NgaySinh"));
//                Date ngaydangky = Date.valueOf(jsonObject.getString("NgayDangKy"));

                KhachHang kh = new KhachHang();
                kh.setID(makh);
                kh.setHoTen(hoten);
                kh.setGioiTinh(gioitinh);
                kh.setPhone(phone);
                kh.setEmail(strings[0]);
                kh.setDiaChi(diachi);
                kh.setDiaChiMuaHang(diachimuahang);
                kh.setDiaChiNhanHang(diachinhanhang);
//                kh.setNgaySinh(ngaysinh);
//                kh.setNgayDangKy(ngaydangky);

                return kh;
            }
            catch (Exception ex) {
                Log.e("LOI O DAY:", ex.toString());
            }
            return null;
        }
    }
}