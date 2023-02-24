package ngo.tuan.androidbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ngo.tuan.model.KhachHang;

public class TaiKhoanActivity extends AppCompatActivity {

    TextView txtTenKhachHang, txtDiaChiNhanHangKH, txtDiaChiMuaHangKH;
    Button btnXemChiTietKH, btnXemLichSuMuaHang, btnDangXuat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhapActivity.KHACHHANG = null;
                DangNhapActivity.TAIKHOAN = null;
                Intent intent = new Intent(TaiKhoanActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        txtTenKhachHang = findViewById(R.id.txtTenKhachHang);
        txtDiaChiNhanHangKH = findViewById(R.id.txtDiaChiNhanHangKH);
        txtDiaChiMuaHangKH = findViewById(R.id.txtDiaChiMuaHangKH);

        txtTenKhachHang.setText(DangNhapActivity.KHACHHANG.getHoTen());

        txtDiaChiMuaHangKH.setText((DangNhapActivity.KHACHHANG.getDiaChiMuaHang()));
        txtDiaChiNhanHangKH.setText((DangNhapActivity.KHACHHANG.getDiaChiNhanHang()));

        btnXemChiTietKH = findViewById(R.id.btnXemChiTietKH);
        btnXemLichSuMuaHang = findViewById(R.id.btnXemLichSuMuaHang);
        btnDangXuat = findViewById(R.id.btnDangXuat);
    }
}