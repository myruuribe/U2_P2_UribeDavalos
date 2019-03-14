package mx.edu.ittepic.michel.u2_p2_uribedavalos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActualizar extends AppCompatActivity {
    EditText txttelefono, txtnombre, txtdireccion, txtfecha;
    Button actualizar;
    String nombre,telefono,direccion,fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actualizar);

        Intent intent = getIntent();
        nombre = intent.getStringExtra("nombre");
        telefono = intent.getStringExtra("telefono");
        direccion = intent.getStringExtra("direccion");
        fecha = intent.getStringExtra("fecha");


        txttelefono= findViewById(R.id.actutelefono);
        txtnombre= findViewById(R.id.actunombre);
        txtdireccion= findViewById(R.id.actudireccion);
        txtfecha= findViewById(R.id.actufecha);
        actualizar= findViewById(R.id.btnactualizar);

        txttelefono.setText(telefono);
        txtnombre.setText(nombre);
        txtdireccion.setText(direccion);
        txtfecha.setText(fecha);

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modificarPropietario();
            }
        });
    }

    private void modificarPropietario(){
        Propietario propietario = new Propietario(this);
        boolean resultado = propietario.actualizaraccion(new Propietario(txttelefono.getText().toString(),txtnombre.getText().toString(),txtdireccion.getText().toString(),txtfecha.getText().toString()));
        if(resultado == true){
            Intent pantallalista= new Intent(MainActualizar.this,MainActivity.class);
            MainActualizar.this.startActivity(pantallalista);
            Toast.makeText(this,"Propietario modificado correctamente",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Error al modifcar al propietario",Toast.LENGTH_LONG).show();
        }
    }
}
