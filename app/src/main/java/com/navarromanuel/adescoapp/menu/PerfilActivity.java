package com.navarromanuel.adescoapp.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.MenuActivity;
import com.navarromanuel.adescoapp.activity.Pojo;


public class PerfilActivity extends AppCompatActivity {

    private Button btnAtras, btnEditPerfil;
    private TextView editNombre, editApe, editNIF,
            editTlfEm, editDirec, editNombreEm, editCodEm, editCiudadEm,
            editProvinciaEm, editCif, editRegistroN, editRegistroA;

    FirebaseAuth mAuth;
    FirebaseDatabase basededatos;
    DatabaseReference usersRef;

    int i = 0;
    Pojo Pojo;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();

        Pojo = new Pojo();

        editNombre = (TextView) findViewById(R.id.edtNombre);
        editApe = (TextView) findViewById(R.id.edtApellido);
        editNIF = (TextView) findViewById(R.id.edtNIF);

        editNombreEm = (TextView) findViewById(R.id.edtNombrEm);
        editTlfEm = (TextView) findViewById(R.id.edtTelefono);
        editDirec = (TextView) findViewById(R.id.edtDirec);
        editCodEm = (TextView) findViewById(R.id.edtCod);
        editCif = (TextView) findViewById(R.id.edtCif);
        editRegistroA = (TextView) findViewById(R.id.edtExpA);
        editRegistroN = (TextView) findViewById(R.id.edtExpN);
        editProvinciaEm = (TextView) findViewById(R.id.edtProv);
        editCiudadEm = (TextView) findViewById(R.id.edtCiu);


        usersRef = basededatos.getInstance().getReference().child("DatosUsuarioyEmpresa").child(user.getUid());
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Pojo pojo = dataSnapshot.getValue(Pojo.class);
                String uidd = dataSnapshot.getKey();

                if(pojo!=null) {

                    String name = pojo.getNombre();
                    String ape = pojo.getApellido();
                    String nif = pojo.getNif();
                    //String correo = user.getEmail();

                    String nameE = pojo.getNombreEmpresa();
                    String tlf = pojo.getTelefonoEmpresa();
                    String direccion = pojo.getDireccionEmpresa();
                    String codE = pojo.getCodigoPostal();
                    String cif = pojo.getCif();
                    String provincia = pojo.getProvinciaEmpresa();
                    String ciudad = pojo.getCiudadEmpresa();
                    String registroA = pojo.getRegitroAutonomico();
                    String registroN = pojo.getRegistroNacinal();


                    String uid = dataSnapshot.getKey();

                    editNombre.setText(name);
                    editApe.setText(ape);
                    editNIF.setText(nif);

                    //txtEm.setText(correo);

                    editTlfEm.setText(""+tlf);
                    editNombreEm.setText(nameE);
                    editDirec.setText(direccion);
                    editCodEm.setText(codE);
                    editCif.setText(cif);
                    editRegistroA.setText(registroA);
                    editRegistroN.setText(registroN);
                    editProvinciaEm.setText(provincia);
                    editCiudadEm.setText(ciudad);

                }

            }
            //  }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }

        });

        btnAtras = (Button) findViewById(R.id.btnAtras);
        //btnEditE = (Button) findViewById(R.id.btnEditE);
        btnEditPerfil = (Button) findViewById(R.id.btnEditPerfil);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(PerfilActivity.this);
                alerta.setMessage("¿Desea volver?").setCancelable(true).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new  Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                        finish();
                        //overridePendingTransition(R.anim.right_in, R.anim.right_out);
                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });

                AlertDialog titulo = alerta.create();
                titulo.setTitle("Atención");
                titulo.show();

            }

        });


        btnEditPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new  Intent(getApplicationContext(), ActualizarPerfil.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                //overridePendingTransition(R.anim.right_in, R.anim.right_out);

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