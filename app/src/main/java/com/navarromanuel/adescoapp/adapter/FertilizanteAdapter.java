package com.navarromanuel.adescoapp.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.PojoInventario;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class FertilizanteAdapter extends FirebaseRecyclerAdapter<PojoInventario, FertilizanteHolder> {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public FertilizanteAdapter(FirebaseRecyclerOptions opciones){
        super(opciones);
    }
    @Override
    public FertilizanteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_fertilizante, parent, false);

        return new FertilizanteHolder(view);
    }

    @Override
    protected void onBindViewHolder(FertilizanteHolder saveViewHolder, final int position, PojoInventario pojoI) {
        saveViewHolder.producto.setText(pojoI.getProducto());
        saveViewHolder.cant.setText(pojoI.getCantidad());
        saveViewHolder.notas.setText(pojoI.getNotas());



        saveViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(saveViewHolder.producto.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_ftz_editar))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText producto=myview.findViewById(R.id.productoEditar);
                final EditText cantidad=myview.findViewById(R.id.cantidadEditar);
                final EditText notas=myview.findViewById(R.id.notasEditar);

                Button submit=myview.findViewById(R.id.btnEditar);

                producto.setText(pojoI.getProducto());
                cantidad.setText(pojoI.getCantidad());
                notas.setText(pojoI.getNotas());


                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("producto",producto.getText().toString());
                        map.put("cantidad",cantidad.getText().toString());
                        map.put("notas",notas.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("ProductoFertilizante").child(""+user.getUid()).child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        saveViewHolder.bindGuardado(pojoI);

       saveViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(saveViewHolder.producto.getContext());
                builder.setTitle("Panel de borrado");
                builder.setMessage("¿Estas seguro de que quieres borrar este producto?");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("ProductoFertilizante").child(""+user.getUid()).child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });
    }

    /*
    class saveViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView producto, cant, notas;
        ImageView edit, delete;

        public saveViewHolder(@NonNull View itemView){
            super(itemView);

            producto = itemView.findViewById(R.id.lblNombre);
            cant = itemView.findViewById(R.id.lblCantidad);
            notas = itemView.findViewById(R.id.lblNotas);

            delete = itemView.findViewById(R.id.editIconD);
            edit = itemView.findViewById(R.id.editIcon);

        }
    }*/
}




