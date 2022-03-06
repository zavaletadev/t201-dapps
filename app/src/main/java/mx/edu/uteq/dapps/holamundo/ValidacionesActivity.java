package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

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
        Validamos el correo electrónico mientras se escribe
         */
        binding.tietEmail.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                /*
                Invocamos al método de validar correo cuando esté escribiendo
                la variable (parámetro) 'editable' contiene el texto conforme se
                va escribiendo
                */
                validarCorreo(editable.toString());
            }
        });

        /*
        Validamos el teléfono mientras se escribe
         */
        binding.tietTelefono.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void afterTextChanged(Editable editable) {
                validarTelefono(editable.toString());
            }
        });

        /*
        Mostramos la edad mientras se mueve el Slider
         */
        binding.msEdad.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                /*
                El parámetro value contiene el valor del Tick del slider al momento
                de arrastrarlo

                Lo mostramos el textview
                 */
                binding.tvEdad.setText("Edad: " + ((int) value) + " años");
                //Validamos que sea mayor de edad
                validaEdad((int) value);

            }
        });

        /*
        Click del botón Registrarse
         */
        binding.mbValidar.setOnClickListener(view -> {
            /*
            Validamos QUE EL NOMBRE SEA INCORRECTO
             */
            if (!validarNombre(binding.tietNombre.getText().toString())) {
                //Programamos cuando el NO nombre sea valido
                //Terminar con el click para que ya no se ejecute NADA
                return;
            }

            /*
            Validamos QUE EL CORREO SEA INCORRECTO
             */
            if (!validarCorreo(binding.tietEmail.getText().toString())) {
                //Terminaos el evento click
                return;
            }

            /*
            Validamos QUE EL TELÉFONO SEA INCORRECTO
             */
            if (!validarTelefono(binding.tietTelefono.getText().toString())) {
                //Terminamos el click
                return;
            }

            /*
            Validamos QUE LA EDAD SEA INCORRECTA (MENOR A 18)
             */
            if (!validaEdad( (int) binding.msEdad.getValue())) {
                return;
            }

            /*
            ESTA SECCIÓN DLE CÓDIGO SOLO SE VA EJECUTAR SI NO CAEMOS EN NINGUNO
            DE LOS IF ANTERIORES
             */

            /*
            Alerta para confirmar los datos, editarlos o regresar a
            la pantalla anterior
             */
            AlertDialog.Builder alerta = new AlertDialog.Builder(ValidacionesActivity.this);
            alerta.setTitle("¿Son todos los datos correctos?");
            alerta.setIcon(R.drawable.ic_quiz);
            alerta.setMessage("Crearemos una cuenta con tu información ingresada");
            alerta.setCancelable(false);

            //icono al boton neutral === editar formulario
            alerta.setNeutralButtonIcon(getDrawable(R.drawable.ic_edit_note));
            alerta.setNeutralButton("", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    /*
                    Borrar el texto de los TEditText
                     */
                    //binding.tietNombre.setText("");
                    //binding.tietEmail.setText("");
                    //binding.tietTelefono.setText("");

                    //Ponemos el cursos en el campo del nombre
                    binding.tietNombre.requestFocus();
                }
            });

            //icono al boton negative === regresar al menu principal
            alerta.setNegativeButtonIcon(getDrawable(R.drawable.ic_clear));
            alerta.setNegativeButton("", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Retorno a la pantalla anterior
                    finish();
                }
            });

            //icono al boton positive === mandar los datos al sig activity
            alerta.setPositiveButtonIcon(getDrawable(R.drawable.ic_save));
            alerta.setPositiveButton("", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    /*
                    Los Intent puede enviar información serializda de una pantalla
                    a otra, utilizando un putExtra
                     putExtra está delimintado por el tipo de dato
                     putExtra("nombre_para_enviar", valor);
                     putExtra("edad", 33);
                     putExtra("nombre", "Raul");
                     */


                    /*
                    Vamos a la siguiente pantalla enviando los valores
                    ingrsados en en formulario
                     */
                    final String valorNombre = binding.tietNombre.getText().toString();
                    final String valorEmail = binding.tietEmail.getText().toString();
                    final String valorTelefono = binding.tietTelefono.getText().toString();
                    final int valorEdad = (int) binding.msEdad.getValue();

                    /*
                    Forma 1 de enviar valores entre Activitys
                     */
                    startActivity(
                        new Intent(
                            ValidacionesActivity.this,
                            RecibeDatosActivity.class
                        )
                        .putExtra("nombre", valorNombre)
                        .putExtra("email", valorEmail)
                        .putExtra("telefono", valorTelefono)
                        .putExtra("edad", valorEdad)
                    );

                    /*
                    Forma 2 de enviar valores entre Activitys
                    CON VARIABLES
                     */
                    /*Intent intentRD = new Intent(
                            ValidacionesActivity.this,
                            RecibeDatosActivity.class
                    );
                    intentRD.putExtra("nombre", valorNombre);
                    intentRD.putExtra("email", valorEmail);
                    intentRD.putExtra("telefono", valorTelefono);
                    intentRD.putExtra("edad", valorEdad);
                    startActivity(intentRD);*/
                }
            });

            //Mostramos la alerta
            alerta.show();

        });

    }

    /**
     *
     * @param texto
     * @return
     */
    public boolean validarNombre(String texto) {
        /*
        Si el texto es menor a 5 caracteres, el nombre es inválido
         */
        if (texto.trim().length() < 8) {
            // Mostramos el error en el TextInputLayout
            binding.tilNombre.setError("Nombre inválido, agrega un nombre de 8 letras como mínimo");
            //Quitamos el icono de error
            //binding.tilNombre.setErrorIconDrawable(null);
            return false;
        }

        else {
            binding.tilNombre.setErrorEnabled(false);
            return true;
        }
    }

    /**
     *
     * @param texto
     * @return
     */
    public boolean validarCorreo(String texto) {
        /*
        Utilizamos la librería android.Partterns para evaluar si un texto tiene
        el formato de un correo electrónico
         */
        if (Patterns.EMAIL_ADDRESS.matcher(texto).matches()) {
            //Elimanos el error
            binding.tilEmail.setErrorEnabled(false);

            return true;
        }

        //Si no es un formato válido de correo electrónico
        else {
            //Indicamos el error
            binding.tilEmail.setError("Ingresa un correo electrónico válido");
            //quitamos el icono de error
            //binding.tilEmail.setErrorIconDrawable(null);
            return false;
        }
    }

    /**
     *
     * @param texto
     * @return
     */
    public boolean validarTelefono(String texto) {
        /*
        Validamos con Patterns
        Si es un teléfono a 10 dígitos SIN ESPACIOS ni puntos ni guiones
        utilizamos el método de comparación contains que retorna verdadero
        cuanod una cadena de texto contiene el caracter indicado en cualquier
        posición
        "Hola m", "Holam ", " Holam"
        CADENA.contains(" ") ===> Verdadero si existe en cualquier pos.

        !CADENA.contains(" ") ===> Verdadero si NO existe en cualquier pos.
        El símbolo "!" indica el inverso o negación
         */

        if (
                Patterns.PHONE.matcher(texto).matches() &&
                texto.length() == 10 &&
                !texto.contains(" ") &&
                !texto.contains("-") &&
                !texto.contains(".")
        ) {
            binding.tilTelefono.setErrorEnabled(false);
            return true;
        }

        else {
            binding.tilTelefono.setError("Ingresa un teléfono válido a 10 dígitos");
            return false;
        }
    }

    /**
     *
      * @param valor
     * @return
     */
    public boolean validaEdad(int valor) {
        //Si es mayor de edad es válido
        if (valor >= 18) {
            binding.tilEdad.setErrorEnabled(false);
            return true;
        }

        else {
            binding.tilEdad.setError("Para continuar debes ser mayor de edad");
            binding.tilEdad.setErrorIconDrawable(null);

            return false;
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