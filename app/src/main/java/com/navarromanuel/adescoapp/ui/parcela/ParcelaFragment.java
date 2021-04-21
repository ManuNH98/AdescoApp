package com.navarromanuel.adescoapp.ui.parcela;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.adapters.AdapterParcela;
import com.navarromanuel.adescoapp.entidades.Parcela;
import com.navarromanuel.adescoapp.interfaces.iComunicaFragments;

import java.util.ArrayList;

public class ParcelaFragment  extends Fragment {
    AdapterParcela adapterParcela;
    RecyclerView recyclerViewParcela;
    ArrayList<Parcela> listParcela;
    EditText txtnombre;

    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_parcela,container,false);
        txtnombre = view.findViewById(R.id.txtNombre);

        recyclerViewParcela = view.findViewById(R.id.recyclerView);
        listParcela = new ArrayList<>();
        cargarLista();
        mostrarData();
        return view;
    }
    public void cargarLista(){
        listParcela.add(new Parcela("CARPACCIO DI MANZO","Carpaccio de solomillo, queso parmesano, champiñones y rúcula",R.drawable.ic_launcher_foreground, "10m"));
        listParcela.add(new Parcela("CALAMARI ROMANA","Calamares saharianos a la romana",R.drawable.ic_launcher_foreground, "11m"));
        listParcela.add(new Parcela("CANNELLONI","Canneloni rellenos de carce y espinacas",R.drawable.ic_launcher_foreground, "8m"));
        listParcela.add(new Parcela("FOCACCIA","Pan de pizza con tomate",R.drawable.ic_launcher_foreground, "3m"));
        listParcela.add(new Parcela("MARGHERITA","Pizza con tomate y mozarella",R.drawable.ic_launcher_foreground, "6m"));
        listParcela.add(new Parcela("CAPRICCIOSA","Pizza con tomate, mozarella, jamón, champiñones, aceitunas y salchichas",R.drawable.ic_launcher_foreground, "9m"));
        listParcela.add(new Parcela("DIAVOLA","Pizza con tomate, mozarella y salami picante",R.drawable.ic_launcher_foreground, "9m"));


    }
    private void mostrarData(){
        recyclerViewParcela.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterParcela = new AdapterParcela(getContext(), listParcela);
        recyclerViewParcela.setAdapter(adapterParcela);

        adapterParcela.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = listParcela.get(recyclerViewParcela.getChildAdapterPosition(view)).getNombre();
                txtnombre.setText(nombre);
                Toast.makeText(getContext(), "Seleccionó: "+ listParcela.get(recyclerViewParcela.getChildAdapterPosition(view)).getNombre(), Toast.LENGTH_SHORT).show();
                interfaceComunicaFragments.enviarParcela(listParcela.get(recyclerViewParcela.getChildAdapterPosition(view)));
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad= (Activity) context;
            interfaceComunicaFragments= (iComunicaFragments) this.actividad;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
