package com.example.flujodedinero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.flujodedinero.Models.gasto;
import com.example.flujodedinero.Models.ingresos;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class registro extends AppCompatActivity {
    RadioButton radioButton_ingresos, radioButton_gastos;
    Button btn_regis;
    EditText input_nombre, input_monto, input_fecha;

    ingresos ingreso_select;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);


        radioButton_gastos = (RadioButton) findViewById(R.id.radio_gastos);
        radioButton_ingresos = (RadioButton) findViewById(R.id.radio_ingresos);
        btn_regis = (Button) findViewById(R.id.btn_regis_registrar);

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (validar()) {
                    case 1:
                        insertarIngre();
                        break;


                    case 2:
                        insertarGasto();
                        break;
                }


            }
        });

        inicializarFirebase();


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    private int validar() {
        String cadena = "seleccionado: ";
        int opc = 0;

        if (radioButton_ingresos.isChecked()) {
            cadena += "ingresos";
            opc = 1;
        }
        if (radioButton_gastos.isChecked()) {
            cadena += "gastos";
            opc = 2;
        }
        Toast.makeText(getApplicationContext(), cadena, Toast.LENGTH_SHORT).show();
        return opc;


    }

    public void onRadioButtonClicked(View view) {

    }

    private void insertarIngre() {
        input_nombre = findViewById(R.id.input_nombre);
        input_monto = findViewById(R.id.input_monto);
        input_fecha = findViewById(R.id.input_fecha);

        String nombre = input_nombre.getText().toString();
        String monto = input_monto.getText().toString();
        String fecha = input_fecha.getText().toString();

        if (nombre.isEmpty()) {
            showError(input_nombre, "Todos los campos son necesarios");
        } else if (monto.isEmpty()) {
            showError(input_monto, "Todos los campos son necesarios");
        } else if (fecha.isEmpty()) {
            showError(input_fecha, "Todos los campos son necesarios");
        } else if (!radioButton_ingresos.isChecked() && !radioButton_gastos.isChecked()) {
            showErrorRadio("Selecciona uno");
        } else {
            ingresos p = new ingresos();
            p.setId(UUID.randomUUID().toString());
            p.setNombre(nombre);
            p.setMonto(monto);
            p.setFecha(fecha);
            databaseReference.child("ingresos").child(p.getId()).setValue(p);
            Toast.makeText(registro.this, "Registrado correctamente", Toast.LENGTH_LONG).show();


            startActivity(new Intent(registro.this, MainActivity.class));
        }


    }

    private void insertarGasto() {
        input_nombre = findViewById(R.id.input_nombre);
        input_monto = findViewById(R.id.input_monto);
        input_fecha = findViewById(R.id.input_fecha);

        String nombre = input_nombre.getText().toString();
        String monto = input_monto.getText().toString();
        String fecha = input_fecha.getText().toString();

        if (nombre.isEmpty()) {
            showError(input_nombre, "Todos los campos son necesarios");
        } else if (monto.isEmpty()) {
            showError(input_monto, "Todos los campos son necesarios");
        } else if (fecha.isEmpty()) {
            showError(input_fecha, "Todos los campos son necesarios");
        } else if (!radioButton_ingresos.isChecked() && !radioButton_gastos.isChecked()) {
            showErrorRadio("Selecciona uno");
        } else {
            gasto p = new gasto();
            p.setId(UUID.randomUUID().toString());
            p.setNombre(nombre);
            p.setMonto(monto);
            p.setFecha(fecha);
            databaseReference.child("gasto").child(p.getId()).setValue(p);
            Toast.makeText(registro.this, "Registrado correctamente", Toast.LENGTH_LONG).show();


            startActivity(new Intent(registro.this, MainActivity.class));
        }


    }

    public void showError(EditText input, String s) {
        input.requestFocus();
        input.setError(s);
    }

    public void showErrorRadio(String s) {

        if (!radioButton_gastos.isChecked()) {
            radioButton_gastos.requestFocus();
            radioButton_gastos.setError(s);

        } else {
            radioButton_ingresos.requestFocus();
            radioButton_ingresos.setError(s);

        }

    }


}