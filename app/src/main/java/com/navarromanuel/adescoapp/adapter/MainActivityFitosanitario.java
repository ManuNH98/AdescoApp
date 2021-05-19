package com.navarromanuel.adescoapp.adapter;

import android.os.Bundle;
import android.view.MenuItem;

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

public class MainActivityFitosanitario extends AppCompatActivity {

    FirebaseAuth mAuth;

    FirebaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fitosanitario);

        // ICON EN ACTION BAR
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference references =
                FirebaseDatabase.getInstance().getReference()
                        .child("ProductoFitosanitario").child(""+user.getUid());


        RecyclerView recycler = findViewById(R.id.recycler_View_fito);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<PojoInventario> options =
                new FirebaseRecyclerOptions.Builder<PojoInventario>()
                        .setQuery(references, PojoInventario.class)
                        .build();


        mAdapter=new GuardadoAdapter(options);

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