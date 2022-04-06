package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.entities.TipoProducto;

/*
Un adaptador es un intermediario entre la vista y el controlador
en esta clase vamos a definir la colección de valores y tambien el diseño
para cada elementos

Android nos provee una serie de métodos que nos facilitan el manejo de
Adaptadores, la clase se llama BaseAdapter y para utililizarla debemos
extender (heredar) de dicha clase

OJO:
Es una clase abstracta, lo que nos obliga a implementar todos sus metodos aunque
no los utilicemos
 */
public class TipoProductoAdapter extends BaseAdapter {

    /*
    Los adaptadores necesitan 3 elementos como minimo para funcionar:
        Contexto
        Colección de datos (LISTA DE TIPOPRODUCTO)
        Latyout (diseño) de cada elementos del arreglo
     */
    private List<TipoProducto> datos; //Arreglo de datos del servicio
    private LayoutInflater inflater; //Componente para ligar el xml a cada elementos de datos

    public TipoProductoAdapter(Context contexto, List<TipoProducto> datos)  {
        /*
        Inicializar los elementos del adapter
         */
        inflater = LayoutInflater.from(contexto);
        this.datos = datos;
    }


    /*
    Retornamos el numero de elementos de la lista
     */
    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /*
    Est método se ejecuta por cada elemento del arreglo
    el parámetro 'i' hace referencia al elementos del arreglo === datos(i)
    el parámetro 'view' contiene una refrencia a cada componente
    de lalección
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*
        Si la vista ya cargó, reutilizamos el mismo layout
        de lo contrario en view montamos nuestros layout
         */
        if (view == null) {
            view = inflater.inflate(R.layout.item_tipoproducto, null);
        }

        /*
        Indicar los valores que deben mostrarse en cada elemento
         */
        try {
            /*
            Creamos un objeto  de tipo producto
            por cada elemento de la lista
             */
            TipoProducto cat = datos.get(i);

            /*
            View puede acceder al xml
            y tomar o poner valores por medio del id
            de los componentes gráficos
             */
            TextView tvIdtp = view.findViewById(R.id.tv_idtp);
            //Convertimos el id a String
            tvIdtp.setText(String.valueOf(cat.getIdtp()));

            TextView tvDesc = view.findViewById(R.id.tv_descripcion);
            tvDesc.setText(cat.getDescripcion());
        }
        catch (Exception e) {
            Log.e("Error tiposprod", e.getMessage());
        }

        return view;
    }
}
