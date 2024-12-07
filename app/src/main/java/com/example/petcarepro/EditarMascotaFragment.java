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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petcarepro.db.DatabaseAdmin;
import com.example.petcarepro.db.Mascotas;
import com.example.petcarepro.model.Mascota;


public class EditarMascotaFragment extends Fragment {
    private String[] especies = {"Perro", "Gato"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_mascota, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonCancelar = view.findViewById(R.id.buttonCancelar);
        Button buttonGuardar = view.findViewById(R.id.buttonGuardar);

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

        EditText nombreInput = view.findViewById(R.id.nombreMascotaEditar);
        Spinner especieSpinner = view.findViewById(R.id.especieMascotaEditar);
        EditText razaInput = view.findViewById(R.id.razaMascotaEditar);
        EditText fechaNacimientoInput = view.findViewById(R.id.fechaNacimientoMascotaEditar);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, especies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        especieSpinner.setAdapter(adapter);

        especieSpinner.setSelection(adapter.getPosition(mascota.getEspecie()));

        nombreInput.setText(mascota.getNombre());
        razaInput.setText(mascota.getRaza());
        fechaNacimientoInput.setText(mascota.getFechaNacimiento());


        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Regresa al fragmento anterior
                Navigation.findNavController(v).popBackStack();
            }
        });

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreInput.getText().toString();
                String especie = especieSpinner.getSelectedItem().toString();
                String raza = razaInput.getText().toString();
                String fechaNacimiento = fechaNacimientoInput.getText().toString();

                mascota.setEspecie(especie);
                mascota.setNombre(nombre);
                mascota.setRaza(raza);
                mascota.setFechaNacimiento(fechaNacimiento);

                DatabaseAdmin db = new DatabaseAdmin(getContext());
                Mascotas mascotas = new Mascotas(db);

                boolean editado = mascotas.editarMascota(mascota);
                db.close();

                if (editado) {
                    Toast.makeText(getContext(), "Mascota editada", Toast.LENGTH_SHORT).show();
                    // cerrar la actividad completa para que se actualicen los datos
                    requireActivity().finish();
                    return;
                }

                Toast.makeText(getContext(), "Error al editar la mascota", Toast.LENGTH_SHORT).show();
            }
        });
    }
}