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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mx.edu.uteq.dapps.holamundo.databinding.ActivityLoginRemotoBinding;

public class LoginRemotoActivity extends AppCompatActivity {

    private ActivityLoginRemotoBinding binding;

    //Configura el ActionBar por fa
    private ActionBar actionBar;

    /*
    Instancia de Volley
     */
    private RequestQueue conServ;
    private StringRequest petServicio;

    private final String END_POINT = "http://dtai.uteq.edu.mx/~uteq/awos/proyectos/carrito/back/backend/acceso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginRemotoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Inicializamos a Volley
        conServ = Volley.newRequestQueue(LoginRemotoActivity.this);

        //click al registro
        binding.mbRegistro.setOnClickListener(view ->{
            startActivity(
                    new Intent(
                            LoginRemotoActivity.this,
                            RegistroRemotoActivity.class
                    )
            );
        });

        /*
        Click en el boton login
         */
        binding.mbLogin.setOnClickListener(view -> {

            /*
            Antes de esto validamos:
            1.- el correo es válido
            2.- la contraseña tiene mínimo 8 caracteres y máximo 16
             */

            final ProgressDialog progress = new ProgressDialog(
                    LoginRemotoActivity.this
            );

            final String usuario = binding.tietUsuario.getText().toString();
            final String password = binding.tietPassword.getText().toString();

            progress.setTitle("Iniciando sesión");
            progress.setMessage("Por favor espera...");
            progress.setCancelable(false);
            progress.setIndeterminate(true);
            progress.show();

            /*
            Configuramos a petición al servicio
             */
            petServicio = new StringRequest(
                Request.Method.POST,
                END_POINT,
                response -> {
                    //Si el servicio responde en formato json
                    try {
                        JSONObject respuesta = new JSONObject(response);

                        /*
                        Si resultado es verdadero, redireccionamos
                        al home login
                         */
                        if (respuesta.getBoolean("resultado")) {
                            /*
                            Creamos un objetos json a partir de los datos
                            del usuario
                             */
                            JSONObject objUsuario = respuesta.getJSONObject("usuario");
                            startActivity(
                                    new Intent(
                                            LoginRemotoActivity.this,
                                            HomeLoginActivity.class
                                    )
                                    .putExtra("id", objUsuario.getString("idusuario"))
                                    .putExtra("nombre", objUsuario.getString("nombre"))
                                    .putExtra("apellidos", objUsuario.getString("apellidos"))
                            );
                        }

                        //Si el usuario o contraseña son incorrectos
                        else {
                            Snackbar
                                    .make(view, respuesta.getString("mensaje"), Snackbar.LENGTH_INDEFINITE)
                                    .setAction("Aceptar", viewSnack -> {
                                        binding.tietUsuario.setText("");
                                        binding.tietPassword.setText("");
                                        binding.tietUsuario.requestFocus();
                                    })
                                    .show();
                        }

                        progress.hide();
                    }

                    //Si no responde en formato json
                    catch(Exception e) {
                        Toast.makeText(
                                this,
                                e.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                        progress.hide();
                    }
                },
                //si no responde o responde con un código de error
                errorResponse -> {
                    Toast.makeText(
                            this,
                            errorResponse.toString(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            )
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //Creamos nuestros parámetros al servidor
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("correo", usuario);
                    parametros.put("contra", password);
                    return parametros;
                }
            }; //petServ
            conServ.add(petServicio);

        }); // click

    } // oncreate

} // clase