package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.Toast;

import mx.edu.uteq.dapps.holamundo.databinding.ActivityValidacionesBinding;

public class ValidacionesActivity extends AppCompatActivity {

    private ActionBar actionBar;

    /*
    Podemos realizar una vinculación automática de los elementos de la vista
    con los componentes del controlador
    utilizando la librería ViewBinding

    Para ello debemos activarla en la configuración del proyecto

    ViewBinding se activa desde el archivo app.gradle

    Binding genera una clase intermediaria entre el Controlador y la vista
    con el nombre:
    Activity/Fragment{NOMBRE_CLASE}Binding
    Ejem:
    FragmentInicioBinding
    ActivityValidacionesBinding
     */
    private ActivityValidacionesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        Indicamos que vamos a mostrar la vista que binding
        interprete a partir del contexto
         */
        binding = ActivityValidacionesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /*
        Usando viewBinding YA NO ES NECESARIO LIGAR LOS COMPONENTES
        mediante findViewById

        Binding permite invocar el identificador del elemento de la vista
         desde el controlador utilizando la definicón del ID convirtiendolo
         a notación camello
         VISTA              CONTROLADOR
         btn_registro       binding.btnRegistro
         til_nombre         binding.tilNombre
         tiet_email         binding.tietEmail
         */

        /*
        Validamos lo que se va escribiendo en el campo de nombre
         */
        binding.tietNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                /* Este método se ejecuta cada que se escribr algo */
                validarNombre(editable.toString());
            }
        });

        /*
        Click del botón Registrarse
         */
        binding.mbValidar.setOnClickListener(view -> {
            /*
            Validamos a nombre
             */
            validarNombre(binding.tietNombre.getText().toString());
        });

    }

    public boolean validarNombre(String texto) {
        /*
        Si el texto es menor a 5 caracteres, el nombre es inválido
         */
        if (texto.trim().length() < 8) {
            // Mostramos el error en el TextInputLayout
            binding.tilNombre.setError("Nombre inválido, agrega un nombre de 8 letras como mínimo");
            //Quitamos el icono de error
            binding.tilNombre.setErrorIconDrawable(null);
            return false;
        }

        else {
            binding.tilNombre.setErrorEnabled(false);
            return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}