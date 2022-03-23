package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.databinding.FragmentCatalogoBinding;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.TipoProductoAdapter;

public class CatalogoFragment extends Fragment {

    private FragmentCatalogoBinding binding;
    private RequestQueue conServ;
    private final String END_POINT = "https://zavaletazea.dev/awos/carrito/back/carrito/gettipos";
    private TipoProductoAdapter adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCatalogoBinding.inflate(inflater, container, false);

        //Inicializamos a volley
        conServ = Volley.newRequestQueue(getActivity());

        /*
        Ponemos en modo carga el swipeRefresh
        y cargamos las categorias justo al inicio
         */
        binding.srlCategorias.post(() -> {
            cargaCategorias();
        });

        /*
        Al arrastrar y soltar cargamos nuevamente las categorías
         */
        binding.srlCategorias.setOnRefreshListener(() -> {
            cargaCategorias();
        });

        return binding.getRoot();
    }

    public void cargaCategorias() {
        binding.srlCategorias.setRefreshing(true);

        /*
        Creamos una peticion al servicio
         */
        final StringRequest petServ = new StringRequest(
            Request.Method.GET,
            END_POINT,
            response -> {
                /*
                Intentamos crear un objeto json con la respuesta del servidor
                 */
                try {
                    JSONObject objRespuesta = new JSONObject(response);

                    //Si resultado es verdadero
                    if (objRespuesta.getBoolean("resultado")) {
                        /*
                        Tomamos el arreglo de tipos
                         */
                        JSONArray arrTipos = objRespuesta.getJSONArray("tipos");

                        /*
                        Creamos un adaptador (TipoProductoAdapter) y le pasamos
                        el arreglo de tipos
                         */
                        adaptador = new TipoProductoAdapter(
                            getActivity(),
                            arrTipos
                        );

                        /*
                        Asignamos el adaptador al listview
                         */
                        binding.lvCategorias.setAdapter(adaptador);

                    }
                }

                catch(Exception e) {
                    Toast.makeText(
                        getActivity(),
                        e.getMessage(),
                        Toast.LENGTH_LONG
                    ).show();
                }

                finally {
                    //Quitamos el loader
                    binding.srlCategorias.setRefreshing(false);
                }

            },
            error -> {
                binding.srlCategorias.setRefreshing(false);
                Toast.makeText(
                        getActivity(),
                        error.toString(),
                        Toast.LENGTH_LONG
                ).show();
            }
        );
        //Ejecutamos la petición al servicio
        conServ.add(petServ);
    }
}