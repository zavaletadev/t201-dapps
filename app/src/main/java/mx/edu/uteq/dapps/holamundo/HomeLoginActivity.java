package mx.edu.uteq.dapps.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mx.edu.uteq.dapps.holamundo.databinding.ActivityHomeLoginBinding;

public class HomeLoginActivity extends AppCompatActivity {

    private ActivityHomeLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Bundle parametros = getIntent().getExtras();

        final String idUsuario = parametros.getString("id");
        final String nombre = parametros.getString("nombre");
        final String apellidos = parametros.getString("apellidos");

        binding.tvDatosServ.setText(
                idUsuario + "\n" +
                nombre + "\n" +
                apellidos
        );
    }
}