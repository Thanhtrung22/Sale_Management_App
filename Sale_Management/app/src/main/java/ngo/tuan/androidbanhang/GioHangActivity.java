package ngo.tuan.androidbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import ngo.tuan.adapter.ChiTietHoaDonBanAdapter;
import ngo.tuan.model.ChiTietHoaDonBan;
import ngo.tuan.model.Configuration;
import ngo.tuan.model.HoaDonBan;

public class GioHangActivity extends AppCompatActivity {

    ListView lvGioHang;
    ArrayList<ChiTietHoaDonBan> dsCTHDB;
    ChiTietHoaDonBanAdapter chiTietHoaDonBanAdapter;

    TextView txtTongTienGioHang;
    Button btnDatHangGioHang;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDatHangGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datHang();
            }
        });
    }

    private void datHang() {
        LuuHoaDonBanTask taskHDB = new LuuHoaDonBanTask();
        taskHDB.execute(ChiTietSanPhamActivity.HDB);
    }

    private void addControls() {
        lvGioHang = findViewById(R.id.lvGioHang);
        dsCTHDB = ChiTietSanPhamActivity.dsCTHDB;
        chiTietHoaDonBanAdapter = new ChiTietHoaDonBanAdapter(GioHangActivity.this, R.layout.chi_tiet_hoa_don_ban_item, dsCTHDB);
        lvGioHang.setAdapter(chiTietHoaDonBanAdapter);

        txtTongTienGioHang = findViewById(R.id.txtTongTienGioHang);
        btnDatHangGioHang = findViewById(R.id.btnDatHangGioHang);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Đặt hàng");
        progressDialog.setMessage("Đang xử lý.....");
    }

    class LuuHoaDonBanTask extends AsyncTask<HoaDonBan, Void, Boolean>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean.booleanValue()==true){
                Toast.makeText(GioHangActivity.this, "Lưu hóa đơn bán thành công!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(GioHangActivity.this, "Lưu hóa đơn bán thất bại!", Toast.LENGTH_LONG).show();
            }
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(HoaDonBan... hoaDonBans) {
            try {
                HoaDonBan hdb = hoaDonBans[0];
                System.out.println("11111111111111111111111111");
                System.out.println(hdb.getID() + "");
                System.out.println(hdb.getKhachHangID());
                System.out.println(hdb.getDiaChiNguoiMua());
                System.out.println(hdb.getDiaChiNhanHang());
                String params = "hoadonban?mahdb="+hdb.getID()+"&khachhangid="+hdb.getKhachHangID()+"&diachinguoimua="+URLEncoder.encode(hdb.getDiaChiNguoiMua())
                        +"&diachinhanhang="+URLEncoder.encode(hdb.getDiaChiNhanHang());

                URL url = new URL(Configuration.URL_SERVER+params);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

                System.out.println("222222222222222222222");

                StringBuilder builder = new StringBuilder();
                String line = null;
                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                while((line=br.readLine())!=null) {
                    builder.append(line);
                }
                boolean kq = builder.toString().contains("true");
                System.out.println("333333333333333333333");
                System.out.println(builder.toString());
                System.out.println(kq);
                return kq;
            }
            catch (Exception ex){
                Log.e("LOI O DAY:", ex.toString());
            }

            return false;
        }
    }

    class LuuChiTietHoaDonBanTask extends AsyncTask<ChiTietHoaDonBan, Void, Boolean>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean.booleanValue()==true){

            }
            else {
                Toast.makeText(GioHangActivity.this, "Dịch vụ gián đoạn, vui lòng kiểm tra kết nối!", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Boolean doInBackground(ChiTietHoaDonBan... chiTietHoaDonBans) {
            try {
                ChiTietHoaDonBan cthdb = chiTietHoaDonBans[0];
                String params = "chitiethoadonban?macthdb="+cthdb.getID()+"&sanphamid="+cthdb.getSanPhamID()+"&hoadonbanid="+cthdb.getHoaDonBanID()+"&soluong="+cthdb.getSoLuong();
                URL url = new URL(Configuration.URL_SERVER+params);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

                StringBuilder builder = new StringBuilder();
                String line = null;
                InputStreamReader isr = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                while((line=br.readLine())!=null) {
                    builder.append(line);
                }
                boolean kq = builder.toString().contains("true");
                return kq;
            }
            catch (Exception ex) {
                Log.e("LOI O DAY:", ex.toString());
            }
            return false;
        }
    }
}