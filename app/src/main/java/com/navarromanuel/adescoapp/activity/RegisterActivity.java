package com.navarromanuel.adescoapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.navarromanuel.adescoapp.R;

public class RegisterActivity extends AppCompatActivity {


    private Button btnRes, btnVolver;
    private EditText editTextTextPassword;
    private EditText inputEmail;
    private EditText editNombre, editApe, editNIF,
            editTlfEm, editDirec, editNombreEm, editCodEm, editCiudadEm,
            editProvinciaEm, editCif, editRegistroN, editRegistroA;
    //private CheckBox cbMostrar;

    private ProgressDialog progressDialog;
    FirebaseDatabase basededatos;
    DatabaseReference referencia;
    int i = 0;
    Pojo Pojo;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // FIREBASE
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Pojo = new Pojo();

        inputEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = (EditText)findViewById(R.id.editTextTextPassword);

        editNombre = (EditText) findViewById(R.id.edtNombre);
        editApe = (EditText) findViewById(R.id.edtApellido);
        editNIF = (EditText) findViewById(R.id.edtNIF);

        editNombreEm = (EditText) findViewById(R.id.edtNombrEm);
        editTlfEm = (EditText) findViewById(R.id.edtTelefono);
        editDirec = (EditText) findViewById(R.id.edtDirec);
        editCodEm = (EditText) findViewById(R.id.edtCod);
        editCif = (EditText) findViewById(R.id.edtCif);
        editRegistroA = (EditText) findViewById(R.id.edtExpA);
        editRegistroN = (EditText) findViewById(R.id.edtExpN);
        editProvinciaEm = (EditText) findViewById(R.id.edtProv);
        editCiudadEm = (EditText) findViewById(R.id.edtCiu);

//       String uid = user.getUid();
        //String email = miembro.getEmail();

        referencia = basededatos.getInstance().getReference().child("DatosUsuarioyEmpresa");

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

        //edtPass = (EditText) findViewById(R.id.edtPass);
        // cbMostrar = findViewById(R.id.ChkMuestrame);

        btnRes = (Button) findViewById(R.id.btnRegistrar);

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editNombre.length() == 0  || editTlfEm.length() == 0
                        || editNombreEm.length() == 0 || editCif.length() == 0 || editCiudadEm.length() == 0
                        || editProvinciaEm.length() == 0 || editDirec.length() == 0 || editCodEm.length() == 0
                        || editRegistroA.length() == 0 || editRegistroN.length() == 0) {

                    Toast.makeText(RegisterActivity.this, "Debes rellenar todos los campos primero ", Toast.LENGTH_LONG).show();
                }

               /*
                edtPass.setError(null);

                String Password = edtPass.getText().toString().trim();

                boolean cancel = false;
                View focusView = null;


                if (TextUtils.isEmpty(Password)){

                    focusView = edtPass;
                    cancel = true;
                }

                if (!Password.matches(".*[a-z].*")){
                   Toast.makeText(getApplicationContext(), "La contraseña debe tener como minimo 1 mayuscula, 1 minuscula y un minimo de 8 caracteres ", Toast.LENGTH_SHORT).show();

                    focusView = edtPass;
                    cancel = true;
                }

                if (!Password.matches(".*[A-Z].*")){

                    focusView = edtPass;
                    cancel = true;
                }

                if (!Password.matches(".{8,15}")){
                    focusView = edtPass;
                    cancel = true;                }

                if (cancel) {

                    focusView.requestFocus();

                }
                editEmail.setError(null);
                String email = editEmail.getText().toString();

                if(!checkEmail(email)) {
                    Toast.makeText(getApplicationContext(), "Debes ingresar un Email válido", Toast.LENGTH_SHORT).show();
                }

                else {*/

                //guardar();

                AlertDialog.Builder alerta1 = new AlertDialog.Builder(RegisterActivity.this);
                alerta1.setMessage("¿Ha compleado todos los campos?").setCancelable(true).setPositiveButton("Si", new DialogInterface.OnClickListener() {
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

                            //guardarR();
                        }


                        //Toast.makeText(ThirdActivity.this, "Resgistrado", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getApplicationContext(), "Se ha registrado los datos correctamente", Toast.LENGTH_SHORT).show();
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

                //}
                try {


                } catch (Exception e) {

                }
                //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


            }

        });

        btnVolver = (Button) findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterActivity.this);
                alerta.setMessage("¿Desea volver?").setCancelable(true).setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                        startActivityForResult(intent, 0);
                        finish();
                        // overridePendingTransition(R.anim.right_in, R.anim.right_out);
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


    }

    /*public void mostrarContra(View v){
        // Salvar cursor
        int cursor = edtPass.getSelectionEnd();

        if(cbMostrar.isChecked()){
            edtPass.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            edtPass.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        }

        // Restaurar cursor
        edtPass.setSelection(cursor);

    }*/

   /*private void guardar() {

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String nombre = editNombre.getText().toString();
        String apellido = editApe.getText().toString();
        String nif = editNIF.getText().toString();
        String email = editEmail.getText().toString();
        String pass = edtPass.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", nombre);
        editor.putString("ape", apellido);
        editor.putString("nif", nif);
        editor.putString("email", email);
        editor.putString("pass", pass);

        editor.commit();

    }*/

   /* private void guardarR() {

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String email = inputEmail.getText().toString();
        String pass = editTextTextPassword.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("email", email);
        editor.putString("pass", pass);

        editor.commit();

    }*/

    /*private void cargarL() {

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String email = preferences.getString("email", "");
        String pass = preferences.getString("pass", "");

        inputEmail.setText(email);
        editTextTextPassword.setText(pass);

    }*/

}