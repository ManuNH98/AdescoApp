package com.navarromanuel.adescoapp.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidades.Parcela;
import com.navarromanuel.adescoapp.interfaces.iComunicaFragments;
import com.navarromanuel.adescoapp.ui.almacen.AlmacenFragment;
import com.navarromanuel.adescoapp.ui.cuaderno.CuadernoFragment;
import com.navarromanuel.adescoapp.ui.parcela.DetalleParcelaFragment;
import com.navarromanuel.adescoapp.ui.parcela.ParcelaFragment;
import com.navarromanuel.adescoapp.ui.perfil.PerfilFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MenuActivity extends AppCompatActivity implements iComunicaFragments {

    BottomNavigationView navView;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DetalleParcelaFragment detalleParcelaFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        navView = findViewById(R.id.nav_view);
        frameLayout = findViewById(R.id.nav_host_fragment);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectListener);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_parcela, R.id.navigation_calendario, R.id.navigation_cuaderno, R.id.navigation_almacen, R.id.navigation_perfil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_almacen:
                    setFragment(new AlmacenFragment());
                    return true;
                case R.id.navigation_cuaderno:
                    setFragment(new CuadernoFragment());
                    return true;
                case R.id.navigation_parcela:
                    setFragment(new ParcelaFragment());
                    return true;
                case R.id.navigation_perfil:
                    setFragment(new PerfilFragment());
                    return true;
            }
            return false;
        }
    };

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment);
        fragmentTransaction.commit();
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