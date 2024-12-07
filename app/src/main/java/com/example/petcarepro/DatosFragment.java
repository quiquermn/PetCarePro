package com.example.petcarepro;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petcarepro.db.DatabaseAdmin;
import com.example.petcarepro.db.Mascotas;
import com.example.petcarepro.model.Mascota;

import java.util.List;


public class DatosFragment extends Fragment implements AdapterView.OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true); // Habilita el menú

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.datos_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.datos_agregarMascota) {
            Intent intent = new Intent(getActivity(), AgregarMascotaActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        this.cargarDatos();
    }

    @Override
    public void onResume() { // Después de que se elimina una mascota, se vuelve a cargar la lista
        super.onResume();
        cargarDatos();
    }

    private void cargarDatos() {
        FragmentActivity activity = getActivity();

        if (activity == null) {
            return;
        }

        DatabaseAdmin databaseAdmin = new DatabaseAdmin(activity);
        Mascotas mascotas = new Mascotas(databaseAdmin);
        List<Mascota> listaMascotas = mascotas.getMascotasFromCurUser();

        databaseAdmin.close();

        ListView listView = getView().findViewById(R.id.mascotasListView);
        ListMascotasAdaptador adaptador = new ListMascotasAdaptador(activity, listaMascotas);
        listView.setAdapter(adaptador);

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Mascota mascota = (Mascota) parent.getItemAtPosition(position);
        if (mascota == null) {
            // Significa que no hay mascotas en la lista
            Intent intent = new Intent(getActivity(), AgregarMascotaActivity.class);
            startActivity(intent);
            return;
        }

        Intent intent = new Intent(getActivity(), DetalleMascotaActivity.class);

        intent.putExtra("mascota", mascota); // Añade la mascota al intent
        startActivity(intent);
    }
}