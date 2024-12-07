package com.example.petcarepro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.petcarepro.db.DatabaseAdmin;
import com.example.petcarepro.db.Mascotas;
import com.example.petcarepro.model.Mascota;

public class DetalleMascotaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_mascota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Mascota mascota = (Mascota) getIntent().getSerializableExtra("mascota"); // Obtenemos la mascota del intent

        if (mascota == null) {
            finish();
            return;
        }

        TextView nombreText = findViewById(R.id.nombreMascotaDetalles);
        TextView especieText = findViewById(R.id.especieMascotaDetalles);
        TextView razaText = findViewById(R.id.razaMascotaDetalles);
        TextView fechaNacimientoText = findViewById(R.id.fechaNacimientoMascotaDetalles);

        nombreText.setText(mascota.getNombre());
        especieText.setText(mascota.getEspecie());
        razaText.setText(mascota.getRaza());
        fechaNacimientoText.setText(mascota.getFechaNacimiento());

        Button regresarButton = findViewById(R.id.buttonRegresar);
        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button eliminarButton = findViewById(R.id.buttonEliminar);
        eliminarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAdmin databaseAdmin = new DatabaseAdmin(DetalleMascotaActivity.this);
                Mascotas mascotas = new Mascotas(databaseAdmin);

                boolean eliminado = mascotas.eliminarMascota(mascota.getId());
                databaseAdmin.close();

                if (!eliminado) {
                    Toast.makeText(DetalleMascotaActivity.this, "Error al eliminar la mascota", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(DetalleMascotaActivity.this, "Mascota eliminada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Button editarButton = findViewById(R.id.buttonEditar);
        editarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetalleMascotaActivity.this, "Editar mascota", Toast.LENGTH_SHORT).show();
            }
        });
    }
}