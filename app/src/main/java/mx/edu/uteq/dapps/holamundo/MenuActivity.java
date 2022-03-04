package mx.edu.uteq.dapps.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    /*
    Por cada componente visual que necesitemos controlar, es necesario
    un atributo privado DEL MISMO TIPO DE DATO
     */
    private Button btnEj1;
    private Button btnLinearlayout;
    private Button btnScrollLayout;
    private Button btnTablelayout;
    private Button btnControles;
    private Button btnEstilostexto;
    private Button btnEstilosBoton;
    private Button btnControlesSel;
    private Button btnFragmentos;
    private Button btnValidaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*
        Una vez creado el atributo, lo inicializamos indicando el
        ID del componente al que pertenece utilizando la nomenclatura:
        findViewById(R.id._NOMBRE_);
         */
        btnEj1 = findViewById(R.id.btn_ej1);
        btnLinearlayout = findViewById(R.id.btn_linearlayout);
        btnScrollLayout = findViewById(R.id.btn_scroll_layout);
        btnTablelayout  = findViewById(R.id.btn_tablelayout);
        btnControles    = findViewById(R.id.btn_controles);
        btnEstilostexto = findViewById(R.id.btn_estilostexto);
        btnEstilosBoton = findViewById(R.id.btn_estilosboton);
        btnControlesSel = findViewById(R.id.btn_controles_sel);
        btnFragmentos = findViewById(R.id.btn_fragmentos);
        btnValidaciones = findViewById(R.id.btn_validaciones);
        /*
        Programamos el evento click del boton con id "btn_ej1"
        El evento click utiliza el método seOnClickListener y pasamos
        como parámetro una implementación de la interface View.Click
         */
        btnEj1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cambiamos el texto
                //btnEj1.setText("Me apachurraron");

                /*
                Para navegar entre Screens utilizamos el método startActiviy,
                pasando como parámetro un Intent

                Los Intent son referencias que indican donde estamos y qué
                queremos hacer
                 */

                //Creamos un Intent de esta actividad a MainActivity
                /*
                Intent usa dos parámetros:
                1. ¿Dónde estás?  ---> NOMBRE_CLASE.this
                2. ¿A dónde quieres ir? --> NOMBRE_CLASE.class
                 */
                Intent alPrimerejercicio = new Intent(
                        MenuActivity.this,
                        MainActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent linearIntent = new Intent(
                        MenuActivity.this,
                        LinearLayoutActivity.class
                );
                startActivity(linearIntent);
            }
        });

        btnScrollLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(
                                MenuActivity.this,
                                ScrollActivity.class
                        )
                );
            }
        });

        /*
        clic del boton tablelayout
         */
        btnTablelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(
                                MenuActivity.this,
                                TableLayoutActivity.class
                        )
                );
            }
        });

        /*
        Click btn controles
         */
        btnControles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(MenuActivity.this,
                                ControlesEntradaActivity.class
                        )
                );
            }
        });

        /*
        Click estilos texto
         */
        btnEstilostexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(
                                MenuActivity.this,
                                EstilosTextoActivity.class
                        )
                );
            }
        });

        /*
        click estilos boton
         */
        btnEstilosBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(
                                MenuActivity.this,
                                EstiloBotonesActivity.class
                        )
                );
            }
        });

        btnControlesSel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(
                                MenuActivity.this,
                                ControlesSeleccionActivity.class
                        )
                );
            }
        });

        btnFragmentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(
                                MenuActivity.this,
                                ContenedorFragmentosActivity.class
                        )
                );
            }
        });

        //Funcione Lambda
        btnValidaciones.setOnClickListener(view -> {
            startActivity(
                    new Intent(
                            MenuActivity.this,
                            ValidacionesActivity.class
                    )
            );
        });

    } // termina oncreate
} // termina la clase