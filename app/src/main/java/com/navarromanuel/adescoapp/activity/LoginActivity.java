package com.navarromanuel.adescoapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
    private CheckBox mostrarContraseña;

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
        editTextTextPassword = (EditText)findViewById(R.id.editTextTextPassword);
        mostrarContraseña = (CheckBox)findViewById(R.id.checkBox);

        progressDialog = new ProgressDialog(this);

        buttonReset = findViewById(R.id.buttonReset);
        buttonReset.setBackground(null);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entrarUsuario();

                //overridePendingTransition(R.anim.left_in, R.anim.left_out);
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

                //overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }



    public void enviarMenu(View view) {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void enviarRegistrar(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
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


                    Toast.makeText(LoginActivity.this, "¡LOGEADO CON ÉXITO!\n¡BIENVENIDO!\n" , Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "¡Error al entrar! Revisa los datos", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();


            }
        });
    }



    public void mostrarContraseña(View v){
        int cursor = editTextTextPassword.getSelectionEnd();

        if(mostrarContraseña.isChecked()){
            editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else{
            editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        editTextTextPassword.setSelection(cursor);
    }
}