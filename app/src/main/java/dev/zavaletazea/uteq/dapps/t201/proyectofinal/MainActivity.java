package dev.zavaletazea.uteq.dapps.t201.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.PublicActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Evitamos regresar a esta pantalla
        finish();
        startActivity(
                new Intent(
                        MainActivity.this,
                        PublicActivity.class
                )
        );
    }
}