package mx.edu.uteq.dapps.holamundo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class ContenedorFragmentosActivity extends AppCompatActivity {

    private MaterialButton mbF1, mbF2, mbF3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_fragmentos);

        mbF1 = findViewById(R.id.mb_f1);
        mbF2 = findViewById(R.id.mb_f2);
        mbF3 = findViewById(R.id.mb_f3);

        mbF1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(
                        ContenedorFragmentosActivity.this,
                        R.id.host_fragment
                );
                navController.navigateUp();
                navController.navigate(R.id.PrimerFragment);
            }
        });

        mbF2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(
                        ContenedorFragmentosActivity.this,
                        R.id.host_fragment
                );
                navController.navigateUp();
                navController.navigate(R.id.SegundoFragment);
            }
        });

        mbF3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(
                        ContenedorFragmentosActivity.this,
                        R.id.host_fragment
                );
                navController.navigateUp();
                navController.navigate(R.id.TercerFragment);
            }
        });
    }
}