package com.example.flujodedinero;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.example.flujodedinero.Adaptadores.ListviewIngresosAdapter;
import com.example.flujodedinero.Models.ingresos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Ingresos extends AppCompatActivity {
    Button btn_atras;
    private ArrayList<ingresos> listIngresos = new ArrayList<ingresos>();
    ArrayAdapter<ingresos> arrayAdapteringresos;
    ListviewIngresosAdapter listviewIngresosAdapter;
    ListView listview_ingresos;

    ingresos ingreso_select;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresos);

        btn_atras = (Button) findViewById(R.id.btn_Atras);
        btn_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Ingresos.this, MainActivity.class));
            }
        });

        listview_ingresos = findViewById(R.id.listview_ingresos);

        listview_ingresos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ingreso_select = (ingresos) parent.getItemAtPosition(position);
            }
        });


        inicializarFirebase();
        listarIngresos();


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void listarIngresos() {
        databaseReference.child("ingresos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listIngresos.clear();
                for (DataSnapshot objSnaptshot : snapshot.getChildren()) {
                    ingresos p = objSnaptshot.getValue(ingresos.class);
                    listIngresos.add(p);
                }
                arrayAdapteringresos = new ArrayAdapter<ingresos>(
                        Ingresos.this, android.R.layout.simple_list_item_1, listIngresos

                );
                listview_ingresos.setAdapter(arrayAdapteringresos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}