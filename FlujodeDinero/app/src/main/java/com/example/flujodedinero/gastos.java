package com.example.flujodedinero;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.flujodedinero.Adaptadores.ListviewIngresosAdapter;
import com.example.flujodedinero.Models.gasto;
import com.example.flujodedinero.Models.ingresos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class gastos extends AppCompatActivity {
    Button btn_atras;

    private ArrayList<gasto> listGastos = new ArrayList<gasto>();
    ArrayAdapter<gasto> arrayAdapterGastos;
    ListviewIngresosAdapter listviewGastoAdapter;
    ListView listview_gasto;

    gasto gasto_select;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gastos);
        btn_atras = (Button) findViewById(R.id.btn_Atras);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(gastos.this, MainActivity.class));
            }
        });

        listview_gasto = findViewById(R.id.listview_gasto);

        listview_gasto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gasto_select= (gasto) parent.getItemAtPosition(position);


            }
        });

        inicializarFirebase();
        listarIngresos();


    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
    }

    private  void  listarIngresos(){
        databaseReference.child("gasto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listGastos.clear();
                for (DataSnapshot objSnaptshot : snapshot.getChildren() ){
                    gasto p= objSnaptshot.getValue(gasto.class);
                    listGastos.add(p);
                }
                arrayAdapterGastos = new ArrayAdapter<gasto>(
                        gastos.this, android.R.layout.simple_list_item_1, listGastos

                );
                listview_gasto.setAdapter(arrayAdapterGastos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}