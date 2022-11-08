package com.example.ql_sinhvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ql_sinhvien.adapter.SinhVienAdapter;
import com.example.ql_sinhvien.model.Sinhvien;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Trang_QLSinhvien extends AppCompatActivity {
private ListView ql_sinhvien;
private ArrayList<Sinhvien> sinhvienArrayList;
private SinhVienAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_qlsinhvien);

        ql_sinhvien =findViewById(R.id.ql_sinhvien);

         sinhvienArrayList=new ArrayList<>();
        GetData();
         adapter =new SinhVienAdapter(this,R.layout.custom_listview_item,sinhvienArrayList);
        ql_sinhvien.setAdapter(adapter);


    }
    private void  GetData(){
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        DatabaseReference myRef =database.getReference("DbSinhvien");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adapter.clear();

        for (DataSnapshot data: dataSnapshot.getChildren()){
            Sinhvien sinhvien=data.getValue(Sinhvien.class);

                sinhvien.setId(data.getKey());
                adapter.add(sinhvien);
                Log.d("MYTAG","onDataChange: "+sinhvien.getHoten());
}

                Toast.makeText(getApplicationContext(),"Load Data Success",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Load Data failed"+ databaseError.toString(),Toast.LENGTH_LONG).show();
                Log.d("MyTag","onCanceled: "+ databaseError.toString());
            }
        });
    }
}