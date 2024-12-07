package com.example.petcarepro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        // Esto es para que aparezca el mensaje de "No hay mascotas" cuando la lista está vacía
        if (listaMascotas == null || listaMascotas.isEmpty()) {
            return 1;
        }
        return listaMascotas.size();
    }

    @Override
    public Mascota getItem(int position) {
        // Esto es para que no haya error al obtener una mascota de una lista vacía
        if (listaMascotas == null || listaMascotas.isEmpty()) {
            return null;
        }
        return listaMascotas.get(position);
    }

    @Override
    public long getItemId(int position) {
        // Esto es para que no haya error al intentar obtener el id de una lista vacía
        if (listaMascotas == null || listaMascotas.isEmpty()) {
            return 0;
        }
        return listaMascotas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.mascota_item, null);
        TextView mascotaNombre = convertView.findViewById(R.id.nombreMascotaList);
        TextView mascotaEspecie = convertView.findViewById(R.id.especieMascotaList);
        ImageView imageView = convertView.findViewById(R.id.iconoMascotaList);


        if (listaMascotas.isEmpty()) {
            imageView.setImageResource(R.drawable.icono_add);

            mascotaNombre.setText("Aún no tienes mascotas guardadas");
            mascotaEspecie.setText("Agrega una");
        } else {
            mascotaNombre.setText(listaMascotas.get(position).getNombre());
            mascotaEspecie.setText("Especie: " + listaMascotas.get(position).getEspecie());
            imageView.setImageResource(R.drawable.icono);
        }


        return convertView;
    }
}
