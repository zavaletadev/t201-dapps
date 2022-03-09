package mx.edu.uteq.dapps.holamundo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import mx.edu.uteq.dapps.holamundo.databinding.ActivityIntroSserviciosBinding;

public class IntroServiciosActivity extends AppCompatActivity {

    private ActivityIntroSserviciosBinding binding;

    /*
    Creamos una alerta de tiempo indeterminado
     */
    private ProgressDialog pdServicio;

    /*
    Para usar Volley necesitamos:
    1.- Una conexión http
    2.- Una petición con la información del servicio:
        2.1.- Tipo ( GET/POST/PUT/DELTE )
        2.2.- Url del servicio
        2.3.- Que hacer si el servicio RESPONDIÓ
        2.4.- Que hacer si el servicio NO RESPONDIÓ
        *2.5.- Para servicios POST / PUT / DELETE
            Indicar las variables que desemos enviar
     */
    private RequestQueue conexionServ;
    private StringRequest peticionServ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroSserviciosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
        Aseguramos que nuestra app tiene explícitamente el permiso de
        internet desde el Manifest
        Y agregar la librería Volley desde el arhivo gradle
         */

        /*
        Los ProgressDialog son muy similares a los alerts
         */
        pdServicio = new ProgressDialog(IntroServiciosActivity.this);
        pdServicio.setTitle("Cargando datos");
        pdServicio.setMessage("Por favor espera...");
        pdServicio.setIndeterminate(true);
        pdServicio.setCancelable(false);
        pdServicio.show();

        /*
        Inicialiamos los parámetros de volley
         */
        conexionServ = Volley.newRequestQueue(
                IntroServiciosActivity.this
        );

        /*
        Ejecutamos al servicio web
         */
        listarDatos();

        /*
        Programamos el evento de arrastrar (swipe)
         */
        binding.srlIs.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listarDatos();
            }
        });

    } // Oncreate

    /**
     * Método que consume el servicio demo
     * y muestra la info en los TextViews
     */
    public void listarDatos() {

        /*
        Mostramos el loader dle Swipe
         */
        binding.srlIs.setRefreshing(true);

        /*
        Inicializamos la petición la servidor con los parámetros:
            1.- Tipo ( GET/POST/PUT/DELTE )
            2.- Url del servicio
            3.- Que hacer si el servicio RESPONDIÓ
            4.- Que hacer si el servicio NO RESPONDIÓ
            *5.- Para servicios POST / PUT / DELETE
                Indicar las variables que desemos enviar
        */
        peticionServ = new StringRequest(
                //1.- Método de petición
                Request.Method.GET,
                //2.- URL
                "https://zavaletazea.dev/ejemplo_json_demo.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        pdServicio.hide();

                        /*
                        Ocultamos el loader del swipe
                         */
                        binding.srlIs.setRefreshing(false);

                        //Toda la respuesta del servicio se empaqueta
                        //dentro de la variable response
                        try {
                            /*
                            A partir de la cadena de texto respuesta
                            intentamos convertirla a un objeto JSON
                             */
                            JSONObject objRespuesta = new JSONObject(response);

                            /*
                            Los objetos json se consultan a partir de
                            su tipo de dato
                            objRespuesta.getString("propiedad");
                            objRespuesta.geInt("propiedad");
                            objRespuesta.getFloat("propiedad");
                            objRespuesta.getJsonArray("propiedad");
                             */

                            //Mostramos el  nombre en el TextView
                            binding.tvNombre.setText(
                                    objRespuesta.getString("nombre")
                            );

                            binding.tvMatricula.setText(
                                    objRespuesta.getString("matricula")
                            );

                            binding.tvEdad.setText(
                                    objRespuesta.getInt("edad") +
                                            " años"
                            );

                            binding.tvPromedio.setText(
                                    objRespuesta.getDouble("promedio") + ""
                            );

                            binding.tvGrupo.setText(
                                    objRespuesta.getString("grupo")
                            );

                            binding.tvTitulado.setText(
                                    //if titulado == true                       //SI         //NO
                                    objRespuesta.getBoolean("titulado") ? "Titulado" : "Sin titularse"
                            );

                        }

                        catch(Exception e) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(
                                    IntroServiciosActivity.this
                            );
                            alerta.setTitle("¡Error al genera JSON!");
                            alerta.setMessage(e.getLocalizedMessage());
                            alerta.setNeutralButton("Ok", null);
                            alerta.show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pdServicio.hide();

                        //Ocultamos el loader del swipe
                        binding.srlIs.setRefreshing(false);

                        /*
                        El error viene definido en la variable
                        error
                         */
                        AlertDialog.Builder alerta = new AlertDialog.Builder(
                                IntroServiciosActivity.this
                        );
                        alerta.setTitle("¡ERROR!");
                        alerta.setMessage(error.toString());
                        alerta.setNeutralButton("Ok", null);
                        alerta.show();
                    }
                }
        );
        //Ejecutar la petición
        conexionServ.add(peticionServ);
    }

} // clase