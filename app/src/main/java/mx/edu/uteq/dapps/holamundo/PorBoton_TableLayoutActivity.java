package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PorBoton_TableLayoutActivity extends AppCompatActivity {

    /*
    botonBack/Home
     */
    private ActionBar actionBar;

    /*
    Componentes de la calculadora
     */
    private TextView tvDisplay;
    private Button btnN7, btnN8, btnN9, btnAc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /*
        Ligamos los componentes de la  vista con este axctivity
        por medio del id y el método findViewById
         */
        tvDisplay = findViewById(R.id.tv_display);
        btnN7 = findViewById(R.id.btn_n7);
        btnN8 = findViewById(R.id.btn_n8);
        btnN9 = findViewById(R.id.btn_n9 );
        btnAc = findViewById(R.id.btn_ac);

        //Click Boton AC
        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDisplay.setText("");
            }
        });

        //Clikc boton 7
        btnN7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tomar el texto actual del display
                String textoDisplay = tvDisplay.getText().toString();

                //Concatenamos el valor actual del display mas el número
                //de este botón
                //Estas 3 formas son representan lo mismo que arriba
                //textoDisplay = textoDisplay + "7";
                //textoDisplay +=  7;
                //textoDisplay +=  "7";
                textoDisplay = textoDisplay + 7;

                //Mostramos el nuevo texto del display
                tvDisplay.setText(textoDisplay);
            }
        });

        //Clikc boton 8
        btnN8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoDisplay = tvDisplay.getText().toString();
                textoDisplay = textoDisplay + 8;
                tvDisplay.setText(textoDisplay);
            }
        });

    } // termina oncreate


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
