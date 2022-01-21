package com.example.flujodedinero.Models;

public class gasto {
    private String nombre;
    private String monto;
    private String fecha;
    private String id;

    public String getId() {return id;}

    public String getNombre() {
        return nombre;
    }

    public String getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setId(String id) { this.id = id;}

    @Override
    public String toString() {
        return "Nombre: "+ nombre+
                "\nMonto: "+ monto +
                "\nFecha: "+ fecha;
    }
}
