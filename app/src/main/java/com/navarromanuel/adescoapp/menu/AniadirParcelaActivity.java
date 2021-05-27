package com.navarromanuel.adescoapp.menu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.viewmodel.RequestCodes;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.navarromanuel.adescoapp.R;
import com.navarromanuel.adescoapp.entidad.Parcela;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class AniadirParcelaActivity extends AppCompatActivity {

    private Button btnAniadir;
    private EditText edtId, edtNombreParcela, edtMetros, edtTipo, edtFechaInicio, edtFechaFin, edtInfo, edtRiego;

    private ProgressDialog progressDialog;
    FirebaseDatabase basededatos;
    DatabaseReference referencia;

    int i = 0;
    Parcela Parcela;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // FIREBASE
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcela_aniadir);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Parcela = new Parcela();

        edtId = findViewById(R.id.edtId);
        edtNombreParcela = findViewById(R.id.edtNombreParcela);
        edtMetros = findViewById(R.id.edtMetros);
        edtTipo = findViewById(R.id.edtTipo);
        edtRiego = findViewById(R.id.edtRiego);
        edtFechaInicio = findViewById(R.id.edtFechaInicio);
        edtFechaFin = findViewById(R.id.edtFechaFin);
        edtInfo = findViewById(R.id.edtInfo);
        btnAniadir = findViewById(R.id.btnAParcela);

        referencia = basededatos.getInstance().getReference().child("Parcelas").child(""+user.getUid()).push();

        referencia.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    i = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FileUploader();
            }
        });

    }

    private void FileUploader(){
        Parcela.setId(edtId.getText().toString());
        referencia.setValue(Parcela);
        Parcela.setNombre(edtNombreParcela.getText().toString());
        referencia.setValue(Parcela);
        Parcela.setMetros(edtMetros.getText().toString());
        referencia.setValue(Parcela);
        Parcela.setTipo(edtTipo.getText().toString());
        referencia.setValue(Parcela);
        Parcela.setRiego(edtRiego.getText().toString());
        referencia.setValue(Parcela);
        Parcela.setFechainicio(edtFechaInicio.getText().toString());
        referencia.setValue(Parcela);
        Parcela.setFechafin(edtFechaFin.getText().toString());
        referencia.setValue(Parcela);
        Parcela.setInfo(edtInfo.getText().toString());
        referencia.setValue(Parcela);

        Parcela.setUid(user.getUid());
        referencia.setValue(Parcela);

        //Toast.makeText(ThirdActivity.this, "Registrado", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "Se ha registrado los datos correctamente", Toast.LENGTH_SHORT).show();
        //dialog.cancel();

        Intent intent = new Intent(getApplicationContext(), ParcelaActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                //overridePendingTransition(R.anim.right_in, R.anim.right_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}