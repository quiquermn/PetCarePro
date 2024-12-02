package com.example.petcarepro;

import android.content.Intent;
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
import com.example.petcarepro.db.Usuarios;
import com.example.petcarepro.model.Usuario;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button registerButton = findViewById(R.id.buttonRegistrarse);
        Button regresarButton = findViewById(R.id.buttonRegresar);

        regresarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Debería de regresar a la actividad anterior
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView inputNombre = findViewById(R.id.inputNombreRegister);
                TextView inputEmail = findViewById(R.id.inputEmailRegister);
                TextView inputPassword = findViewById(R.id.inputPasswordRegister);

                String nombre = inputNombre.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();

                if (nombre.trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (email.trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "El email no puede estar vacío", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!email.contains("@")) {
                    Toast.makeText(RegisterActivity.this, "Email inválido", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatabaseAdmin databaseAdmin = new DatabaseAdmin(RegisterActivity.this);
                Usuarios usuarios = new Usuarios(databaseAdmin);

                boolean registrado = usuarios.insertarUsuario(new Usuario(nombre, email, password));

                databaseAdmin.close();

                if (registrado) {
                    Toast.makeText(RegisterActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                    usuarios.login(email, password);
                    Intent intent = new Intent(RegisterActivity.this, InicioActivity.class);
                    startActivity(intent);

                    return;
                }

                Toast.makeText(RegisterActivity.this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();


            }
        });
    }
}