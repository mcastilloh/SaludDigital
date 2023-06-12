package fia.ues.saluddigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fia.ues.saluddigital.Mapa.MapsActivity;
import fia.ues.saluddigital.PageLoop.ViewPager;
import fia.ues.saluddigital.Usuario.RegistroUsuario;

public class Menu_principal extends AppCompatActivity {
    Button btn_registrarUsuario, btnMapas, btnRecetas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        btn_registrarUsuario = findViewById(R.id.btn_regUs);
        btn_registrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrarUs = new Intent(Menu_principal.this, RegistroUsuario.class);
                startActivity(registrarUs);
            }
        });

        btnMapas = findViewById(R.id.btn_mapas);
        btnMapas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapas = new Intent(Menu_principal.this, MapsActivity.class);
                startActivity(mapas);
            }
        });

        btnRecetas = findViewById(R.id.btn_recetas);
        btnRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recetas = new Intent(Menu_principal.this, ViewPager.class);
                startActivity(recetas);
            }
        });

    }
}