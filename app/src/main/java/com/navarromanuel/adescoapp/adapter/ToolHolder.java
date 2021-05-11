package com.navarromanuel.adescoapp.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.PojoInventario;

public class ToolHolder extends RecyclerView.ViewHolder {

    private View mView;
    TextView field1, field2, field3;

    public ToolHolder(View itemView) {
        super(itemView);
        mView = itemView;
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