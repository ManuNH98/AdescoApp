package com.navarromanuel.adescoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidades.Parcela;

import java.util.ArrayList;

public class AdapterParcela extends RecyclerView.Adapter<AdapterParcela.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    ArrayList<Parcela> model;

    private View.OnClickListener listener;

    public AdapterParcela(Context context, ArrayList<Parcela> model){
        this.inflater = LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_parcela, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnclickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String nombres = model.get(position).getNombre();
        String info = model.get(position).getInfo();
        String metros = model.get(position).getMetros();
        int imageid = model.get(position).getImagenid();
        holder.nombres.setText(nombres);
        holder.info.setText(info);
        holder.metros.setText(metros);
        holder.imagenid.setImageResource(imageid);
    }


    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombres, info, metros;
        ImageView imagenid;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            nombres = itemView.findViewById(R.id.nombreParcela);
            info = itemView.findViewById(R.id.info);
            metros = itemView.findViewById(R.id.metrosParcela);
            imagenid = itemView.findViewById(R.id.imagenParcela);
        }

    }

}
