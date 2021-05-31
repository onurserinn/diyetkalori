package com.example.diyetkalori;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DietFragment extends Fragment {

    Button btnListele, btntemizle;
    DBHelper db;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_diet, container, false);

       btnListele = v.findViewById(R.id.btnListele);

       db = new DBHelper(this);

        btnListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=db.getData();
                if(res.getCount()==0){
                    Toast.makeText(getContext().getApplicationContext(), "Veri Bulunamadı", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuilder buffer = new StringBuilder();
                while(res.moveToNext()){
                    buffer.append("Yiyecek İsmi: "+ res.getString(0)+"\n");
                    buffer.append("Kalori: "+ res.getString(1)+"\n");
                    buffer.append("Karbonhidrat: "+ res.getString(2)+"\n");
                    buffer.append("Protein: "+ res.getString(3)+"\n");
                    buffer.append("Yağ: "+ res.getString(4)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(DietFragment.this.getContext());
                builder.setCancelable(true);
                builder.setTitle("Bugün Yediğim Besinler");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        /*btntemizle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deletealldata("köri soslu tavuk");
                Toast.makeText(DietFragment.this.getContext(), "Besinler Temizlendi!", Toast.LENGTH_SHORT).show();
            }
        });*/



       return v;
    }
}
