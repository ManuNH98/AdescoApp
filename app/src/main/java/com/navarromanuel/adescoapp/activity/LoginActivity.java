package com.navarromanuel.adescoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.navarromanuel.adescoapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextTextPassword;
    private CheckBox mostrarContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTextPassword = (EditText)findViewById(R.id.editTextTextPassword);
        mostrarContraseña = (CheckBox)findViewById(R.id.checkBox);

    }

    public void enviarMenu(View view) {
        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    public void enviarRegistrar(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
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