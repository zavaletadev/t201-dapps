package mx.edu.uteq.dapps.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import mx.edu.uteq.dapps.holamundo.databinding.ActivityRecibeDatosBinding;

public class RecibeDatosActivity extends AppCompatActivity {

    /*
    Implementamos viewBinding en este Screen
     */
    private ActivityRecibeDatosBinding binding;
    private Intent datosScreenAnterior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecibeDatosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
        Tomamos las variables tipo extra que nos mandó el screen anterior
         */
        datosScreenAnterior = getIntent();

        /*
        Accedemos a cada valor a partir del tipo de dato
        Si esperamos recibir un entero: getIntExtra("nombre", valor_defecto);
        Si esperamos recibir un String: getStringExtra("nombre");
         */
        final String nombre = datosScreenAnterior.getStringExtra("nombre");
        final String correo = datosScreenAnterior.getStringExtra("email");
        final String telefono = datosScreenAnterior.getStringExtra("telefono");
        final int edad = datosScreenAnterior.getIntExtra("edad", 999);

        /*
        Mostramos los datos nuestros TextView
         */
        binding.tvNombre.setText(nombre);
        binding.tvEmail.setText(correo);
        binding.tvTelefono.setText(telefono);
        //Forma 1 de convertir a String
        binding.tvEdad.setText(edad + " años");
        //Forma 2 de convertir a String
        //binding.tvEdad.setText(String.valueOf(edad));



    }
}