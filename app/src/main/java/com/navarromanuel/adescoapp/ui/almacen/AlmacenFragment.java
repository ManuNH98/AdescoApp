package com.navarromanuel.adescoapp.ui.almacen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.ui.calendario.CalendarioViewModel;

public class AlmacenFragment extends Fragment {

    private AlmacenViewModel almacenViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        almacenViewModel =
                new ViewModelProvider(this).get(AlmacenViewModel.class);
        View root = inflater.inflate(R.layout.fragment_almacen, container, false);
        final TextView textView = root.findViewById(R.id.text_almacen);
        almacenViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}