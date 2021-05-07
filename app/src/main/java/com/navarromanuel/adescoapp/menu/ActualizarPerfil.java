package com.navarromanuel.adescoapp.menu;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import com.navarromanuel.adescoapp.activity.Pojo;

public class ActualizarPerfil extends AppCompatActivity {

    private Button btnAct;
    private EditText editNombre, editApe, editNIF;
    private TextView  editTlfEm, editDirec, editNombreEm, editCodEm, editCiudadEm,
            editProvinciaEm, editCif, editRegistroN, editRegistroA;

    int i = 0;
    com.navarromanuel.adescoapp.activity.Pojo Pojo;

    // FIREBASE
    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ProgressDialog progressDialog;

    FirebaseDatabase basededatos;
    DatabaseReference referencia, urusersRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_perfil);

        // ICON EN ACTION BAR
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Pojo = new Pojo();

        editNombre = (EditText) findViewById(R.id.edtNombre);
        editApe = (EditText) findViewById(R.id.edtApellido);
        editNIF = (EditText) findViewById(R.id.edtNIF);

        editNombreEm = (TextView) findViewById(R.id.edtNombrEm);
        editTlfEm = (TextView) findViewById(R.id.edtTelefono);
        editDirec = (TextView) findViewById(R.id.edtDirec);
        editCodEm = (TextView) findViewById(R.id.edtCod);
        editCif = (TextView) findViewById(R.id.edtCif);
        editRegistroA = (TextView) findViewById(R.id.edtExpA);
        editRegistroN = (TextView) findViewById(R.id.edtExpN);
        editProvinciaEm = (TextView) findViewById(R.id.edtProv);
        editCiudadEm = (TextView) findViewById(R.id.edtCiu);

        urusersRef = basededatos.getInstance().getReference().child("DatosUsuarioyEmpresa").child(""+user.getUid());
        urusersRef.addValueEventListener(new ValueEventListener() {
                                           @Override
                                           public void onDataChange(DataSnapshot dataSnapshot) {

                                               Pojo pojo = dataSnapshot.getValue(Pojo.class);
                                               //String uidd = dataSnapshot.getKey();

                                               if (pojo != null) {

                                                   String name = pojo.getNombre();
                                                   String ape = pojo.getApellido();
                                                   String nif = pojo.getNif();

                                                   String nameE = pojo.getNombreEmpresa();
                                                   String tlf = pojo.getTelefonoEmpresa();
                                                   String direccion = pojo.getDireccionEmpresa();
                                                   String codE = pojo.getCodigoPostal();
                                                   String cif = pojo.getCif();
                                                   String provincia = pojo.getProvinciaEmpresa();
                                                   String ciudad = pojo.getCiudadEmpresa();
                                                   String registroA = pojo.getRegitroAutonomico();
                                                   String registroN = pojo.getRegistroNacinal();

                                                   //String tlf = String.valueOf(pojo.getTlf());

                                                   String uid = dataSnapshot.getKey();

                                                   //puedes hacer lo que quieras con los datos
                                                   editNombre.setText(name);
                                                   editApe.setText(ape);
                                                   editNIF.setText(nif);
                                                  // editTlf.setText("" + tlf);

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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        referencia = basededatos.getInstance().getReference().child("DatosUsuarioyEmpresa");

        referencia.addValueEventListener((new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    i = (int)snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }));


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        // ICON EN ACTION BAR
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAct =(Button)findViewById(R.id.btnAct);

        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        
               if (editApe.length() == 0 || editNombre.length() == 0 || editNIF.length() == 0 || editTlfEm.length() == 0
                       || editNombreEm.length() == 0 || editCif.length() == 0 || editCiudadEm.length() == 0
                       || editProvinciaEm.length() == 0 || editDirec.length() == 0 || editCodEm.length() == 0
                       || editRegistroA.length() == 0 || editRegistroN.length() == 0){
                    Toast.makeText(ActualizarPerfil.this, "Debes rellenar todos los campos primero ", Toast.LENGTH_LONG).show();
                }


                    AlertDialog.Builder alerta1 = new AlertDialog.Builder(ActualizarPerfil.this);
                    alerta1.setMessage("¿Estas seguro de hacer estos cambios?").setCancelable(true).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (Pojo != null) {

                                Pojo.setNombre(editNombre.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setApellido(editApe.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setNif(editNIF.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);


                                Pojo.setNombreEmpresa(editNombreEm.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setDireccionEmpresa(editDirec.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setCodigoPostal(editCodEm.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setTelefonoEmpresa(editTlfEm.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setCiudadEmpresa(editCiudadEm.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setCif(editCif.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setProvinciaEmpresa(editProvinciaEm.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setRegistroNacinal(editRegistroN.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);
                                Pojo.setRegitroAutonomico(editRegistroA.getText().toString());
                                referencia.child(user.getUid()).setValue(Pojo);

                                Pojo.setUid(user.getUid());
                                referencia.child(user.getUid()).setValue(Pojo);

                            }

                            Intent intent = new  Intent(getApplicationContext(), PerfilActivity.class);

                            startActivity(intent);
                            finish();
                            //overridePendingTransition(R.anim.left_in, R.anim.left_out);
                           //Toast.makeText(ThirdActivity.this, "Resgistrado", Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Se han actualizado los datos correctamente", Toast.LENGTH_SHORT).show();
                            dialog.cancel();

                        }
                    })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();

                                }
                            });

                    AlertDialog titulo1 = alerta1.create();
                    titulo1.setTitle("Atención");
                    titulo1.show();

            }

        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
               // overridePendingTransition(R.anim.right_in, R.anim.right_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}



