package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mx.edu.uteq.dapps.holamundo.databinding.ActivityRegistroRemotoBinding;

public class RegistroRemotoActivity extends AppCompatActivity {

    private ActivityRegistroRemotoBinding binding;
    private ActionBar actionBar;

    private ProgressDialog progress;
    private final String URL_SERV = "http://dtai.uteq.edu.mx/~uteq/awos/proyectos/carrito/back/backend/registrausuario";

    /*
    Peticiones remotas con Volley
     */
    private RequestQueue conexionServ;
    private StringRequest peticionServ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegistroRemotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /*
        Inicializamos los componentes
         */
        progress = new ProgressDialog(RegistroRemotoActivity.this);
        conexionServ = Volley.newRequestQueue(RegistroRemotoActivity.this);

        /*
        Click login
         */
        binding.mbLogin.setOnClickListener(view -> {
            startActivity(
                    new Intent(
                            RegistroRemotoActivity.this,
                            LoginRemotoActivity.class
                    )
            );
        });

        /*
        Click del boton registrarme
         */
        binding.mbRegistro.setOnClickListener(view -> {
            progress.setTitle("Registrandote");
            progress.setMessage("Por favor espera...");
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();

            /*
            Validaciones necesarias:
            Nombre: min 3 caracteres SOLO LETRAS Y ACENTOS, diéresis y eñes
            Aapellidos: min 3 caracteres SOLO LETRAS Y ACENTOS, diéresis y eñes
            Coreo
            Contraseña: minimo 8 caracteres sin espacios '123 1234'
             */

            /*
            Ya validados, tomar los valores que el servicio necesita
             */
            final String nombre = binding.tietNombre.getText().toString();
            final String apellidos = binding.tietApellidos.getText().toString();
            final String correo = binding.tietUsuario.getText().toString();
            final String password = binding.tietPassword.getText().toString();

            /*
            Preparamos la peticioón al servicio
             1.- Método (POST/GET)
             2.- URL destino
             3.- ResponseListener
             4.- ErrorListener
            *5.- (Solo para POST)
                 Antes de ejecutar el servicio, debemos indicar las variables
                 tipo POST que vamos a enviar por medio de la petición
                 sobreescribiendo el método getParams y creando un mapa
                 de datos
             */
            peticionServ = new StringRequest(
                Request.Method.POST,
                URL_SERV,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();

                        /*
                        intentamos convertir el String de la respuesta
                        en un objetos JSON
                         */
                        try {
                            JSONObject objResp = new JSONObject(response);

                            //Tomamos el valor de la prop resultado
                            boolean resultado = objResp.getBoolean("resultado");
                            /*
                            Si resultado es verdadero, el usuario pudo registrarse
                            de lo contrario no se ejecutó el registro, asi que mostramos el error
                             */
                            String mensaje = objResp.getString("mensaje");
                            if (resultado) {
                                /*
                                Mensaje de éxito y hacer lo que sigue
                                 */
                                Snackbar
                                        .make(view, mensaje, Snackbar.LENGTH_INDEFINITE)
                                        .setAction("Login", view -> {
                                            //Redireccionamos al login
                                            startActivity(
                                                    new Intent(
                                                            RegistroRemotoActivity.this,
                                                            LoginRemotoActivity.class
                                                    )
                                            );
                                        })
                                        .show();
                            }

                            else {
                                Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG).show();
                            }

                        }

                        catch(Exception e) {

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.hide();
                    }
                }
            ) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //Creamos un mapa con claves tipo String
                    //y valores tipo String
                    Map<String, String> parametros = new HashMap<>();

                    /*
                    Por medio del método put podemos agregar una clave
                    y valor
                     */
                    parametros.put("nombre", nombre);
                    parametros.put("apellidos", apellidos);
                    parametros.put("correo", correo);
                    parametros.put("contra", password);

                    return parametros;
                } // getParams

            }; // peticionServ

            // Consumir el servicio
            conexionServ.add(peticionServ);

        }); // clic btn registro

    } // oncreate

    //Ayúdame a programar el optionsBacHome
}