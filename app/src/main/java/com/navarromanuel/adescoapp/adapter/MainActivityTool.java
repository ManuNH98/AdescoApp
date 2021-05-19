package com.navarromanuel.adescoapp.adapter;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.PojoInventario;

public class MainActivityTool extends AppCompatActivity {

    FirebaseAuth mAuth;
    RecyclerView recycler;
    FirebaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tool);
        setTitle("Buscar por nombre...");

        // ICON EN ACTION BAR
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference references =
                FirebaseDatabase.getInstance().getReference()
                        .child("Herramientas").child(""+user.getUid());


        recycler = findViewById(R.id.recycler_View_tool);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<PojoInventario> options =
                new FirebaseRecyclerOptions.Builder<PojoInventario>()
                        .setQuery(references, PojoInventario.class)
                        .build();


        mAdapter=new ToolAdapter(options);

        recycler.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();

    }

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
                        .child("Herramientas").child(""+user.getUid());


        FirebaseRecyclerOptions<PojoInventario> options =
                new FirebaseRecyclerOptions.Builder<PojoInventario>()
                        .setQuery(references.orderByChild("producto").startAt(s).endAt(s+"\uf8ff"), PojoInventario.class)
                        .build();

        mAdapter=new FertilizanteAdapter(options);
        mAdapter.startListening();
        recycler.setAdapter(mAdapter);

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