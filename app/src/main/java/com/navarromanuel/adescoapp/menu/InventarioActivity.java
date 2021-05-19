package com.navarromanuel.adescoapp.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.MenuActivity;

public class InventarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void enviarFito(View view) {
        Intent intent1 = new  Intent(InventarioActivity.this, FitoActivity.class);
        startActivity(intent1);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    public void enviarFert(View view) {
        Intent intent2 = new  Intent(InventarioActivity.this, FrtzActivity.class);
        startActivity(intent2);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    public void enviarHerramientas(View view) {
        Intent intent3 = new  Intent(InventarioActivity.this, ToolActivity.class);
        startActivity(intent3);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);

    }

    public void enviarMiInventario(View view) {
        Intent intent4 = new  Intent(InventarioActivity.this, InventarioActivityOpciones.class);
        startActivity(intent4);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

                startActivity(intent);

                finish();
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}