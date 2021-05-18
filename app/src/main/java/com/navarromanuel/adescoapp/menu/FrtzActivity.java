package com.navarromanuel.adescoapp.menu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.LoginActivity;
import com.navarromanuel.adescoapp.activity.Pojo;
import com.navarromanuel.adescoapp.activity.PojoInventario;
import com.navarromanuel.adescoapp.activity.RegisterActivity;

public class FrtzActivity extends AppCompatActivity {

    private Button btnAniadir;
    private EditText edtN, edtC, edtNo, edtID, edtFechaA, edtProcedencia;

    private ProgressDialog progressDialog;
    FirebaseDatabase basededatos;
    DatabaseReference referencia;
    int i = 0;
    PojoInventario PojoInventario;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // FIREBASE
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fertilizante);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PojoInventario = new PojoInventario();

        edtN = (EditText) findViewById(R.id.edtNombre);
        edtC = (EditText) findViewById(R.id.edtCant);
        edtNo = (EditText) findViewById(R.id.edtNotas);
        edtID = (EditText) findViewById(R.id.idproductoEditar);
        edtProcedencia = (EditText) findViewById(R.id.procedenciaEditar);
        edtFechaA = (EditText) findViewById(R.id.fechaAlta);

        referencia = basededatos.getInstance().getReference().child("ProductoFertilizante").child(""+user.getUid()).push();

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


        btnAniadir = (Button) findViewById(R.id.btnA);

        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtN.getText().toString().isEmpty() || edtC.getText().toString().isEmpty()|| edtProcedencia.getText().toString().isEmpty()
                        || edtFechaA.getText().toString().isEmpty()
                        || edtID.getText().toString().isEmpty()) {

                    Toast.makeText(FrtzActivity.this, "Debes rellenar todos los campos primero ", Toast.LENGTH_LONG).show();
                }


                else if (PojoInventario != null) {

                    PojoInventario.setProducto(edtN.getText().toString());
                    referencia.setValue(PojoInventario);
                    PojoInventario.setCantidad(edtC.getText().toString());
                    referencia.setValue(PojoInventario);
                    PojoInventario.setNotas(edtNo.getText().toString());
                    referencia.setValue(PojoInventario);

                    PojoInventario.setID(edtID.getText().toString());
                    referencia.setValue(PojoInventario);
                    PojoInventario.setProcedencia(edtProcedencia.getText().toString());
                    referencia.setValue(PojoInventario);
                    PojoInventario.setFechaAlta(edtFechaA.getText().toString());
                    referencia.setValue(PojoInventario);

                    PojoInventario.setUid(user.getUid());
                    referencia.setValue(PojoInventario);

                    //Toast.makeText(ThirdActivity.this, "Resgistrado", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Se ha registrado los datos correctamente", Toast.LENGTH_SHORT).show();
                    //dialog.cancel();


                    Intent intent = new Intent(getApplicationContext(), InventarioActivity.class);
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