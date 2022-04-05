package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.Helper;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.databinding.FragmentListaProductosBinding;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.ProductoAdapter;

public class ListaProductosFragment extends Fragment {

    private FragmentListaProductosBinding binding;
    private RequestQueue conServ;
    private String idTipoproducto;
    private final String END_POINT = Helper.baseUrl() + "back/carrito/getproductos";
    private ProductoAdapter adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentListaProductosBinding.inflate(inflater, container, false);

        final Bundle datos = getArguments();
        idTipoproducto = datos.getString("idtp");
        conServ = Volley.newRequestQueue(getActivity());

        binding.srlProductos.post(() -> {
            actualizaProductos();
        });

        binding.srlProductos.setOnRefreshListener(() -> {
            actualizaProductos();
        });

        return binding.getRoot();
    }

    public void actualizaProductos() {
        binding.srlProductos.setRefreshing(true);
        final StringRequest petServ = new StringRequest(
            Request.Method.POST,
            END_POINT,
            response -> {
                try {
                    JSONObject objRespuesta = new JSONObject(response);

                    if (objRespuesta.getBoolean("resultado")) {
                        adaptador = new ProductoAdapter(
                                getActivity(),
                                objRespuesta.getJSONArray("productos")
                        );
                        binding.lvProductos.setAdapter(adaptador);
                    }
                }

                catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                finally {
                    binding.srlProductos.setRefreshing(false);
                }
            },
            error -> {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                binding.srlProductos.setRefreshing(false);
            }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("idtp", idTipoproducto);

                return parametros;
            }
        };
        conServ.add(petServ);
    }
}