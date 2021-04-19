package com.navarromanuel.adescoapp.ui.parcela;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.navarromanuel.adescoapp.R;

public class ParcelaFragment extends Fragment {

    private ParcelaViewModel parcelaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        parcelaViewModel =
                new ViewModelProvider(this).get(ParcelaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_parcela, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        parcelaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}