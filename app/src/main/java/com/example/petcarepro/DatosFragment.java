package com.example.petcarepro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petcarepro.db.DatabaseAdmin;
import com.example.petcarepro.db.Mascotas;
import com.example.petcarepro.model.Mascota;

import java.util.List;


public class DatosFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentActivity activity = getActivity();

        if (activity == null) {
            return;
        }

        DatabaseAdmin databaseAdmin = new DatabaseAdmin(activity);
        Mascotas mascotas = new Mascotas(databaseAdmin);
        List<Mascota> listaMascotas = mascotas.getMascotasFromCurUser();

        TextView tituloDatos = activity.findViewById(R.id.tituloDatos);

        for (int i = 0; i < listaMascotas.size(); i++) {
            Mascota mascota = listaMascotas.get(i);
            tituloDatos.setText(tituloDatos.getText() + mascota.getNombre() + "-" + i + "\n");
        }
    }
}