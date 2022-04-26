package dev.zavaletazea.uteq.dapps.t201.demoextraordinario;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.json.JSONArray;

import java.util.List;

/*
El adaptador debe heredar de baseAdapter
 */
public class CategoriaAdapter extends BaseAdapter {

    private Activity activity;
    private JSONArray arregloCategorias;
    private LayoutInflater inflater;

    /*
    Constructor que inicia todos los parámetos
     */
    public CategoriaAdapter (Activity activity, JSONArray arregloCategorias, Context contexto) {
        this.activity = activity;
        this.arregloCategorias = arregloCategorias;
        this.inflater = LayoutInflater.from(contexto);
    }

    // Cuantos elementos tenemos en la lista?
    @Override
    public int getCount() {
        return arregloCategorias.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // Este método se ejecuta por cada elementos del arreglo
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // indicamos que el estilo de este spinner viene de mi item xml
        view = inflater.inflate(R.layout.item_categoria, null);
        return view;
    }
}
