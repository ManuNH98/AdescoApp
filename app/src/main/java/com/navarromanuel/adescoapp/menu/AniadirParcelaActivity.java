package com.navarromanuel.adescoapp.menu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidad.Parcela;

public class AniadirParcelaActivity extends AppCompatActivity {

    private Button btnAniadir;
    private EditText edtId, edtNombreParcela, edtMetros, edtTipo, edtFechaInicio, edtFechaFin, edtInfo;

    private ProgressDialog progressDialog;
    FirebaseDatabase basededatos;
    DatabaseReference referencia;
    int i = 0;
    Parcela Parcela;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // FIREBASE
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela_aniadir);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Parcela = new Parcela();

        edtId = (EditText) findViewById(R.id.edtId);
        edtNombreParcela = (EditText) findViewById(R.id.edtNombreParcela);
        edtMetros = (EditText) findViewById(R.id.edtMetros);
        edtTipo = (EditText) findViewById(R.id.edtTipo);
        edtFechaInicio = (EditText) findViewById(R.id.edtFechaInicio);
        edtFechaFin = (EditText) findViewById(R.id.edtFechaFin);
        edtInfo = (EditText) findViewById(R.id.edtInfo);

        referencia = basededatos.getInstance().getReference().child("Parcelas").child(""+user.getUid()).push();

        referencia.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);


        btnAniadir = (Button) findViewById(R.id.btnAParcela);

        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtId.getText().toString().isEmpty() || edtNombreParcela.getText().toString().isEmpty()|| edtMetros.getText().toString().isEmpty()
                        || edtTipo.getText().toString().isEmpty()) {

                    Toast.makeText(AniadirParcelaActivity.this, "Debes rellenar todos los campos primero ", Toast.LENGTH_LONG).show();
                }


                else if (Parcela != null) {

                    Parcela.setId(edtId.getText().toString());
                    referencia.setValue(Parcela);
                    Parcela.setNombre(edtNombreParcela.getText().toString());
                    referencia.setValue(Parcela);
                    Parcela.setMetros(edtMetros.getText().toString());
                    referencia.setValue(Parcela);
                    Parcela.setTipo(edtTipo.getText().toString());
                    referencia.setValue(Parcela);
                    Parcela.setFechainicio(edtFechaInicio.getText().toString());
                    referencia.setValue(Parcela);
                    Parcela.setFechafin(edtFechaFin.getText().toString());
                    referencia.setValue(Parcela);
                    Parcela.setInfo(edtInfo.getText().toString());
                    referencia.setValue(Parcela);

                    Parcela.setUid(user.getUid());
                    referencia.setValue(Parcela);

                    //Toast.makeText(ThirdActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Se ha registrado los datos correctamente", Toast.LENGTH_SHORT).show();
                    //dialog.cancel();

                    Intent intent = new Intent(getApplicationContext(), ParcelaActivity.class);
                    startActivity(intent);
                }

            }

        });

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