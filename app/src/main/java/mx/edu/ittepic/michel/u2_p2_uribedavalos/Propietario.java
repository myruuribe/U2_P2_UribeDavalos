package mx.edu.ittepic.michel.u2_p2_uribedavalos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Propietario {
    private BaseDatos base;
    private String telefono;
    private String nombre;
    private String direccion;
    private String fecha;
    protected String error;

    public Propietario(Activity activity){
        base= new BaseDatos(activity,"aseguradora",null,1);
    }

    public Propietario(String t,String n,String d, String f){
        telefono= t;
        nombre= n;
        direccion= d;
        fecha= f;
    }

    //metodo insertar
    public boolean insertar(Propietario propietario){
        try {
            SQLiteDatabase insertaraccion= base.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("TELEOFNO",propietario.getTelefono());
            datos.put("NOMBRE", propietario.getNombre());
            datos.put("DIRECCION", propietario.getDireccion());
            datos.put("FECHA", propietario.getFecha());

            long resultado= insertaraccion.insert("PROPIETARIO",null,datos);
            insertaraccion.close();
            if (resultado == -1) return false;
        }catch (SQLiteException e){
            error= e.getMessage();
            return false;
        }
        return true;
    }

    //metodo consultar
    public Propietario[] consultaraccion(){
        Propietario[] propietarios= null;
        try {
           SQLiteDatabase verconsulta= base.getReadableDatabase();
           String SQL= "SELECT * FROM PROPIETARIO";
            Cursor c= verconsulta.rawQuery(SQL,null);

            if (c.moveToFirst()){
                propietarios= new Propietario[c.getCount()];
                int pos=0;
                do {
                    propietarios[pos]= new Propietario(c.getString(0),c.getString(1),
                            c.getString(2),c.getString(3));
                    pos++;
                }while (c.moveToNext());
            }
            verconsulta.close();
        }catch (SQLiteException e){
            error= e.getMessage();
            return null;
        }
        return propietarios;
    }

    //metodo consultar
    public Propietario consultarIndividualAccion(String telefono){
        Propietario propietarios= null;
        try {
            SQLiteDatabase verconsulta= base.getReadableDatabase();
            String SQL= "SELECT * FROM PROPIETARIO WHERE TELEOFNO = '"+telefono+"' ";
            Cursor c= verconsulta.rawQuery(SQL,null);

            if (c.moveToFirst()){
                int pos=0;
                do {
                    propietarios = new Propietario(c.getString(0),c.getString(1),
                            c.getString(2),c.getString(3));
                    pos++;
                }while (c.moveToNext());
            }
            verconsulta.close();
        }catch (SQLiteException e){
            error= e.getMessage();
            return null;
        }
        return propietarios;
    }

    //metodo actualizar
    public boolean actualizaraccion(Propietario propietario){
        try {
            SQLiteDatabase actualiza= base.getWritableDatabase();
            ContentValues datos= new ContentValues();
            datos.put("TELEOFNO",propietario.getTelefono());
            datos.put("NOMBRE",propietario.getNombre());
            datos.put("DIRECCION",propietario.getDireccion());
            datos.put("FECHA",propietario.getFecha());
            String p[]={propietario.getTelefono()};
            long resultado = actualiza.update("PROPIETARIO",datos,"TELEOFNO=?",p);
            actualiza.close();
            if (resultado==-1) return false;
        }catch (SQLiteException e){
            error= e.getMessage();
            return false;
        }
        return true;
    }

    //metodo eliminar
    public boolean eliminaraccion(Propietario propietario){

        int resultado;
            try {
                SQLiteDatabase elimina= base.getWritableDatabase();
                String p[]= {propietario.getTelefono()};
                resultado= elimina.delete("PROPIETARIO","TELEOFNO=?",p);
                elimina.close();
            }catch (SQLiteException e){
                error= e.getMessage();
                return false;
            }
        return resultado>0;
    }


    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
