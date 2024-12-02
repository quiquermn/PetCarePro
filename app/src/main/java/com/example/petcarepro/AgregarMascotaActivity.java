package com.example.petcarepro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AgregarMascotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_mascota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button regresarButton = findViewById(R.id.buttonRegresar);
        Button agregarMascotaButton = findViewById(R.id.buttonAgregarMascota);

        agregarMascotaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nombreMascotaInput = findViewById(R.id.inputNombreMascota);
                EditText especieMascotaInput = findViewById(R.id.inputEspecie);
                EditText razaMascotaInput = findViewById(R.id.inputRaza);
                EditText fechaNacimientoMascotaInput = findViewById(R.id.inputFechaNacimiento);

                String nombreMascota = nombreMascotaInput.getText().toString().trim();
                String especieMascota = especieMascotaInput.getText().toString().trim();
                String razaMascota = razaMascotaInput.getText().toString().trim();
                String fechaNacimientoMascota = fechaNacimientoMascotaInput.getText().toString().trim();


                if (nombreMascota.isEmpty()) {
                    Toast.makeText(AgregarMascotaActivity.this, "El nombre de la mascota es requerido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (especieMascota.isEmpty()) {
                    Toast.makeText(AgregarMascotaActivity.this, "La especie de la mascota es requerida", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (razaMascota.isEmpty()) {
                    Toast.makeText(AgregarMascotaActivity.this, "La raza de la mascota es requerida", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (fechaNacimientoMascota.isEmpty()) {
                    Toast.makeText(AgregarMascotaActivity.this, "La fecha de nacimiento de la mascota es requerida", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(AgregarMascotaActivity.this, "Mascota agregada correctamente", Toast.LENGTH_SHORT).show();

            }
        });

        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}