package e.jesus.telefonosmsintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jesus on 22/12/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "telefono";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String llamada = "CREATE TABLE llamada(_id INTEGER PRIMARY KEY AUTOINCREMENT, numero TEXT, fecha TEXT, hora TEXT)";
        db.execSQL(llamada);
        String mensaje = "CREATE TABLE mensaje(_id INTEGER PRIMARY KEY AUTOINCREMENT, numero TEXT, texto TEXT, fecha TEXT, hora TEXT)";
        db.execSQL(mensaje);

        ContentValues cv = new ContentValues();
        cv.put("numero", "5539868068");
        cv.put("fecha", "Dec 22 2017");
        cv.put("hora","08-23-23 PM");
        db.insert("llamada", "numero", cv);


        cv.put("numero", "5545304357");
        cv.put("fecha", "Dec 22 2017");
        cv.put("hora","08-23-23 PM");
        db.insert("llamada", "numero", cv);

        cv.put("numero", "5545304357");
        cv.put("texto","Hola que hace");
        cv.put("fecha", "Dec 21 2017");
        cv.put("hora","07-23-23 PM");
        db.insert("mensaje", "numero", cv);


        cv.put("numero", "5539868068");
        cv.put("texto","Vamonos al gym perro");
        cv.put("fecha", "Dec 21 2017");
        cv.put("hora","07-23-23 PM");
        db.insert("mensaje", "numero", cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
