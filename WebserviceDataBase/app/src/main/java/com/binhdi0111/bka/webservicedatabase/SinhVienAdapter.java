package com.binhdi0111.bka.webservicedatabase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class SinhVienAdapter extends BaseAdapter {
    public SinhVienAdapter(MainActivity context, int layout, List<SinhVien> sinhVienList) {
        this.context = context;
        this.layout = layout;
        this.sinhVienList = sinhVienList;
    }

    private MainActivity context;
    private int layout;
    private List<SinhVien> sinhVienList;
    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvTen,tvNamSinh,tvDiaChi;
        ImageView imgDelete,imgEdit;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.tvTen = (TextView) view.findViewById(R.id.textViewTen);
            holder.tvNamSinh = (TextView) view.findViewById(R.id.textViewNamSinh);
            holder.tvDiaChi = (TextView) view.findViewById(R.id.textViewDiaChi);
            holder.imgDelete = (ImageView)view.findViewById(R.id.imageViewdelete);
            holder.imgEdit = (ImageView)view.findViewById(R.id.imageViewEdit);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        SinhVien sinhVien = sinhVienList.get(i);
        holder.tvTen.setText(sinhVien.getHoTen());
        holder.tvNamSinh.setText("Năm Sinh: " + sinhVien.getNamSinh());
        holder.tvDiaChi.setText(sinhVien.getDiaChi());
        //bắt sự kiện xóa,sửa
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,Update_Activity.class);
                intent.putExtra("dataSinhVien",sinhVien);
                context.startActivity(intent);
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XacnhanXoa(sinhVien.getHoTen(),sinhVien.getId());
            }
        });
        return view;
    }
    private  void XacnhanXoa(String ten, int id){
        AlertDialog.Builder alerDialog = new AlertDialog.Builder(context);
        alerDialog.setTitle("Thông báo");
        alerDialog.setIcon(R.mipmap.ic_launcher);
        alerDialog.setMessage("Bạn có muốn xóa " + ten +" không?");
        alerDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                context.DeleteSinhVien(id);
            }
        });
        alerDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alerDialog.show();
    }
}
