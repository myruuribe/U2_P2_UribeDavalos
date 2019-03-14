package mx.edu.ittepic.michel.u2_p2_uribedavalos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainAgregarSeguro extends AppCompatActivity {
    EditText des, fech;
    Spinner tip;
    TextView resultado;
    Button agregar, regret;
    String telefono;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_agregar_seguro);
        //OBTENER EL INTENT
        Intent intent = getIntent();
        telefono = intent.getStringExtra("telefono");

        des= findViewById(R.id.descripcion);
        fech=findViewById(R.id.fecha);
        tip= findViewById(R.id.tipo);
        agregar= findViewById(R.id.btnseguro);
        resultado= findViewById(R.id.etiqueta);
        resultado.setText("Se agregara un seguro al numero "+telefono);
        regret= findViewById(R.id.regresar);
        
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar2();
            }
        });

        regret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pantallalista= new Intent(MainAgregarSeguro.this,MainActivity.class);
                MainAgregarSeguro.this.startActivity(pantallalista);
            }
        });
    }

    private void insertar2() {
        String mensaje= "";
        Seguro seguro= new Seguro(this);
        boolean respuesta = seguro.insertar2(new Seguro(0,des.getText().toString(),fech.getText().toString(),
                tip.getSelectedItem().toString(),telefono));

        if (respuesta){
            mensaje= "Se inserto un seguro al propietario";
        }else {
            mensaje= "Error, no se inserto el seguro";
        }
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG).show();
    }
}
