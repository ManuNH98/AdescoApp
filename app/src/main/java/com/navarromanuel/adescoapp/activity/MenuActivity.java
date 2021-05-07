package com.navarromanuel.adescoapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.menu.PerfilActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void enviarParcela(View view) {
    }

    public void enviarCDC(View view) {
    }

    public void enviarInventario(View view) {

    }

    public void enviarPerfil(View view) {
        Intent intent4 = new  Intent(MenuActivity.this, PerfilActivity.class);
        startActivity(intent4);
    }

    /*@Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                //overridePendingTransition(R.anim.right_in, R.anim.right_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
