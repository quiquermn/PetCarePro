package com.example.petcarepro;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.petcarepro.db.DatabaseAdmin;
import com.example.petcarepro.db.Mascotas;
import com.example.petcarepro.db.Usuarios;
import com.example.petcarepro.model.Mascota;
import com.example.petcarepro.model.Usuario;

public class AgregarMascotaActivity extends AppCompatActivity {
    private EditText nombreMascotaInput;
    private Spinner especieMascotaSpinner;
    private EditText razaMascotaInput;
    private EditText fechaNacimientoMascotaInput;

    private String[] especies = {"Selecciona una especie", "Perro", "Gato"};

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

        nombreMascotaInput = findViewById(R.id.inputNombreMascota);
        especieMascotaSpinner = findViewById(R.id.spinnerEspecie);
        razaMascotaInput = findViewById(R.id.inputRaza);
        fechaNacimientoMascotaInput = findViewById(R.id.inputFechaNacimiento);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, especies);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        especieMascotaSpinner.setAdapter(adapter);

        agregarMascotaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreMascota = nombreMascotaInput.getText().toString().trim();
                String especieMascota = especieMascotaSpinner.getSelectedItem().toString();
                String razaMascota = razaMascotaInput.getText().toString().trim();
                String fechaNacimientoMascota = fechaNacimientoMascotaInput.getText().toString().trim();


                if (nombreMascota.isEmpty()) {
                    Toast.makeText(AgregarMascotaActivity.this, "El nombre de la mascota es requerido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (especieMascota.equals(especies[0])) { // Si la especie es la primera opción, no está lleno
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

                String[] fechas = fechaNacimientoMascota.split("/");

                if (fechas.length != 3) {
                    Toast.makeText(AgregarMascotaActivity.this, "Fecha de nacimiento inválida", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fechas[0].length() != 2 || fechas[1].length() != 2 || fechas[2].length() != 2) {
                    Toast.makeText(AgregarMascotaActivity.this, "Se debe de cumplir el formato dd/mm/aa", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseAdmin databaseAdmin = new DatabaseAdmin(AgregarMascotaActivity.this);
                Usuarios usuarios = new Usuarios(databaseAdmin);
                Usuario currentUsuario = usuarios.getCurUser();

                Mascota mascota = new Mascota(nombreMascota, especieMascota, razaMascota, fechaNacimientoMascota, currentUsuario.getIdUsuario());
                Mascotas mascotas = new Mascotas(databaseAdmin);
                mascotas.insertarMascota(mascota);

                databaseAdmin.close();

                limpiar();
                Toast.makeText(AgregarMascotaActivity.this, "Mascota agregada.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void limpiar() {
        nombreMascotaInput.setText("");
        especieMascotaSpinner.setSelection(0);
        razaMascotaInput.setText("");
        fechaNacimientoMascotaInput.setText("");
    }
}