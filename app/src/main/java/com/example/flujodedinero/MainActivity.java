package com.example.flujodedinero;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.flujodedinero.Adaptadores.ListviewIngresosAdapter;
import com.example.flujodedinero.Models.ingresos;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_registrar, btningresos, btngastos;


    private ArrayList<ingresos> listingresos = new ArrayList<ingresos>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_registrar = (Button) findViewById(R.id.btn_registrar);
        btningresos = (Button) findViewById(R.id.btn_ingresos);
        btngastos = (Button) findViewById(R.id.btn_gastos);


        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, registro.class));
            }
        });

        btningresos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Ingresos.class));
            }
        });

        btngastos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        gastos.class));
            }
        });


    }


}