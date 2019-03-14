package mx.edu.ittepic.michel.u2_p2_uribedavalos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    EditText phone, name, date, address;
    Button insert, btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        phone= findViewById(R.id.telefono);
        name= findViewById(R.id.nombre);
        date= findViewById(R.id.fecha);
        address= findViewById(R.id.direccion);
        insert= findViewById(R.id.ingresar);
        btn= findViewById(R.id.btnagregarseguro);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void insertar() {
        String mensaje0 = "";
        Propietario propietario= new Propietario(this);
        boolean respuesta= propietario.insertar(new Propietario(phone.getText().toString(),
                name.getText().toString(),address.getText().toString(),date.getText().toString()));

        if (respuesta){
            mensaje0= "Se inserto el propietario";
        }else {
            mensaje0= "Error, no se inserto el propietario";
        }
        Intent pantallaSeguro = new Intent(Main2Activity.this,MainAgregarSeguro.class);
        //PASAR UN VALOR A LA ACTIVITY DE AGREGAR SEGUROS
        pantallaSeguro.putExtra("telefono",phone.getText().toString());
        Main2Activity.this.startActivity(pantallaSeguro);
        Toast.makeText(this,mensaje0,Toast.LENGTH_LONG).show();
    }
}
