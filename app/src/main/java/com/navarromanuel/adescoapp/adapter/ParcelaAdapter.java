package com.navarromanuel.adescoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidad.Parcela;

import java.util.ArrayList;
import java.util.List;

public class ParcelaAdapter extends RecyclerView.Adapter<ParcelaAdapter.ViewHolder> {

    private List<Parcela> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ParcelaAdapter(List<Parcela> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    @Override
    public ParcelaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.activity_parcela_list, null);
        return new ParcelaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ParcelaAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Parcela> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenid;
        TextView nombre, info, metros;

        ViewHolder(View itemView) {
            super(itemView);
            imagenid = itemView.findViewById(R.id.imagenParcela);
            nombre = itemView.findViewById(R.id.nombreParcela);
            info = itemView.findViewById(R.id.info);
            metros = itemView.findViewById(R.id.metros);
        }

        public void bindData(final Parcela item){
            imagenid.setImageResource(item.getImagenid());
            nombre.setText(item.getNombre());
            info.setText(item.getInfo());
            metros.setText(item.getMetros());
        }
    }
}