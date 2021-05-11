package com.navarromanuel.adescoapp.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.MenuActivity;
import com.navarromanuel.adescoapp.adapter.MainActivityFertilizante;
import com.navarromanuel.adescoapp.adapter.MainActivityFitosanitario;
import com.navarromanuel.adescoapp.adapter.MainActivityTool;

public class InventarioActivityOpciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_total);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void enviarFitosanitario(View view) {
        Intent intent = new  Intent(InventarioActivityOpciones.this, MainActivityFitosanitario.class);
        startActivity(intent);
    }

    public void enviarFertilizantes(View view) {
        Intent intent2 = new  Intent(InventarioActivityOpciones.this, MainActivityFertilizante.class);
        startActivity(intent2);
    }

    public void enviarHerramientas(View view) {
        Intent intent1 = new  Intent(InventarioActivityOpciones.this, MainActivityTool.class);
        startActivity(intent1);
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