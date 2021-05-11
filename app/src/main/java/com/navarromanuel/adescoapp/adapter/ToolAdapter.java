package com.navarromanuel.adescoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.activity.PojoInventario;

public class ToolAdapter extends FirebaseRecyclerAdapter<PojoInventario, ToolHolder> {

    public ToolAdapter(FirebaseRecyclerOptions opciones){
        super(opciones);
    }
    @Override
    public ToolHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_tool, parent, false);

        return new ToolHolder(view);
    }

    @Override
    protected void onBindViewHolder(ToolHolder saveViewHolder, final int position, PojoInventario pojoI) {
        saveViewHolder.bindGuardado(pojoI);
    }



}
