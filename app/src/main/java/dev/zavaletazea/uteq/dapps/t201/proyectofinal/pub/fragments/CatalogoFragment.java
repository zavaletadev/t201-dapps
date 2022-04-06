package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.Helper;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.databinding.FragmentCatalogoBinding;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.entities.TipoProducto;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.TipoProductoAdapter;

public class CatalogoFragment extends Fragment {

    private FragmentCatalogoBinding binding;
    private RequestQueue conServ;
    //private final String END_POINT = Helper.baseUrl() + "back/carrito/gettipos";
    private final String END_POINT = "http://dtai.uteq.edu.mx/~uteq/awos/proyectos/carrito/back/catalogo/get_categorias";
    private TipoProductoAdapter adaptador;
    private NavController navController;

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

        /*
        Clic de cada item del listview
         */
        binding.lvCategorias.setOnItemClickListener((adapterView, view, i, l) -> {

            TextView tvIdtp = view.findViewById(R.id.tv_idtp);

            navController = Navigation.findNavController(
                    getActivity(),
                    R.id.host_public_fragments
            );

            Bundle datos = new Bundle();
            datos.putString(
                "idtp",
                tvIdtp.getText().toString()
            );
            navController.navigateUp();
            navController.navigate(
                R.id.ListaProductosFragment,
                datos
            );
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
                        Inicializamos una lista de tipoproducto
                         */
                        List<TipoProducto> datos = new ArrayList<>();

                        /*
                        Recorremos el arreglo JSON
                         */
                        for (int i = 0; i < arrTipos.length(); i++) {
                            //Por cada elemeno del arreglo JSON
                            //creamos un objeto
                            JSONObject objCat = arrTipos.getJSONObject(i);

                            //Crear un objeto de tiupoproducto
                            TipoProducto cat = new TipoProducto();

                            //Pasamos los datos del objeto JSON
                            //a nuestro tipoproducto
                            cat.setIdtp(objCat.getInt("idtp"));
                            cat.setDescripcion(objCat.getString("descripcion"));

                            //Agregamos el tipoproducto a nuestra lista

                            datos.add(cat);
                        }

                        /*
                        Creamos un adaptador (TipoProductoAdapter) y le pasamos
                        el arreglo de tipos
                         */
                        adaptador = new TipoProductoAdapter(
                            getActivity(),
                            datos
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