package com.navarromanuel.adescoapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.navarromanuel.adescoapp.R;

public class ResetPassword extends AppCompatActivity {


    private EditText edtEmail;
    private Button bReset;

    private String email ="";

    private FirebaseAuth FbA;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pass);

        // ICON EN ACTION BAR
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bReset = (Button) findViewById(R.id.btnReset);

        edtEmail = (EditText) findViewById(R.id.txtEmail);

        FbA = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtEmail.getText().toString();

                if(!email.isEmpty()) {
                    progressDialog.setMessage("Espere un momento");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    restaurarPass();
                }else {
                    Toast.makeText(ResetPassword.this, "Debe ingresar el email", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void restaurarPass()     {
        FbA.setLanguageCode("es");
        FbA.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ResetPassword.this, "Se ha enviado un correo para restrablecer la contraseña", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ResetPassword.this, "No se puede enviar el correo de reestablecer contraseña", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
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