package fia.ues.saluddigital.Conexiones;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import fia.ues.saluddigital.GestionPeso.RecordPeso;

public class ConexionSQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "salud_digital.db";
    // private static final String TABLE_name = "name";
    private static final String RECORD_PESO_TABLE = "record_peso";
    private SQLiteDatabase database;

    public ConexionSQLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Aquí se crea la estructura de las tablas y se ejecutan las sentencias SQL necesarias
        // sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_name);  Define tus tablas y sus columnas aquí
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + RECORD_PESO_TABLE + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fecha TEXT," +
                "peso REAL," +
                "unidad TEXT)");
        sqLiteDatabase.execSQL("INSERT INTO " + RECORD_PESO_TABLE + " (fecha, peso, unidad) " +
                "VALUES\n" +
                "('2020-03-04', 15.2, 'Libras'),\n" +
                "('2020-03-05', 12.5, 'Libras'),\n" +
                "('2020-03-06', 18.7, 'Libras'),\n" +
                "('2020-03-07', 10.9, 'Libras'),\n" +
                "('2020-03-08', 14.3, 'Libras'),\n" +
                "('2020-03-09', 16.8, 'Libras'),\n" +
                "('2020-03-10', 13.6, 'Libras'),\n" +
                "('2020-03-11', 11.2, 'Libras'),\n" +
                "('2020-03-12', 17.4, 'Libras'),\n" +
                "('2020-03-13', 19.1, 'Libras');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Aquí se actualiza la estructura de las tablas en caso de que haya cambios en la versión de la base de datos
        // sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_name); Elimina las tablas existentes si es necesario
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RECORD_PESO_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void open() {
        database = getWritableDatabase();
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    public void registrarPeso(RecordPeso recordPeso) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fecha", recordPeso.getFecha());
        values.put("peso", recordPeso.getPeso());
        values.put("unidad", recordPeso.getUnidad());
        db.insert("record_peso", null, values);
    }

    public Cursor obtenerRegistrosPesoOrdenadosPorUnidad() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columnas = {"fecha", "peso"};
        String orderBy = "unidad ASC";
        return db.query("record_peso", columnas, null, null, null, null, orderBy);
    }
}
