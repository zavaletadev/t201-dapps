package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TableLayoutActivity extends AppCompatActivity {

    /*
    botonBack/Home
     */
    private ActionBar actionBar;

    /*
    Componentes de la calculadora
     */
    private TextView tvDisplay;
    private Button btnAc, btnMAs, btnMasmenos;

    /*
    Creamos dos variables que guarden
    los números ingresados en la calculadora
     */
    private double num1, num2;

    private boolean enOperacion = false;

    private int contadorPuntos;

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
        btnAc = findViewById(R.id.btn_ac);
        btnMAs = findViewById(R.id.btn_mas);
        btnMasmenos = findViewById(R.id.btn_masmenos);

        //Click mas menos
        btnMasmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tomamos el texto del display
                String textoDisplay = tvDisplay.getText().toString();

                //Convertimos a número
                double numeroDispolay = Double.parseDouble(textoDisplay);

                //Evaluamos si tenemos numeros guardados
                if (num1 == 0) {
                    num1 = numeroDispolay;
                } else {
                    num2 = numeroDispolay;
                }

                //Lo multiplicamos por -1
                numeroDispolay = numeroDispolay * -1;

                //Mostramos el texto
                tvDisplay.setText(String.valueOf(numeroDispolay));
            }
        });

        //Click Boton AC
        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvDisplay.setText("0");
                num1 = 0;
                num2 = 0;
            }
        });

        //Click boton mas
        btnMAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Tomamos el texto del display
                String textDisplay = tvDisplay.getText().toString();

                //Convertimos el valor del displat a un número
                double numeroDisplay = Double.parseDouble(textDisplay);

                //Evaluamos si nuestras variables que guardan números
                //tienen  valor
                if (num1 == 0) {
                    num1 = numeroDisplay;
                } else {
                    num2 = numeroDisplay;
                }

                //Realizamos la suma de los números
                double resultado = num1 + num2;

                //Mostramos la suma
                tvDisplay.setText(String.valueOf(resultado));

                enOperacion = true;

            }
        });


    } // termina oncreate

    /*
    Podemos agregar metodos genericos para ser utilizados por
    varios componentes, en este caso haremos un click genérico
    para todos los número

    Los métodos click genéricos deben ser void y contener un
    parámetro de tipo View

    Para indicar en la vista que se va a invocar a este
    metodo, utilizamos el atributo onclick
     */
    public void clickNumero(View componente) {

        /*
        Los click genérico te permiten conocer que componente
        fue el que disparó el evento, dicha información viene
        del parámetro "componente"

        A partir del parametro de tipo View "componente" vamos a
        crear un boton (el boton seleccionado)
         */
        Button botonSeleccionado = (Button) componente;

         /*
        Creamos un mensaje Toast
        Toast necesita 3 parámetros:
        1.- Contexto
        2.- Texto
        3.- Tiempo de visualización
            LENGTH_LONG <--- 3 segundos
            LENGTH_SHORT <-- 1.5 segundos
         */
//        Toast.makeText(
//                TableLayoutActivity.this,
//                botonSeleccionado.getText().toString(),
//                Toast.LENGTH_SHORT
//        ).show();

        //Tomamos el texto actual del display
        String textoDisplay = tvDisplay.getText().toString();

        //Concatenamos el valor actual del displat MAS el botón
        //presionado

        textoDisplay += botonSeleccionado.getText().toString();

        //Mostramos el nuvo texto del display
        tvDisplay.setText(textoDisplay);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}