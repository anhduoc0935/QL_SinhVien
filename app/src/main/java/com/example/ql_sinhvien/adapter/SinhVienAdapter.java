package com.example.ql_sinhvien.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ql_sinhvien.R;
import com.example.ql_sinhvien.model.Sinhvien;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<Sinhvien> {

    @NonNull
    private Activity activity;
    private int resource;
    @NonNull
    private List<Sinhvien> objects;

    public SinhVienAdapter(@NonNull Activity activity, int resource, @NonNull List<Sinhvien> objects) {
        super(activity, resource, objects);
        this.activity=activity;
        this.objects=objects;
        this.resource=resource;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =this.activity.getLayoutInflater();
        View view=inflater.inflate(this.resource, null);

        TextView txtHoten =view.findViewById(R.id.txtHoten);
        TextView txtEmail = view.findViewById(R.id.txtEmail);

        Sinhvien sinhvien =this.objects.get(position);
        txtHoten.setText(sinhvien.getHoten());
        txtEmail.setText(sinhvien.getEmail());

        ImageView btnMenu=view.findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu =new PopupMenu(activity,view);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                       if (menuItem.getItemId()==R.id.item_themSV){
                           Toast.makeText(activity,"Ban da nhan nut them",Toast.LENGTH_LONG).show();
                       }
                       else if (menuItem.getItemId()==R.id.item_SuaSV){
                           Toast.makeText(activity,"Ban da nhan nut sua"+sinhvien.getHoten(),Toast.LENGTH_LONG).show();
                       }
                       else if (menuItem.getItemId()==R.id.item_XoaSV){
                           Toast.makeText(activity,"Ban da nhan nut xoa"+sinhvien.getHoten(),Toast.LENGTH_LONG).show();
                       }
                        return false;
                    }
                });
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
                try{
                    Field field= popupMenu.getClass().getDeclaredField("mPopup");
                    field.setAccessible(true);
                    Object popUpMenuHelper =field.get(popupMenu);
                    Class<?> cls = Class.forName("com.android.internal.view.menu.MenuPopupHelper");
                    Method method =cls.getDeclaredMethod("setForceShowIcon",new Class[]{boolean.class});
                    method.setAccessible(true);
                    method.invoke(popUpMenuHelper,new Object[]{true});
                }catch (Exception e){
                    Log.d("MyTag","onClick: "+e.toString());
                }
                popupMenu.show();
            }
        });
        return view;
    }
}
