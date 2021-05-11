package com.navarromanuel.adescoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.PojoInventario;

public class GuardadoAdapter extends FirebaseRecyclerAdapter<PojoInventario, GuardadoHolder> {

    public GuardadoAdapter(FirebaseRecyclerOptions opciones){
        super(opciones);
    }
    @Override
    public GuardadoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_fitosanitario, parent, false);

        return new GuardadoHolder(view);
    }

    @Override
    protected void onBindViewHolder(GuardadoHolder saveViewHolder, final int position, PojoInventario pojoI) {
        saveViewHolder.bindGuardado(pojoI);
    }



}
