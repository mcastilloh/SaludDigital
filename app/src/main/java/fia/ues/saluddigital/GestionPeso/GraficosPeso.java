package fia.ues.saluddigital.GestionPeso;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;


import fia.ues.saluddigital.Conexiones.ConexionSQLite;
import fia.ues.saluddigital.R;

public class GraficosPeso extends AppCompatActivity {
    private LinearLayout chartLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos_peso);


        // Lógica para mostrar los registros de peso ordenados por unidad en un histograma
        mostrarRegistrosPesoOrdenadosPorUnidad();


    }

    private void mostrarRegistrosPesoOrdenadosPorUnidad() {
        ConexionSQLite conexionSQLite = new ConexionSQLite(this);
        conexionSQLite.open();

        Cursor cursor = conexionSQLite.obtenerRegistrosPesoOrdenadosPorUnidad();
        if (cursor != null && cursor.moveToFirst()) {
            Map<String, List<Float>> data = new HashMap<>();
            List<String> fechas = new ArrayList<>();

            do {
                @SuppressLint("Range") String fecha = cursor.getString(cursor.getColumnIndex("fecha"));
                @SuppressLint("Range") float peso = cursor.getFloat(cursor.getColumnIndex("peso"));

                // Aquí puedes modificar el código según tus necesidades
                String unidad = "Libras"; // Reemplaza "Unidad de Peso" con la lógica adecuada para obtener la unidad correspondiente

                if (!data.containsKey(unidad)) {
                    data.put(unidad, new ArrayList<>());
                }
                data.get(unidad).add(peso);

                fechas.add(fecha);
            } while (cursor.moveToNext());

            crearTabla(data, fechas);
        }

        cursor.close();
        conexionSQLite.close();
    }

    private void crearTabla(Map<String, List<Float>> data, List<String> fechas) {
        LineChart lineChart = findViewById(R.id.chart);

        List<ILineDataSet> dataSets = new ArrayList<>();

        for (Map.Entry<String, List<Float>> entry : data.entrySet()) {
            String unidad = entry.getKey();
            List<Float> pesos = entry.getValue();

            List<Entry> entries = new ArrayList<>();

            for (int i = 0; i < pesos.size(); i++) {
                String fecha = fechas.get(i);
                float peso = pesos.get(i);

                entries.add(new Entry(i, peso));
            }

            LineDataSet dataSet = new LineDataSet(entries, unidad);
            dataSet.setColor(Color.BLUE);
            dataSet.setCircleColor(Color.BLUE);
            dataSet.setLineWidth(2f);
            dataSet.setCircleRadius(4f);
            dataSet.setDrawValues(true);

            dataSets.add(dataSet);
        }

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }



}
