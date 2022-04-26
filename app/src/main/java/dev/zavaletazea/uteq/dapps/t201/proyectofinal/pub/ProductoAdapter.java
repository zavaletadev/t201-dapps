package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.Helper;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;

public class ProductoAdapter extends BaseAdapter {

    public JSONArray datos;
    public LayoutInflater inflater;
    public Activity activity;

    public ProductoAdapter (Context contexto, JSONArray datos, Activity activity) {
        this.datos = datos;
        inflater = LayoutInflater.from(contexto);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return datos.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.item_producto, null);

            try {
                JSONObject objProducto = datos.getJSONObject(i);

                TextView tvIdProducto = view.findViewById(R.id.tv_idproducto);
                tvIdProducto.setText(
                        objProducto.getString("idproducto")
                );
                final String idProducto = objProducto.getString("idproducto");

                ImageView ivImagen = view.findViewById(R.id.iv_img_producto);
                Picasso.get()
                        .load(
                        Helper.baseUrl() +
                        "back/static/upload/img/" +
                        objProducto.getString("idproducto") + ".jpg"
                ).placeholder(R.drawable.sand_clock)
                .into(ivImagen);

                TextView tvNombre = view.findViewById(
                        R.id.tv_nomproducto
                );
                tvNombre.setText(objProducto.getString("nomproducto"));

                TextView tvPrecio = view.findViewById(
                        R.id.tv_precio
                );
                tvPrecio.setText(
                        "$" +
                        objProducto.getString("precio") +
                        " MXN"
                );

                TextView tvExistencias = view.findViewById(
                        R.id.tv_exist
                );
                tvExistencias.setText(
                        objProducto.getString("existencia")
                        + " pzs."
                );

                /*
                Programos nuestros botones
                 */
                MaterialButton mbAgregaListadeseos;
                mbAgregaListadeseos = view.findViewById(R.id.mb_agrega_listadeseos);

                mbAgregaListadeseos.setOnClickListener(view1 -> {
                    Toast.makeText(
                            activity,
                            "Aqui en el clic id: [" + idProducto + "]" ,
                            Toast.LENGTH_SHORT).show();
                });
            }

            catch(Exception e) {
                Log.e("Error lista prod", e.getMessage());
            }
        }
        return view;
    }
}
