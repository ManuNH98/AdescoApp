package com.navarromanuel.adescoapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.PojoInventario;

import de.hdodenhof.circleimageview.CircleImageView;

public class FertilizanteHolder extends RecyclerView.ViewHolder {

    private View mView;
    TextView field1, field2, field3;
    CircleImageView img;
    TextView producto, cant, notas;
    ImageView edit, delete;


    public FertilizanteHolder(View itemView) {
        super(itemView);
        mView = itemView;

        producto = itemView.findViewById(R.id.lblNombre);
        cant = itemView.findViewById(R.id.lblCantidad);
        notas = itemView.findViewById(R.id.lblNotas);

        delete = itemView.findViewById(R.id.editIconDelete);
        edit = itemView.findViewById(R.id.editIcon);
    }

    public void bindGuardado(PojoInventario pojoI) {

        field1 = mView.findViewById(R.id.lblNombre);
        field1.setText("Producto: " + pojoI.getProducto());

        field2 = mView.findViewById(R.id.lblCantidad);
        field2.setText("Cantidad: " + pojoI.getCantidad());

        field3 = mView.findViewById(R.id.lblNotas);
        field3.setText("Notas: " + pojoI.getNotas());


    }
}