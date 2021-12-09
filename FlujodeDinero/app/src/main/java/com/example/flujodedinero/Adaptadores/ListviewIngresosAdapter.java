package com.example.flujodedinero.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.flujodedinero.Models.ingresos;
import com.example.flujodedinero.R;

import java.util.ArrayList;

public class ListviewIngresosAdapter extends BaseAdapter {
    Context context;
    ArrayList<ingresos> ingresosdata;
    LayoutInflater layoutInflater;
    ingresos ingresosmodel;

    public ListviewIngresosAdapter(Context context, ArrayList<ingresos> ingresosdata) {
        this.context = context;
        this.ingresosdata = ingresosdata;
        layoutInflater = (LayoutInflater) context.getSystemService(
                context.LAYOUT_INFLATER_SERVICE
        );
    }

    @Override
    public int getCount() {
        return ingresosdata.size();
    }

    @Override
    public Object getItem(int position) {
        return ingresosdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null){
            rowView = layoutInflater.inflate(R.layout.ingresos,
                    null,
                    true);
        }
        //enlazar vistas
        TextView nombre = rowView.findViewById(R.id.cantnombre);
        TextView monto = rowView.findViewById(R.id.cantmonto);
        TextView fecha = rowView.findViewById(R.id.cantfecha);


        ingresosmodel = ingresosdata.get(position);
        nombre.setText(ingresosmodel.getNombre());
        monto.setText(ingresosmodel.getMonto());
        fecha.setText(ingresosmodel.getFecha());

        //E
        return rowView;
    }
}
