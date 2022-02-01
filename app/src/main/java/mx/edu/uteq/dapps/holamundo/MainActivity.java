package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

/*
Todas las Screens o Actividades de Android
Heredan de AppCompatActivity
 */
public class MainActivity extends AppCompatActivity {

    /*
    Creamos un atributo para controlar la barra superior
    de la aplicación
     */
    private ActionBar actionBar;

    /*
    El método OnCreate es el equivalente al
    public static void main de JavaSE
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Invocamos al constructior del padre
        super.onCreate(savedInstanceState);

        //Este controlador (Activity) está invocando
        //A su layout
        //R hace referencia a la carpeta de recursos (res)
        //layout hace referencia a la carpeta 'layout'
        //activity_main es el nombre del archivo xml (layout)
        setContentView(R.layout.activity_main);

        //Inicializamos nuestra barra de aplicación
        actionBar = getSupportActionBar();
        //Indicamos que el ActionBar tendrá el botón
        //home habilitado SIEMPRE Y CUANDO actionBar sea visible
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    } //termina onCreate

    /*
    Existe un método específico para implementar el click
    de los botones dentro de actionbar, se llama
    onOptionsItemSelected
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        /*
        Evaluamos si el boton seleccionado del ActionBar es
        el boton de Home (retorno) por medio del id
        del botón (android.home)
         */
        if (item.getItemId() == android.R.id.home) {
            /*
            El metodo finish regresa a la pantalla anterior
             */
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}