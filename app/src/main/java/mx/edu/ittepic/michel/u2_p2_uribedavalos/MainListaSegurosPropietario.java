package mx.edu.ittepic.michel.u2_p2_uribedavalos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainListaSegurosPropietario extends AppCompatActivity {

    Seguro [] arregloSeguro = null;
    ListView listViewSeguros;
    int[] listaIdSeguros;
    String telefono = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lista_seguros_propietario);
        listViewSeguros = findViewById(R.id.listaseguros);

        Intent intent = getIntent();
        telefono = intent.getStringExtra("telefono");

        if(telefono == ""){
            mostrartodosseguros();
        }
        else{
            mostrarpropietarioseguros();
        }

        listViewSeguros.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final int idSeguro = listaIdSeguros[position];
                Toast.makeText(MainListaSegurosPropietario.this,String.valueOf(idSeguro),Toast.LENGTH_LONG).show();

                String[] opciones= {"Eliminar"};
                AlertDialog.Builder alerta= new AlertDialog.Builder(MainListaSegurosPropietario.this);
                alerta.setTitle("Selecciona una opcion");
                alerta.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            eliminarSeguro(idSeguro);
                        }
                    }
                });
                alerta.show();
            }
        });
    }

    private void mostrartodosseguros(){
        Seguro seguro= new Seguro(this);
        arregloSeguro  = seguro.consultarseguros();
        String[] listaSeguros= new String[arregloSeguro.length];
        listaIdSeguros= new int[arregloSeguro.length];

        for (int i=0; i<arregloSeguro.length; i++){
            listaSeguros[i]= arregloSeguro[i].getTipo()+"\n"+arregloSeguro[i].getTelefono();
            listaIdSeguros[i]= arregloSeguro[i].getId();
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaSeguros);
        listViewSeguros.setAdapter(adapter);

    }

    private void mostrarpropietarioseguros(){
        ArrayAdapter<String> a= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new String[0]);
        listViewSeguros.setAdapter(a);
        Seguro seguro= new Seguro(this);
        arregloSeguro  = seguro.consultarsegurospropietario(telefono);
        if(arregloSeguro == null){
            return;
        }
        String[] listaSeguros= new String[arregloSeguro.length];
        listaIdSeguros= new int[arregloSeguro.length];

        for (int i=0; i<arregloSeguro.length; i++){
            listaSeguros[i]= arregloSeguro[i].getTipo()+"\n"+arregloSeguro[i].getTelefono();
            listaIdSeguros[i]= arregloSeguro[i].getId();
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listaSeguros);
        listViewSeguros.setAdapter(adapter);

    }

    private void eliminarSeguro(int idseguro){
        Seguro seguro= new Seguro(this);
        Seguro arregloseguro  = seguro.consultarsegurosIndividual(idseguro);
        boolean resultado =   seguro.elimiartransaccion(arregloseguro);
        if(resultado == true){
            Toast.makeText(this,"Se elimino el seguro",Toast.LENGTH_LONG).show();
            if(telefono == ""){
                mostrartodosseguros();
            }
            else{
                mostrarpropietarioseguros();
            }
        }
        else{
            Toast.makeText(this,"Error al intentar eliminar el seguro",Toast.LENGTH_LONG).show();
        }
    }
}
