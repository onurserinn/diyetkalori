package com.example.diyetkalori;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class UrunFragment extends Fragment {
    Button btnaddFood, btndeleteFood, btnupdateFood, btnviewFood;
    EditText foodname, calories, carbs, protein, fat;
    DBHelper db;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_urun, container, false);

        foodname = v.findViewById(R.id.foodName);
        calories = v.findViewById(R.id.calories);
        carbs = v.findViewById(R.id.carbs);
        protein = v.findViewById(R.id.protein);
        fat = v.findViewById(R.id.fat);

        btnaddFood = v.findViewById(R.id.btnaddFood);
        btnupdateFood = v.findViewById(R.id.btnupdateFood);
        btndeleteFood = v.findViewById(R.id.btndeleteFood);
        btnviewFood = v.findViewById(R.id.btnviewFood);

        db = new DBHelper(this);


        // Yiyecek - İçecek Ekleme
        btnaddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodnameTXT = foodname.getText().toString();
                String caloriesTXT = calories.getText().toString();
                String carbsTXT = carbs.getText().toString();
                String proteinTXT = protein.getText().toString();
                String fatTXT = fat.getText().toString();

                Boolean checkinsertdata = db.insertfoodsdata(foodnameTXT, caloriesTXT, carbsTXT, proteinTXT, fatTXT);
                if (checkinsertdata==true)
                    Toast.makeText(getContext().getApplicationContext(), "Besin Eklendi!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext().getApplicationContext(), "Besin Eklenemedi!", Toast.LENGTH_SHORT).show();
            }
        });


        // Yiyecek İçecek Güncelleme
        btnupdateFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodnameTXT = foodname.getText().toString();
                String caloriesTXT = calories.getText().toString();
                String carbsTXT = carbs.getText().toString();
                String proteinTXT = protein.getText().toString();
                String fatTXT = fat.getText().toString();

                Boolean checkupdatedata = db.updatefoodsdata(foodnameTXT, caloriesTXT, carbsTXT, proteinTXT, fatTXT);
                if (checkupdatedata==true)
                    Toast.makeText(UrunFragment.this.getContext(), "Besin Güncellendi!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UrunFragment.this.getContext(), "Besin Güncellenemedi!!", Toast.LENGTH_SHORT).show();
            }
        });

        btndeleteFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodnameTXT = foodname.getText().toString();
                db.deletefoodsdata(foodnameTXT);
                Toast.makeText(UrunFragment.this.getContext(), "Besin Silindi!", Toast.LENGTH_SHORT).show();
            }
        });

        btnviewFood.setOnClickListener(new View.OnClickListener() {
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

                AlertDialog.Builder builder = new AlertDialog.Builder(UrunFragment.this.getContext());
                builder.setCancelable(true);
                builder.setTitle("Besin Listesi;");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });



        return v;
    }


}
