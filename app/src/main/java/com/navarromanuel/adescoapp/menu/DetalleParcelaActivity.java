package com.navarromanuel.adescoapp.menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidad.Parcela;

public class DetalleParcelaActivity extends AppCompatActivity {

    TextView nombreDetalle, metrosDetalle, descripcionDetalle, tipoDetalle, fechainicioDetalle, fechafinDetalle;
    ImageView imagenDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela_detalle);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Parcela parcela = (Parcela) getIntent().getSerializableExtra("Parcela");
        imagenDetalle = findViewById(R.id.imagen_detalleid);
        nombreDetalle = findViewById(R.id.nombre_detalle);
        metrosDetalle = findViewById(R.id.metros_detalle);
        descripcionDetalle = findViewById(R.id.descripcion_detalle);
        tipoDetalle = findViewById(R.id.tipo_cultivo_detalle);
        fechainicioDetalle = findViewById(R.id.fecha_inicio_detalle);
        fechafinDetalle = findViewById(R.id.fecha_fin_detalle);

        imagenDetalle.setImageResource(parcela.getImagenid());
        nombreDetalle.setText(parcela.getNombre());
        metrosDetalle.setText(parcela.getMetros());
        descripcionDetalle.setText(parcela.getInfo());
        tipoDetalle.setText(parcela.getTipo());
        fechainicioDetalle.setText(parcela.getFechainicio());
        fechafinDetalle.setText(parcela.getFechafin());
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
