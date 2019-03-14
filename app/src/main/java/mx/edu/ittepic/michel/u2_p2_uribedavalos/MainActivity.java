package mx.edu.ittepic.michel.u2_p2_uribedavalos;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    String[] listapropietarios;
    Propietario [] arregloPropietario = null;
    String[] listatelefonos;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista= findViewById(R.id.listapropi);
        btn=findViewById(R.id.btnnuevopropietario);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevopropietario= new Intent(MainActivity.this,Main2Activity.class);
                MainActivity.this.startActivity(nuevopropietario);
            }
        });

        mostrarpropietarios();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final String telefono= listatelefonos[position];
                Toast.makeText(MainActivity.this,telefono,Toast.LENGTH_LONG).show();

                String[] opciones= {"Ver Seguros","Eliminar","Modificar","Agregar Seguro"};
                AlertDialog.Builder alerta= new AlertDialog.Builder(MainActivity.this);
                alerta.setTitle("Selecciona una opcion");
                alerta.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            Intent pantallaSeguros = new Intent(MainActivity.this, MainListaSegurosPropietario.class);
                            pantallaSeguros.putExtra("telefono",telefono);
                            MainActivity.this.startActivity(pantallaSeguros);
                        }else if (which==1){
                            eliminarPropietario(telefono);
                        }else if (which==2){
                            seleccionarPropietarioActualizar(position);

                        }else {
                            Intent intentn = new Intent(MainActivity.this,MainAgregarSeguro.class);
                            intentn.putExtra("telefono",telefono);
                            MainActivity.this.startActivity(intentn);
                        }
                    }
                });
                alerta.show();
            }
        });

    }

    private void mostrarpropietarios(){
        ArrayAdapter<String> a= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,new String[0]);
        lista.setAdapter(a);
        Propietario propietario= new Propietario(this);
        arregloPropietario  = propietario.consultaraccion();
        if(arregloPropietario == null){
            return;
        }
        String[] listapropietario= new String[arregloPropietario.length];
        listatelefonos= new String[arregloPropietario.length];

        for (int i=0; i<arregloPropietario.length; i++){
            listapropietario[i]= arregloPropietario[i].getNombre()+"\n"+arregloPropietario[i].getTelefono();
            listatelefonos[i]= arregloPropietario[i].getTelefono();
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listapropietario);
        lista.setAdapter(adapter);

    }

    private void eliminarPropietario(String telefono){
        Propietario propietario= new Propietario(this);
        Propietario arregloPropietario  = propietario.consultarIndividualAccion(telefono);
        boolean resultado =   propietario.eliminaraccion(arregloPropietario);
        if(resultado == true){
            Toast.makeText(this,"Se elimino al propietario",Toast.LENGTH_LONG).show();
            mostrarpropietarios();
        }
        else{
            Toast.makeText(this,"Error al intentar eliminar al propietario",Toast.LENGTH_LONG).show();
        }
    }

    private void seleccionarPropietarioActualizar(int posicion){
        String nombre = arregloPropietario[posicion].getNombre();
        String telefono = arregloPropietario[posicion].getTelefono();
        String direccion = arregloPropietario[posicion].getDireccion();
        String fecha = arregloPropietario[posicion].getFecha();

        Intent pantallaActualizar = new Intent(MainActivity.this,MainActualizar.class);
        pantallaActualizar.putExtra("nombre",nombre);
        pantallaActualizar.putExtra("telefono",telefono);
        pantallaActualizar.putExtra("direccion",direccion);
        pantallaActualizar.putExtra("fecha",fecha);
        MainActivity.this.startActivity(pantallaActualizar);
    }
}
