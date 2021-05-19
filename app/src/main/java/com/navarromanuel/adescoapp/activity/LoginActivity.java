package com.navarromanuel.adescoapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.navarromanuel.adescoapp.R;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextTextPassword;
    private Button btnLogin, btnRes, buttonReset;
    private EditText inputEmail;
    private TextView textView;
    private ProgressDialog progressDialog;
    private CheckBox mostrarContraseña, recordar;

    // FIREBASE
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        Toast.makeText(LoginActivity.this, "¡REGISTRE USUARIO Y PASS Y CONTINUE EL REGISTRO! \n                          POR FAVOR", Toast.LENGTH_LONG).show();
        // ICON EN ACTION BAR


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        inputEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        btnLogin = findViewById(R.id.buttonLogin);
        btnRes = findViewById(R.id.buttonRegistrar);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        mostrarContraseña = (CheckBox) findViewById(R.id.checkBox);
        recordar = (CheckBox) findViewById(R.id.recordar);

        cargarPreferencias();

        progressDialog = new ProgressDialog(this);

        buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setBackground(null);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(switchCompat.isChecked()){

                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email", inputEmail.getText().toString());
                    editor.putString("password", editTextTextPassword.getText().toString());
                    editor.putBoolean("stateSwitch", switchCompat.isChecked());
                    editor.commit();

                } else {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("email","");
                    editor.putString("password", "");
                    editor.putBoolean("stateSwitch", switchCompat.isChecked());
                    editor.commit();
                }
*/
                entrarUsuario();
                guardarPreferencias();

            }
        });

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarUsuario();
                guardarPreferencias();
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (buttonReset.isClickable()) {
                    buttonReset.setTextColor(Color.BLUE);
                    Intent intent = new Intent(getApplicationContext(), ResetPassword.class);
                    startActivity(intent);

                }

                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }



    /*public void enviarMenu(View view) {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void enviarRegistrar(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }*/

    public void registrarUsuario() {

        final String correo = inputEmail.getText().toString();
        final String pass = editTextTextPassword.getText().toString();

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(LoginActivity.this, "Debe ingresar un email.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(LoginActivity.this, "Debe ingresar su contraseña.", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(correo, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Toast.makeText(ThirdActivity.this, "Resgistrado", Toast.LENGTH_SHORT).show();

                    Toast.makeText(LoginActivity.this, "¡Se ha registrado Email y Password correctamente!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                    //overridePendingTransition(R.anim.left_in, R.anim.left_out);

                } else {
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(LoginActivity.this, " El usuario ya existe", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, " ¡ERROR al REGISTRARSE!", Toast.LENGTH_LONG).show();
                    }
                }
                progressDialog.dismiss();
            }
        });

    }

    public void entrarUsuario() {

        final String correo = inputEmail.getText().toString();
        final String pass = editTextTextPassword.getText().toString();

        if (TextUtils.isEmpty(correo)) {
            Toast.makeText(LoginActivity.this, "Debe ingresar un email.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(LoginActivity.this, "Debe ingresar su contraseña.", Toast.LENGTH_SHORT).show();
            return;
        }


        mAuth.signInWithEmailAndPassword(correo, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.setMessage("Espere un momento");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    // intentRegistro(emailstring, pwdstring);
                    Intent intent = new Intent(getApplication(), com.navarromanuel.adescoapp.activity.MenuActivity.class);
                    //intent.putExtra(gabri.com.dam.greenfilm.inicio.email, correo);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_in, R.anim.left_out);

                    Toast.makeText(LoginActivity.this, "¡LOGEADO CON ÉXITO!\n¡BIENVENIDO!\n", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "¡Error al entrar! Revisa los datos", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();


            }
        });
    }

   /* private void guardarL() {

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String nombre = editNombre.getText().toString();
        String apellido = editApe.getText().toString();
        String nif = editNIF.getText().toString();
        String email = inputEmail.getText().toString();
        String pass = editTextTextPassword.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", nombre);
        editor.putString("ape", apellido);
        editor.putString("nif", nif);
        editor.putString("email", email);
        editor.putString("pass", pass);

        editor.commit();

    }*/

    /*private void cargarR() {

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String email = preferences.getString("email", "");
        String pass = preferences.getString("pass", "");

        inputEmail.setText(email);
        editTextTextPassword.setText(pass);

    }*/


    public void mostrarContraseña(View v) {
        int cursor = editTextTextPassword.getSelectionEnd();

        if (mostrarContraseña.isChecked()) {
            editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        editTextTextPassword.setSelection(cursor);
    }


    public void cargarPreferencias() {
        SharedPreferences preferencias = getSharedPreferences("MiUsuario", Context.MODE_PRIVATE);

        recordar.setChecked(preferencias.getBoolean("checked",false));
        inputEmail.setText(preferencias.getString("email",""));
        editTextTextPassword.setText(preferencias.getString("pass",""));

    }

    public void guardarPreferencias() {
        SharedPreferences preferencias = getSharedPreferences("MiUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        boolean valor = recordar.isChecked();
        String email = inputEmail.getText().toString();
        String pass = editTextTextPassword.getText().toString();

        editor.putBoolean("checked",valor);
        editor.putString("email",email);
        editor.putString("pass",pass);

        editor.commit();

         }
}