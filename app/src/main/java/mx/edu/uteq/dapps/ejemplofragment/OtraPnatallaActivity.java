package mx.edu.uteq.dapps.ejemplofragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import mx.edu.uteq.dapps.ejemplofragment.databinding.ActivityOtraPnatallaBinding;

public class OtraPnatallaActivity extends AppCompatActivity {
    //Forma tradicional
    //private Button btnregresarHome;
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otra_pnatalla);

        btnregresarHome = findViewById(R.id.btn_regresar_home);

        btnregresarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(
                                OtraPnatallaActivity.this,
                                MainActivity.class
                        )
                );
            }
        });
    }*/

    //Llamar componentes via binding
    private ActivityOtraPnatallaBinding binding;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hacemos el link automático de los elementos
        // de la vista al controlador
        binding = ActivityOtraPnatallaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegresarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(
                                OtraPnatallaActivity.this,
                                MainActivity.class
                        )
                );
            }
        });
    }
}