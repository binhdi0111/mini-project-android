package com.binhdi0111.bka.webservicedatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView listViewSinhVien;
    ArrayList<SinhVien> sinhVienArrayList;
    SinhVienAdapter adapter;
    String urlGetdata = "http:192.168.1.165/androidwebservice/getData.php";
    String urlDelete = "http:192.168.1.165/androidwebservice/delete.php";
    EditText txtloi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetData(urlGetdata);
        listViewSinhVien = (ListView) findViewById(R.id.listView);
        sinhVienArrayList = new ArrayList<>();
        adapter = new SinhVienAdapter(MainActivity.this,R.layout.dong_sinhven,sinhVienArrayList);
        listViewSinhVien.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuAdd){
            startActivity(new Intent(MainActivity.this,AddSinhVienActivity.class));

        }        return super.onOptionsItemSelected(item);
    }

    public void DeleteSinhVien(int idsv){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(MainActivity.this, "Xóa thành công!"+response.toString(), Toast.LENGTH_SHORT).show();
                            GetData(urlGetdata);
                        }else{
                            Toast.makeText(MainActivity.this, "lỗi!"+response.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Xảy ra lỗi", Toast.LENGTH_LONG).show();
                        Log.d("BINHBKA0111","LỖI!\n"+error.toString());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String ,String> params = new HashMap<>();
                params.put("idcuaSV",String.valueOf(idsv));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void GetData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        sinhVienArrayList.clear();
                        for(int i = 0;i<response.length();i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                sinhVienArrayList.add(new SinhVien(
                                        object.getInt("Id"),
                                        object.getString("HoTen"),
                                        object.getInt("NamSinh"),
                                        object.getString("DiaChi")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        txtloi.setText(error.toString().trim());
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}