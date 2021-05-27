/*package com.navarromanuel.adescoapp.menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidad.Parcela;

public class DetalleParcelaActivity extends AppCompatActivity {

    TextView nombreDetalle, metrosDetalle, descripcionDetalle, tipoDetalle, fechainicioDetalle, fechafinDetalle;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela_detalle);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nombreDetalle = findViewById(R.id.nombre_detalle);
        metrosDetalle = findViewById(R.id.metros_detalle);
        descripcionDetalle = findViewById(R.id.descripcion_detalle);
        tipoDetalle = findViewById(R.id.tipo_cultivo_detalle);
        fechainicioDetalle = findViewById(R.id.fecha_inicio_detalle);
        fechafinDetalle = findViewById(R.id.fecha_fin_detalle);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Parcelas");

        String uid = getIntent().getStringExtra("uid");

        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    String metros = dataSnapshot.child("metros").getValue().toString();
                    String info = dataSnapshot.child("info").getValue().toString();
                    String tipo = dataSnapshot.child("tipo").getValue().toString();
                    String fechainicio = dataSnapshot.child("fechainicio").getValue().toString();
                    String fechafin = dataSnapshot.child("fechafin").getValue().toString();

                    nombreDetalle.setText(nombre);
                    metrosDetalle.setText(metros);
                    descripcionDetalle.setText(info);
                    tipoDetalle.setText(tipo);
                    fechainicioDetalle.setText(fechainicio);
                    fechafinDetalle.setText(fechafin);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
}*/
