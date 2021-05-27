package com.navarromanuel.adescoapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidad.Parcela;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class ParcelaAdapter extends FirebaseRecyclerAdapter<Parcela,ParcelaAdapter.ParcelaViewHolder>{

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public ParcelaAdapter(@NonNull FirebaseRecyclerOptions<Parcela> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ParcelaViewHolder parcelaViewHolder, final int position, @NonNull final Parcela parcela){

        parcelaViewHolder.id.setText(parcela.getId());
        parcelaViewHolder.nombre.setText(parcela.getNombre());
        parcelaViewHolder.tipoCultivo.setText(parcela.getTipo());
        parcelaViewHolder.riego.setText(parcela.getRiego());
        parcelaViewHolder.metros.setText(parcela.getMetros());
        parcelaViewHolder.fechaInicio.setText(parcela.getFechainicio());
        parcelaViewHolder.fechaFin.setText(parcela.getFechafin());
        parcelaViewHolder.info.setText(parcela.getInfo());

        parcelaViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(parcelaViewHolder.nombre.getContext())
                        .setContentHolder(new ViewHolder(R.layout.activity_parcela_editar))
                        .setExpanded(true,1100)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText nombre=myview.findViewById(R.id.nombreEditar);
                final EditText tipoCultivo=myview.findViewById(R.id.tipoEditar);
                final EditText riego=myview.findViewById(R.id.riegoEditar);
                final EditText metros=myview.findViewById(R.id.metrosEditar);
                final EditText fechaInicio=myview.findViewById(R.id.fechaInicioEditar);
                final EditText fechaFin=myview.findViewById(R.id.fechaFinEditar);
                final EditText info=myview.findViewById(R.id.infoEditar);
                Button submit=myview.findViewById(R.id.btnEditar);

                nombre.setText(parcela.getNombre());
                tipoCultivo.setText(parcela.getTipo());
                riego.setText(parcela.getRiego());
                metros.setText(parcela.getMetros());
                fechaInicio.setText(parcela.getFechainicio());
                fechaFin.setText(parcela.getFechafin());
                info.setText(parcela.getInfo());

                dialogPlus.show();

                    submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Map<String,Object> map=new HashMap<>();
                            map.put("nombre",nombre.getText().toString());
                            map.put("tipo",tipoCultivo.getText().toString());
                            map.put("riego",riego.getText().toString());
                            map.put("metros",metros.getText().toString());
                            map.put("fechainicio",fechaInicio.getText().toString());
                            map.put("fechafin",fechaFin.getText().toString());
                            map.put("info",info.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child("Parcelas").child(""+user.getUid()).child(getRef(position).getKey()).updateChildren(map)
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

        parcelaViewHolder.bindGuardado(parcela);

        parcelaViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(parcelaViewHolder.nombre.getContext());
                builder.setTitle("Eliminar Parcela");
                builder.setMessage("¿Seguro que quieres eliminar?");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Parcelas").child(""+user.getUid()).child(getRef(position).getKey()).removeValue();
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

    @NonNull
    @Override
    public ParcelaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_parcela_list, parent, false);
        return new ParcelaViewHolder(v);
    }

    class ParcelaViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView id, nombre, info, metros, tipoCultivo, fechaInicio, fechaFin, riego;
        ImageView imagen, edit, delete;

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Parcelas").child(""+user.getUid()).child("-MaY0YEMphksL0xC2rB2");

        public ParcelaViewHolder(@NonNull View itemView){
            super(itemView);
            mView = itemView;

            imagen = itemView.findViewById(R.id.imagenParcela);
            id = itemView.findViewById((R.id.id));
            nombre = itemView.findViewById(R.id.nombreParcela);
            info = itemView.findViewById(R.id.info);
            metros = itemView.findViewById(R.id.metros);
            tipoCultivo = itemView.findViewById(R.id.tipoCultivo);
            riego = itemView.findViewById(R.id.tipoRiego);
            fechaInicio = itemView.findViewById(R.id.fechaInicio);
            fechaFin = itemView.findViewById(R.id.fechaFin);
            edit = itemView.findViewById(R.id.editIcon);
            delete = itemView.findViewById(R.id.deleteIcon);

        }

        public void bindGuardado(Parcela parcela) {
            TextView field2, field3, field4, field5, field6, field7, field8;
            ImageView field1;

            field1 = mView.findViewById(R.id.imagenParcela);
            Glide.with(itemView).load(databaseReference).placeholder(R.drawable.parcelasviso).error(R.drawable.parcelasviso).into(field1);

            field2 = mView.findViewById(R.id.nombreParcela);
            field2.setText(parcela.getNombre());

            field3 = mView.findViewById(R.id.id);
            field3.setText("Id: " + parcela.getId());

            field4 = mView.findViewById(R.id.metros);
            field4.setText(parcela.getMetros());

            field5 = mView.findViewById(R.id.tipoCultivo);
            field5.setText("Cultivo: " + parcela.getTipo());

            field6 = mView.findViewById(R.id.tipoRiego);
            field6.setText("Riego: " + parcela.getRiego());

            field7 = mView.findViewById(R.id.fechaInicio);
            field7.setText("Fecha Plantación: \n" + parcela.getFechainicio());

            field8 = mView.findViewById(R.id.fechaFin);
            field8.setText("Fecha Recolección: \n" + parcela.getFechafin());
        }

    }
}