package com.navarromanuel.adescoapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.PojoInventario;

import de.hdodenhof.circleimageview.CircleImageView;

public class GuardadoHolder extends RecyclerView.ViewHolder {

    private View mView;
    TextView field1, field2, field3, field4, field5, field6;
    CircleImageView img;
    TextView producto, cant, notas, id, procedencia, fechaAlta;
    ImageView edit, delete;


    public GuardadoHolder(View itemView) {
        super(itemView);
        mView = itemView;

        producto = itemView.findViewById(R.id.lblNombre);
        cant = itemView.findViewById(R.id.lblCantidad);
        notas = itemView.findViewById(R.id.lblNotas);

        id = itemView.findViewById(R.id.lblidproductoEditar);
        procedencia = itemView.findViewById(R.id.lblidProcedencia);
        fechaAlta = itemView.findViewById(R.id.lblfechaAlta);

        delete = itemView.findViewById(R.id.editIconDelete);
        edit = itemView.findViewById(R.id.editIcon);
    }

    public void bindGuardado(PojoInventario pojoI) {

        field1 = mView.findViewById(R.id.lblNombre);
        field1.setText("Producto: \n" + pojoI.getProducto());

        field2 = mView.findViewById(R.id.lblCantidad);
        field2.setText("Cantidad: \n" + pojoI.getCantidad());

        field3 = mView.findViewById(R.id.lblNotas);
        field3.setText("Notas: \n" + pojoI.getNotas());

        field4 = mView.findViewById(R.id.lblidproductoEditar);
        field4.setText("Id: " + pojoI.getID());
        field5 = mView.findViewById(R.id.lblidProcedencia);
        field5.setText("Procedencia: \n" + pojoI.getProcedencia());
        field6 = mView.findViewById(R.id.lblfechaAlta);
        field6.setText("Fecha Alta: \n" + pojoI.getFechaAlta());


         }

    }