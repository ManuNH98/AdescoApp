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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.MenuActivity;
import com.navarromanuel.adescoapp.adapter.ParcelaAdapter;
import com.navarromanuel.adescoapp.entidad.Parcela;

import java.util.ArrayList;
import java.util.List;

public class ParcelaActivity extends AppCompatActivity {

    RecyclerView recyclerViewParcela;
    FloatingActionButton fab;
    ParcelaAdapter adapter;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela);
        setTitle("Buscar..");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference references =
                FirebaseDatabase.getInstance().getReference()
                        .child("Parcelas").child(""+user.getUid());

        fab = findViewById(R.id.floatingActionButton);
        recyclerViewParcela = findViewById(R.id.recyclerView);
        recyclerViewParcela.setLayoutManager(new  LinearLayoutManager(this));

        FirebaseRecyclerOptions<Parcela> options =
                new FirebaseRecyclerOptions.Builder<Parcela>()
                        .setQuery(references, Parcela.class)
                        .build();

        adapter=new ParcelaAdapter(options);
        recyclerViewParcela.setAdapter(adapter);


    }

    public void enviarNuevaParcela(View view){
        Intent intent = new  Intent(ParcelaActivity.this, AniadirParcelaActivity.class);
        startActivity(intent);
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference references =
                FirebaseDatabase.getInstance().getReference()
                        .child("Parcelas").child(""+user.getUid());

        FirebaseRecyclerOptions<Parcela> options =
                new FirebaseRecyclerOptions.Builder<Parcela>()
                        .setQuery(references.orderByChild("id").startAt(s).endAt(s+"\uf8ff"), Parcela.class)
                        .build();

        adapter=new ParcelaAdapter(options);
        adapter.startListening();
        recyclerViewParcela.setAdapter(adapter);

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