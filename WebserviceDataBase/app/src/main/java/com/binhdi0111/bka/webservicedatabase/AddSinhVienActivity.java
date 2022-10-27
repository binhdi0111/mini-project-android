package com.binhdi0111.bka.webservicedatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddSinhVienActivity extends AppCompatActivity {
    Button btnThem,btnHuy;
    EditText edtTen,edtNamSinh,edtDiaChi;
    String urlInsert = "http:192.168.1.165/androidwebservice/insertDB.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sinh_vien);
        Anhxa();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edtTen.getText().toString().trim();
                String namsinh = edtNamSinh.getText().toString().trim();
                String diachi = edtDiaChi.getText().toString().trim();
                if(hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty()){
                    Toast.makeText(AddSinhVienActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    ThemSV(urlInsert);
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void ThemSV(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(AddSinhVienActivity.this, "thêm thành công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddSinhVienActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(AddSinhVienActivity.this, "lỗi!"+response.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddSinhVienActivity.this, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
                        Log.d("BINHBKA0111","LỖI!\n"+error.toString());
                    }
                }
        )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("hotenSV",edtTen.getText().toString().trim());
                params.put("namsinhSV",edtNamSinh.getText().toString().trim());
                params.put("diachiSV",edtDiaChi.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void Anhxa(){
       btnThem = (Button) findViewById(R.id.buttonCapNhat);
       btnHuy = (Button) findViewById(R.id.buttonHuyCN);
       edtTen = (EditText) findViewById(R.id.editTextNhapTenCN);
       edtNamSinh = (EditText) findViewById(R.id.editTextNamsinhCN);
       edtDiaChi = (EditText) findViewById(R.id.editTextDiaChiCN);
    }
}