package ngo.tuan.androidbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import ngo.tuan.model.ChiTietHoaDonBan;
import ngo.tuan.model.HoaDonBan;
import ngo.tuan.model.SanPham;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    TextView txtTenSanPhamChiTiet, txtGiaBanChiTiet, txtMoTaChiTiet;
    ImageView imgSanPhamChiTiet;
    Button btnDatMuaChiTiet;

    ProgressDialog progressDialog;

    SanPham sp;

    public static HoaDonBan HDB;
    public static ArrayList<ChiTietHoaDonBan> dsCTHDB = new ArrayList<>();

    public static ArrayList<SanPham> dsSanPhamDatMua = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDatMuaChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(DangNhapActivity.KHACHHANG != null) {
                    themVaoGioHang();
                }
                else {
                    Toast.makeText(ChiTietSanPhamActivity.this, "Bạn cần đăng nhập để mua hàng!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void themVaoGioHang() {
        String now = String.valueOf(System.currentTimeMillis());
        String strNow = now.substring(4, 9);

        int nowTime = Integer.parseInt(strNow);
        // phải đăng nhập rồi mới tạo được hóa đơn
        if(HDB==null && DangNhapActivity.KHACHHANG !=null){
            HDB = new HoaDonBan();
            HDB.setID(nowTime);
            HDB.setKhachHangID(DangNhapActivity.KHACHHANG.getID());
            HDB.setDiaChiNguoiMua(DangNhapActivity.KHACHHANG.getDiaChi());
            HDB.setDiaChiNhanHang(DangNhapActivity.KHACHHANG.getDiaChiNhanHang());
        }
        ChiTietHoaDonBan ctHDB = new ChiTietHoaDonBan();
        ctHDB.setID(nowTime);
        ctHDB.setSanPhamID(sp.getID());
        ctHDB.setHoaDonBanID(HDB.getID());
        // mặc định là 1
        ctHDB.setSoLuong(1);
        dsCTHDB.add(ctHDB);

        dsSanPhamDatMua.add(sp);

        Toast.makeText(this, "Đã thêm vào giỏ hàng", Toast.LENGTH_LONG).show();
    }

    private void addControls() {
        txtTenSanPhamChiTiet = findViewById(R.id.txtTenSanPhamChiTiet);
        txtGiaBanChiTiet = findViewById(R.id.txtGiaBanChiTiet);
        txtMoTaChiTiet = findViewById(R.id.txtMoTaChiTiet);
        imgSanPhamChiTiet = findViewById(R.id.imgSanPhamChiTiet);
        btnDatMuaChiTiet = findViewById(R.id.btnDatMuaChiTiet);

        progressDialog = new ProgressDialog(this);

        Intent intent = getIntent();
        sp = (SanPham) intent.getSerializableExtra("SANPHAM");

        txtTenSanPhamChiTiet.setText(sp.getTenSanPham());

        byte[] decodedString = Base64.decode(sp.getHinhAnhSanPham(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgSanPhamChiTiet.setImageBitmap(decodedByte);
        txtGiaBanChiTiet.setText(sp.getGiaBan()+" Đ");
        txtMoTaChiTiet.setText(sp.getMoTa());
    }


}