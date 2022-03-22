package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.databinding.ActivityPublicBinding;

public class PublicActivity extends AppCompatActivity {

    private ActivityPublicBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPublicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /*
    Ligamos el menú principal a este activity
    Necesitamos un menu en res/menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_public, menu);
        return super.onCreateOptionsMenu(menu);
    }
}