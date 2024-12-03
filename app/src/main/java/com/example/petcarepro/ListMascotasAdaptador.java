package com.example.petcarepro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.petcarepro.model.Mascota;

import java.util.List;

public class ListMascotasAdaptador extends BaseAdapter {
    private Context contexto;
    private List<Mascota> listaMascotas;
    private LayoutInflater inflater;

    public ListMascotasAdaptador(Context contexto, List<Mascota> listaMascotas) {
        this.contexto = contexto;
        this.listaMascotas = listaMascotas;
        this.inflater = LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return listaMascotas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaMascotas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaMascotas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.mascota_item, null);
        TextView mascotaNombre = convertView.findViewById(R.id.nombreMascotaList);
        TextView mascotaEspecie = convertView.findViewById(R.id.especieMascotaList);

        mascotaNombre.setText(listaMascotas.get(position).getNombre());
        mascotaEspecie.setText(listaMascotas.get(position).getEspecie());
        return convertView;
    }
}
