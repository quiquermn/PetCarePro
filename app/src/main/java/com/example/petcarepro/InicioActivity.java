package com.example.petcarepro;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class InicioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int NAV_INICIO = R.id.nav_inicio;
    private static final int NAV_DATOS = R.id.nav_datos;
    private static final int NAV_CONSEJOS = R.id.nav_consejos;
    private static final int NAV_EXIT = R.id.nav_salir;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Inicio");

        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new InicioFragment()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        drawerLayout.closeDrawer(GravityCompat.START);

        if (itemId == NAV_INICIO) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new InicioFragment()).commit();

            drawerLayout.setBackgroundResource(R.drawable.fondoinicio);

            toolbar.setBackgroundColor(Color.parseColor("#00000000"));
            toolbar.setTitle("Inicio");

        } else if (itemId == NAV_DATOS) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new DatosFragment()).commit();

            drawerLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

            toolbar.setBackgroundColor(Color.parseColor("#dab4f6"));
            toolbar.setTitle("Tus mascotas");
        } else if (itemId == NAV_CONSEJOS) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new ConsejosFragment()).commit();

            drawerLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));

            toolbar.setBackgroundColor(Color.parseColor("#dab4f6"));
            toolbar.setTitle("Consejos");
        }

        return true;
    }
}