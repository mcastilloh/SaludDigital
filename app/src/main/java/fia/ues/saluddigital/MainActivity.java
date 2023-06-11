package fia.ues.saluddigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import fia.ues.saluddigital.GestionPeso.GestionPesoMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Intent intent = new Intent(MainActivity.this, GestionPesoMenu.class);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}