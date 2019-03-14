package mx.edu.ittepic.michel.u2_p2_uribedavalos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Seguro {
    private BaseDatos base;
    private int id;
    private String descripcion;
    private String fecha;
    private String tipo;
    private String telefono;
    protected String error;

    public Seguro(Activity activity){
        base= new BaseDatos(activity,"aseguradora",null,1);
    }

    public Seguro(int id,String de,String fe,String ti,String te){
        this.id= id;
        this.descripcion = de;
        this.fecha= fe;
        this.tipo= ti;
        this.telefono= te;
    }

    //metodo insertar
    public boolean insertar2(Seguro seguro){
        try {
            SQLiteDatabase transaccioninsertar= base.getWritableDatabase();
            ContentValues datso2= new ContentValues();
            datso2.put("DESCRIPCION",seguro.getDescripcion());
            datso2.put("FECHA",seguro.getFecha());
            datso2.put("TIPO",seguro.getTipo());
            datso2.put("TELEFONO",seguro.getTelefono());

            long resultado= transaccioninsertar.insert("SEGURO","IDSEGURO=?",datso2);
            transaccioninsertar.close();
            if (resultado== -1) return false;
        }catch (SQLiteException e){
           error= e.getMessage();
           return false;
        }
        return true;
    }

    //Consultar seguros
    public Seguro[] consultarseguros(){
        Seguro[] seguros= null;
        try {
            SQLiteDatabase verconsulta2= base.getReadableDatabase();
            String SQL= "SELECT * FROM SEGURO";
            Cursor c = verconsulta2.rawQuery(SQL,null);

            if (c.moveToFirst()){
                seguros= new Seguro[c.getCount()];
                int pos=0;
                do {
                    seguros[pos]= new Seguro(c.getInt(0),c.getString(1),
                            c.getString(2),c.getString(3),c.getString(4));
                    pos++;
                }while (c.moveToNext());
            }
            verconsulta2.close();

        }catch (SQLiteException e){

        }
        return seguros;
    }

    //Consultar seguros individualmente
    public Seguro consultarsegurosIndividual(int id){
        Seguro seguros= null;
        try {
            SQLiteDatabase verconsulta2= base.getReadableDatabase();
            String SQL= "SELECT * FROM SEGURO WHERE IDSEGURO="+id;
            Cursor c = verconsulta2.rawQuery(SQL,null);

            if (c.moveToFirst()){
                int pos=0;
                do {
                    seguros= new Seguro(c.getInt(0),c.getString(1),
                            c.getString(2),c.getString(3),c.getString(4));
                    pos++;
                }while (c.moveToNext());
            }
            verconsulta2.close();

        }catch (SQLiteException e){

        }
        return seguros;
    }

    //Consultar seguros por propietario
    public Seguro[] consultarsegurospropietario(String telefono){
        Seguro[] seguros= null;
        try {
            SQLiteDatabase verconsulta2= base.getReadableDatabase();
            String SQL= "SELECT * FROM SEGURO WHERE TELEFONO = '"+telefono+"' ";
            Cursor c= verconsulta2.rawQuery(SQL,null);

            if (c.moveToFirst()){
                seguros= new Seguro[c.getCount()];
                int pos=0;
                do {
                    seguros[pos]= new Seguro(c.getInt(0),c.getString(1),
                            c.getString(2),c.getString(3),c.getString(4));
                    pos++;
                }while (c.moveToNext());
            }
            verconsulta2.close();
        }catch (SQLiteException e){

        }
        return seguros;
    }

    //metodo eliminar
    public  boolean elimiartransaccion(Seguro seguro){
        int resultado2;
        try {
            SQLiteDatabase elimina2= base.getWritableDatabase();
            String s[]= {""+seguro.getId()};
            resultado2= elimina2.delete("SEGURO","IDSEGURO=?",s);
            elimina2.close();
        }catch (SQLiteException e){
            error= e.getMessage();
            return false;
        }
        return resultado2>0;
    }



    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
