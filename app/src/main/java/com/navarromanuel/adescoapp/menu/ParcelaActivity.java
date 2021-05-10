package com.navarromanuel.adescoapp.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.adapter.ParcelaAdapter;

public class ParcelaActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ParcelaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela);


    }
}