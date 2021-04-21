package com.navarromanuel.adescoapp.activity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidades.Parcela;
import com.navarromanuel.adescoapp.interfaces.iComunicaFragments;
import com.navarromanuel.adescoapp.ui.parcela.DetalleParcelaFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MenuActivity extends AppCompatActivity implements iComunicaFragments {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DetalleParcelaFragment detalleParcelaFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_parcela, R.id.navigation_calendario, R.id.navigation_cuaderno, R.id.navigation_almacen, R.id.navigation_perfil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void enviarParcela(Parcela parcela) {
        detalleParcelaFragment = new DetalleParcelaFragment();
        Bundle bundleEnvio = new Bundle();
        bundleEnvio.putSerializable("objeto", parcela);
        detalleParcelaFragment.setArguments(bundleEnvio);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, detalleParcelaFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}