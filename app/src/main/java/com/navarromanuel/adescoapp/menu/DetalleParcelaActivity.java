package com.navarromanuel.adescoapp.menu;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidad.Parcela;

public class DetalleParcelaActivity extends AppCompatActivity {

    TextView nombreDetalle, metrosDetalle, descripcionDetalle;
    ImageView imagenDetalle;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela_detalle);

        Parcela parcela = (Parcela) getIntent().getSerializableExtra("Parcela");
        imagenDetalle = findViewById(R.id.imagen_detalleid);
        nombreDetalle = findViewById(R.id.nombre_detalle);
        metrosDetalle = findViewById(R.id.metros_detalle);
        descripcionDetalle = findViewById(R.id.descripcion_detalle);

        imagenDetalle.setImageResource(parcela.getImagenid());
        nombreDetalle.setText(parcela.getNombre());
        metrosDetalle.setText(parcela.getMetros());
        descripcionDetalle.setText(parcela.getInfo());
    }
}
