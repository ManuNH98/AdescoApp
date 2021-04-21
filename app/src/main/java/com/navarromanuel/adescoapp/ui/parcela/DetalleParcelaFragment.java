package com.navarromanuel.adescoapp.ui.parcela;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidades.Parcela;

public class DetalleParcelaFragment extends Fragment {
    TextView nombre, info, metros;
    ImageView imagenid;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detalle_parcela_fragment,container,false);
        nombre = view.findViewById(R.id.nombre_detalle);
        imagenid = view.findViewById(R.id.imagen_detalleId);
        metros = view.findViewById(R.id.metros_detalle);
        info = view.findViewById(R.id.descripcion_detalle);
        Bundle objetoParcela = getArguments();
        Parcela parcela = null;;
        if(objetoParcela !=null){
            parcela = (Parcela) objetoParcela.getSerializable("objeto");
            imagenid.setImageResource(parcela.getImagenid());
            nombre.setText(parcela.getNombre());
            info.setText(parcela.getInfo());
            metros.setText(parcela.getMetros());
        }
        return view;
    }
}
