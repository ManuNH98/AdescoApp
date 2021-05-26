package com.navarromanuel.adescoapp.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.navarromanuel.adescoapp.R;

import java.net.URI;

public class CuadernoActivity extends AppCompatActivity{

    String url_firebase = "https://console.firebase.google.com/u/2/project/adesco-cdc21/database/adesco-cdc21-default-rtdb/data";
            String url_pdf = "https://anyconv.com/es/convertidor-de-json-a-pdf/";
    String url_csv = "https://csvjson.com/json2csv";
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuaderno);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button1);
        b3 = findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri linkF = Uri.parse(url_firebase);
                Intent i = new Intent(Intent.ACTION_VIEW,linkF);
                startActivity(i);

            }
        });


    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri linkP = Uri.parse(url_pdf);
            Intent a = new Intent(Intent.ACTION_VIEW,linkP);
            startActivity(a);
        }
    });


        b3.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Uri linkC = Uri.parse(url_csv);
            Intent q = new Intent(Intent.ACTION_VIEW,linkC);
            startActivity(q);
                }
                });
                }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}