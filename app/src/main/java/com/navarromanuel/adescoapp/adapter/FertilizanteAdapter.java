package com.navarromanuel.adescoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.PojoInventario;

public class FertilizanteAdapter extends FirebaseRecyclerAdapter<PojoInventario, FertilizanteHolder> {

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
        saveViewHolder.bindGuardado(pojoI);
    }



}
