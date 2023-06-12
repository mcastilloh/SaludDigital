package fia.ues.saluddigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fia.ues.saluddigital.BD_Control.BD_Control;
import fia.ues.saluddigital.Usuario.RegistroUsuario;
import fia.ues.saluddigital.Usuario.Usuario;

public class MainActivity extends AppCompatActivity {

    Button btn_reg_us;
    Button btn_iniciar_sesion;
    EditText txt_nombreUsuario, txtPass;
    BD_Control bdControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bdControl = new BD_Control(this);
        txt_nombreUsuario = findViewById(R.id.txt_usuario);
        txtPass = findViewById(R.id.txt_contra);
        btn_iniciar_sesion = findViewById(R.id.btn_login);
        bdControl.abrir();
        llenarDatos();
        bdControl.cerrar();

        btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bdControl.abrir();
                String u = txt_nombreUsuario.getText().toString();
                String p = txtPass.getText().toString();
                if (u.equals("") && p.equals("")) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    bdControl.cerrar();
                } else if (bdControl.login(u, p) == 1) {
                    bdControl.cerrar();
                    Toast.makeText(MainActivity.this, "Datos Correctos", Toast.LENGTH_SHORT).show();
                    Intent admin = new Intent(MainActivity.this, Menu_principal.class);
                    startActivity(admin);
                }
                bdControl.cerrar();
            }
        });
    }

    public void llenarDatos(){
        Usuario usuario = new Usuario(0,"a","a");
        bdControl.insertar(usuario);
    }
}