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

public class Update_Activity extends AppCompatActivity {
    Button btnUpdate,btnHuyUpdate;
    EditText edtTenUpdate,edtNamSinhUpdate,edtDiaChiUpdate;
    String urlUpdate = "http:192.168.1.165/androidwebservice/update.php";
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("dataSinhVien");
        Anhxa();
        id = sinhVien.getId();
        edtTenUpdate.setText(sinhVien.getHoTen());
        edtNamSinhUpdate.setText(sinhVien.getNamSinh() + "");
        edtDiaChiUpdate.setText(sinhVien.getDiaChi());
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edtTenUpdate.getText().toString().trim();
                String namsinh = edtNamSinhUpdate.getText().toString().trim();
                String diachi = edtDiaChiUpdate.getText().toString().trim();
                if(hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty()){
                    Toast.makeText(Update_Activity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    UpdateData(urlUpdate);
                }
            }
        });
        btnHuyUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void UpdateData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(Update_Activity.this, "cập nhật thành công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Update_Activity.this,MainActivity.class));
                        }else{
                            Toast.makeText(Update_Activity.this, "lỗi!"+response.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Update_Activity.this, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
                        Log.d("BINHBKA0111","LỖI!\n"+error.toString());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("idSV",String.valueOf(id));
                params.put("hotenSV",edtTenUpdate.getText().toString().trim());
                params.put("namsinhSV",edtNamSinhUpdate.getText().toString().trim());
                params.put("diachiSV",edtDiaChiUpdate.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void Anhxa(){
        btnUpdate  = (Button) findViewById(R.id.buttonCapNhat);
        btnHuyUpdate = (Button) findViewById(R.id.buttonHuyCN);
        edtTenUpdate = (EditText) findViewById(R.id.editTextNhapTenCN);
        edtNamSinhUpdate = (EditText) findViewById(R.id.editTextNamsinhCN);
        edtDiaChiUpdate = (EditText) findViewById(R.id.editTextDiaChiCN);
    }
}