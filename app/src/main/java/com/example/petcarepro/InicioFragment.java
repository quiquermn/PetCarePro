package com.example.petcarepro;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.petcarepro.db.DatabaseAdmin;
import com.example.petcarepro.db.Usuarios;
import com.example.petcarepro.model.Usuario;


public class InicioFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button addMascotaButton = view.findViewById(R.id.addMascotaButton);

        addMascotaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AgregarMascotaActivity.class);
                startActivity(intent);
            }
        });

        TextView textBienvenida = view.findViewById(R.id.textBienvenida);

        DatabaseAdmin databaseAdmin = new DatabaseAdmin(view.getContext());
        Usuarios usuarios = new Usuarios(databaseAdmin);
        Usuario usuario = usuarios.getCurUser();
        databaseAdmin.close();
        textBienvenida.setText(String.format("Hola, %s, empecemos a cuidar juntos de tus mascotitas…", usuario.getNombre()));


    }
}