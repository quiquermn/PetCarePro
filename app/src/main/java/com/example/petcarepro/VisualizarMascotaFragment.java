package com.example.petcarepro;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.petcarepro.model.Mascota;


public class VisualizarMascotaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visualizar_mascota, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonEditar = view.findViewById(R.id.buttonEditar);

        // Obtenido de: https://stackoverflow.com/questions/73598778/passing-data-from-activity-to-fragmentnavigation-component#73599612
        // Obtiene la mascota del intent que se utilizó para abrir la actividad principal
        Intent intent = requireActivity().getIntent();
        if (intent == null) {
            return;
        }

        Mascota mascota = (Mascota) intent.getSerializableExtra("mascota");

        if (mascota == null) {
            return;
        }

        TextView nombreText = view.findViewById(R.id.nombreMascotaDetalles);
        TextView especieText = view.findViewById(R.id.especieMascotaDetalles);
        TextView razaText = view.findViewById(R.id.razaMascotaDetalles);
        TextView fechaNacimientoText = view.findViewById(R.id.fechaNacimientoMascotaDetalles);

        nombreText.setText(mascota.getNombre());
        especieText.setText(mascota.getEspecie());
        razaText.setText(mascota.getRaza());
        fechaNacimientoText.setText(mascota.getFechaNacimiento());

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.editarMascotaFragment);
            }
        });
    }
}