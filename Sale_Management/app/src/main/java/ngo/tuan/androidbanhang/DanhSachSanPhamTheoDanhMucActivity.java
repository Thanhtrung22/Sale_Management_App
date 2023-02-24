package ngo.tuan.androidbanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import ngo.tuan.adapter.SanPhamAdapter;
import ngo.tuan.model.Configuration;
import ngo.tuan.model.DanhMuc;
import ngo.tuan.model.SanPham;

public class DanhSachSanPhamTheoDanhMucActivity extends AppCompatActivity {

    ListView lvSanPham;
    ArrayList<SanPham> dsSanPham;
    SanPhamAdapter sanPhamAdapter;

   // A dialog that shows a progress indicator and an optional text message or view.
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_san_pham_theo_danh_muc);

        addControls();
        addEvents();
    }

    private void addEvents() {
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sp = sanPhamAdapter.getItem(position);
                moManHinhChiTietSanPham(sp);
            }
        });
    }

    private void moManHinhChiTietSanPham(SanPham sp) {
        Intent intent = new Intent(DanhSachSanPhamTheoDanhMucActivity.this, ChiTietSanPhamActivity.class);
        intent.putExtra("SANPHAM", sp);
        startActivity(intent);
    }

    private void addControls() {
        lvSanPham = findViewById(R.id.lvSanPham);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Thông báo");
        progressDialog.setMessage("Đang load.....");

        Intent intent = getIntent();
        DanhMuc dm = (DanhMuc) intent.getSerializableExtra("DANHMUC");

        DanhSachSanPhamTheoDanhMucTask task = new DanhSachSanPhamTheoDanhMucTask();
        task.execute(dm);
    }

    class DanhSachSanPhamTheoDanhMucTask extends AsyncTask<DanhMuc, Void, ArrayList<SanPham>>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.show();
        }

        @Override
        protected void onPostExecute(ArrayList<SanPham> sanPhams) {
            super.onPostExecute(sanPhams);

            if(sanPhams!=null) {
                sanPhamAdapter = new SanPhamAdapter(DanhSachSanPhamTheoDanhMucActivity.this, R.layout.sanpham_item, sanPhams);
                lvSanPham.setAdapter(sanPhamAdapter);
            }
            else {
                Toast.makeText(DanhSachSanPhamTheoDanhMucActivity.this, "Lỗi kết nối!", Toast.LENGTH_LONG).show();
            }
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<SanPham> doInBackground(DanhMuc... danhMucs) {
            ArrayList<SanPham> dsSP = new ArrayList<>();
            try {
                URL url = new URL(Configuration.URL_SERVER+"sanpham?madm="+danhMucs[0].getID());
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
                for(int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int masp = jsonObject.getInt("ID");
                    String tensp = jsonObject.getString("TenSanPham");
                    String hinhanhsp = jsonObject.getString("HinhAnhSanPham");
//                    int danhmucid = jsonObject.getInt("DanhMucID");
                    int soluong = jsonObject.getInt("SoLuong");
                    int giaban = jsonObject.getInt("GiaBan");
                    String mota = jsonObject.getString("MoTa");

                    SanPham sp = new SanPham();
                    sp.setID(masp);
                    sp.setTenSanPham(tensp);
                    sp.setHinhAnhSanPham(hinhanhsp);
//                    sp.setDanhMucID(danhmucid);
                    sp.setSoLuong(soluong);
                    sp.setGiaBan(giaban);
                    sp.setMoTa(mota);

                    dsSP.add(sp);
                }
                br.close();
            }
            catch (Exception ex) {
                Log.e("LOI O DAY:", ex.toString());
            }
            return dsSP;
        }
    }
}