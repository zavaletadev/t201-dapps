package mx.edu.uteq.dapps.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/*
Todas las Screens o Actividades de Android
Heredan de AppCompatActivity
 */
public class MainActivity extends AppCompatActivity {

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
    }
}