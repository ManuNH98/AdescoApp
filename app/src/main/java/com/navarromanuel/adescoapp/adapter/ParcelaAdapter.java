package com.navarromanuel.adescoapp.adapter;

import android.app.AlertDialog;
import android.content.Context;
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
import com.google.firebase.database.FirebaseDatabase;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidad.Parcela;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ParcelaAdapter extends FirebaseRecyclerAdapter<Parcela,ParcelaAdapter.ParcelaViewHolder> {

    public ParcelaAdapter(@NonNull FirebaseRecyclerOptions<Parcela> options){
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final ParcelaViewHolder parcelaViewHolder, final int position, @NonNull final Parcela parcela){
        parcelaViewHolder.nombre.setText(parcela.getNombre());
        parcelaViewHolder.tipoCultivo.setText(parcela.getTipo());
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
                final EditText metros=myview.findViewById(R.id.metrosEditar);
                final EditText fechaInicio=myview.findViewById(R.id.fechaInicioEditar);
                final EditText fechaFin=myview.findViewById(R.id.fechaFinEditar);
                final EditText info=myview.findViewById(R.id.infoEditar);
                Button submit=myview.findViewById(R.id.btnEditar);

                nombre.setText(parcela.getNombre());
                tipoCultivo.setText(parcela.getTipo());
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
                            map.put("tipoCultivo",tipoCultivo.getText().toString());
                            map.put("metros",metros.getText().toString());
                            map.put("fechaInicio",fechaInicio.getText().toString());
                            map.put("fechaFin",fechaFin.getText().toString());
                            map.put("info",info.getText().toString());

                            FirebaseDatabase.getInstance().getReference().child("Parcelas")
                                    .child(getRef(position).getKey()).updateChildren(map)
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

        /*parcelaViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(parcelaViewHolder.nombre.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Parcelas")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });*/

    }

    @NonNull
    @Override
    public ParcelaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_parcela_list, parent, false);
        return new ParcelaViewHolder(v);
    }

    class ParcelaViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView nombre, info, metros, tipoCultivo, fechaInicio, fechaFin;
        ImageView edit;

        public ParcelaViewHolder(@NonNull View itemView){
            super(itemView);

            nombre = itemView.findViewById(R.id.nombreParcela);
            info = itemView.findViewById(R.id.info);
            metros = itemView.findViewById(R.id.metros);
            tipoCultivo = itemView.findViewById(R.id.tipoCultivo);
            fechaInicio = itemView.findViewById(R.id.fechaInicio);
            fechaFin = itemView.findViewById(R.id.fechaFin);
            edit = itemView.findViewById(R.id.editIcon);

        }
    }
}