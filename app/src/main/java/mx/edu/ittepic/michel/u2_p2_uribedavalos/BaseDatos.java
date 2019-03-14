package mx.edu.ittepic.michel.u2_p2_uribedavalos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDatos extends SQLiteOpenHelper {
    public BaseDatos( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROPIETARIO(TELEOFNO VARCHAR(200) PRIMARY KEY NOT NULL," +
                "NOMBRE VARCHAR(200),DIRECCION VARCHAR(200),FECHA VARCHAR(200))");

        db.execSQL("CREATE TABLE SEGURO(IDSEGURO INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DESCRIPCION VARCHAR(200) NOT NULL,FECHA VARCHAR(200),TIPO VARCHAR(200)," +
                "TELEFONO VARCHAR(200), FOREIGN KEY (TELEFONO) REFERENCES PROPIETARIO(TELEOFNO))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class propietario {
    }
}
