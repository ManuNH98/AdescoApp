package com.navarromanuel.adescoapp.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.adapter.ParcelaAdapter;
import com.navarromanuel.adescoapp.entidad.Parcela;

import java.util.ArrayList;
import java.util.List;

public class ParcelaActivity extends AppCompatActivity {

    private List<Parcela> parcelaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    public void init(){

        parcelaList = new ArrayList<>();
        parcelaList.add(new Parcela("Parcela 15", "En esta parcela se plantan papas y muchas mas cosas locas", "27m x 15m",R.drawable.ic_launcher_foreground));
        parcelaList.add(new Parcela("Parcela 15", "En esta parcela se plantan papas y muchas mas cosas locas", "27m x 15m",R.drawable.ic_launcher_foreground));
        parcelaList.add(new Parcela("Parcela 15", "En esta parcela se plantan papas y muchas mas cosas locas", "27m x 15m",R.drawable.ic_launcher_foreground));
        parcelaList.add(new Parcela("Parcela 15", "En esta parcela se plantan papas y muchas mas cosas locas", "27m x 15m",R.drawable.ic_launcher_foreground));
        parcelaList.add(new Parcela("Parcela 15", "En esta parcela se plantan papas y muchas mas cosas locas", "27m x 15m",R.drawable.ic_launcher_foreground));
        parcelaList.add(new Parcela("Parcela 15", "En esta parcela se plantan papas y muchas mas cosas locas", "27m x 15m",R.drawable.ic_launcher_foreground));
        parcelaList.add(new Parcela("Parcela 15", "En esta parcela se plantan papas y muchas mas cosas locas", "27m x 15m",R.drawable.ic_launcher_foreground));

        ParcelaAdapter mAdapter = new ParcelaAdapter(parcelaList, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                //overridePendingTransition(R.anim.right_in, R.anim.right_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}