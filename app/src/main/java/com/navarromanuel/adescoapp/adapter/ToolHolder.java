package com.navarromanuel.adescoapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.PojoInventario;

import de.hdodenhof.circleimageview.CircleImageView;

public class ToolHolder extends RecyclerView.ViewHolder {

    private View mView;
    TextView field1, field2, field3, field4, field5, field6;
    CircleImageView img;
    TextView producto, cant, notas, id, titular, fechaAlta;
    ImageView edit, delete;


    public ToolHolder(View itemView) {
        super(itemView);
        mView = itemView;

        producto = itemView.findViewById(R.id.lblNombre);
        cant = itemView.findViewById(R.id.lblCantidad);
        notas = itemView.findViewById(R.id.lblNotas);

        id = itemView.findViewById(R.id.lblidproductoEditar);
        titular = itemView.findViewById(R.id.lbltitular);
        fechaAlta = itemView.findViewById(R.id.lblfechaAlta);


        delete = itemView.findViewById(R.id.editIconDelete);
        edit = itemView.findViewById(R.id.editIcon);
    }

    public void bindGuardado(PojoInventario pojoI) {

        field1 = mView.findViewById(R.id.lblNombre);
        field1.setText( pojoI.getProducto());

        field2 = mView.findViewById(R.id.lblCantidad);
        field2.setText("Cantidad: \n" + pojoI.getCantidad());

        field3 = mView.findViewById(R.id.lblNotas);
        field3.setText("Notas: " + pojoI.getNotas());

        field4 = mView.findViewById(R.id.lblidproductoEditar);
        field4.setText("ID: " + pojoI.getID());
        field5 = mView.findViewById(R.id.lbltitular);
        field5.setText("Titular:  " + pojoI.getTitular());
        field6 = mView.findViewById(R.id.lblfechaAlta);
        field6.setText("Fecha Alta: " + pojoI.getFechaAlta());

         }

    }