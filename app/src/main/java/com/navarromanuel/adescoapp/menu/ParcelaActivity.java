package com.navarromanuel.adescoapp.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.adapter.ParcelaAdapter;
import com.navarromanuel.adescoapp.entidad.Parcela;

import java.util.ArrayList;
import java.util.List;

public class ParcelaActivity extends AppCompatActivity {

    RecyclerView recyclerViewParcela;
    ParcelaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela);
        setTitle("Search here..");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewParcela = findViewById(R.id.recyclerView);
        recyclerViewParcela.setLayoutManager(new  LinearLayoutManager(this));

        FirebaseRecyclerOptions<Parcela> options =
                new FirebaseRecyclerOptions.Builder<Parcela>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Parcelas"), Parcela.class)
                        .build();

        adapter=new ParcelaAdapter(options);
        recyclerViewParcela.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
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