package fia.ues.saluddigital.GestionPeso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fia.ues.saluddigital.Conexiones.ConexionSQLite;
import fia.ues.saluddigital.Conexiones.WebServiceManager;
import fia.ues.saluddigital.R;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import android.app.DatePickerDialog;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;



public class RegistrarPeso extends AppCompatActivity {
    private WebServiceManager webServiceManager;
    private Spinner spinnerUMedida;
    private EditText editFecha;
    private Button btnRegistar;
    private EditText editPeso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_peso);

        webServiceManager = WebServiceManager.getInstance(this);
        btnRegistar = findViewById(R.id.btnRegistrar);

        editPeso = findViewById(R.id.editPeso);

        spinnerUMedida = findViewById(R.id.editUMedida);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.unidades_medida_peso, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUMedida.setAdapter(adapter);

        editFecha = findViewById(R.id.editFecha);

        editFecha.setOnClickListener(v -> {
            // Obtener fecha actual
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            // Crear el DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, yearSelected, monthOfYear, dayOfMonth) -> {
                // Obtener la fecha seleccionada y hacer algo con ella
                String selectedDate = yearSelected + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                editFecha.setText(selectedDate);
            }, year, month, day);

            // Mostrar el DatePickerDialog
            datePickerDialog.show();
        });

        // Lógica para guardar el registro de peso
        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener los valores ingresados
                String fecha = editFecha.getText().toString();
                double peso = Double.parseDouble(editPeso.getText().toString());
                String unidad = spinnerUMedida.getSelectedItem().toString();

                // Crear el objeto RecordPeso
                RecordPeso recordPeso = new RecordPeso();
                recordPeso.setFecha(fecha);
                recordPeso.setPeso(peso);
                recordPeso.setUnidad(unidad);


                // Llamar al método para registrar el peso
                ConexionSQLite conexionSQLite = new ConexionSQLite(RegistrarPeso.this);
                conexionSQLite.open();
                conexionSQLite.registrarPeso(recordPeso);
                conexionSQLite.close();

                // Mostrar un mensaje de éxito
                Toast.makeText(RegistrarPeso.this, "Registro de peso exitoso", Toast.LENGTH_SHORT).show();
                clearText();

            }
        });

    }

    private void clearText(){
        editFecha.setText("");
        editPeso.setText("");
    }

}
