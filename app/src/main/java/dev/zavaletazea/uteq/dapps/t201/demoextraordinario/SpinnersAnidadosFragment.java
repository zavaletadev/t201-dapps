package dev.zavaletazea.uteq.dapps.t201.demoextraordinario;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.zavaletazea.uteq.dapps.t201.demoextraordinario.databinding.FragmentSpinnersAnidadosBinding;

public class SpinnersAnidadosFragment extends Fragment {

    // Utilizamos bindign pata ahorrarnos variables
    // Asegúrate de que ViewBinding esté habilitado en tu Gradle.app
    private FragmentSpinnersAnidadosBinding binding;

    // Creamos un String para guardar la URL del servicio
    private final String END_POINT = "https://zavaletazea.dev/awos/carrito/back/ejemplos_extra/get_categorias";

    // Creamos un ArrayList para llenar el Spinner con la respuesta
    // del servidor
    private List<String> listaCategorias;

    // Creamos un adaptador para mostrar la lista de valores desde el controlador
    // a la vista
    private ArrayAdapter<String> adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSpinnersAnidadosBinding.inflate(
                inflater, container, false
        );

        // Creamos un progressDialog con el contexto del padre
        ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setTitle("Recolectando info");
        progress.setMessage("Por favor espera...");
        progress.setCancelable(false);
        progress.setIndeterminate(true);
        progress.show();

        /*
        Creamos una petición de Volley
        Necesitmos:
        1.- conexión de tipo Requestqueue
        2.- petición de tipo StringRequest
        3.- Tipo de servicio (GET/POST)
        4.- URL del servicio
         */

        // 1
        RequestQueue conexionServ;
        conexionServ = Volley.newRequestQueue(getActivity());

        //2
        StringRequest peticionServ = new StringRequest(
                Request.Method.POST,
                END_POINT,
                response -> {
                    // Intentamos crear un objeto json con el contenido
                    // de la respuesta
                    Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();
                    try {
                        // Creamos un objeto JSON desde la respuesta (response)
                        JSONObject objRespuesta = new JSONObject(response);

                        // Tomamos SOLO las categorías del servicio
                        JSONArray jsonCategorias = objRespuesta.getJSONArray("categorias");

                        // Inicializamos la lista de valores
                        listaCategorias = new ArrayList<>();
                        listaCategorias.add(" = = = SELECCIONA = = = ");

                        // Recorrer el arreglo json de categorias y llenar la lista
                        for(int i = 0; i < jsonCategorias.length(); i++) {
                            // Creamos un objeto de categoría
                            JSONObject objCategoria = jsonCategorias.getJSONObject(i);

                            // Agregamos el texto a listview
                            listaCategorias.add(objCategoria.getString("descripcion"));
                        }

                        //Inicializamos el adaptador con los valores agregados previamente
                        /*
                        1.- Contexto
                        2.- Diseño de cada elementos del spinner
                        3.- Lista de valores
                         */
                        adaptador = new ArrayAdapter<>(
                                getActivity(),
                                android.R.layout.simple_spinner_dropdown_item,
                                listaCategorias
                        );

                        //Ligamos el adaptador a nuestro spinner
                        binding.spCategorias.setAdapter(adaptador);

                        // Oculto el progress
                        progress.hide();
                    }

                    catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    // Indicamos que salió mal
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                }
        );
        //Ejecuto el servicio en la conexión
        conexionServ.add(peticionServ);

        /*
        Programos un cambio en los valores del spinner
         */
        binding.spCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,
                                       int i, long l) {
                // si el indice es diferente de 0, es decir,
                // hasta que seleccione algo
                if (i != 0) {
                    // Mostramos el texto del elemento seleccionado
                    Toast.makeText(
                            getActivity(),
                            binding.spCategorias.getItemAtPosition(i).toString(),
                            Toast.LENGTH_SHORT
                    ).show();

                    progress.show();

                    /*
                    Invocamos a otro volley
                    */


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Esta siempre debe ser la última línea de un fragmento
        return binding.getRoot();
    }
}