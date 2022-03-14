package mx.edu.uteq.dapps.holamundo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import mx.edu.uteq.dapps.holamundo.databinding.ActivityListViewEjemploBinding;

public class ListViewEjemploActivity extends AppCompatActivity {

    /*
    Utilizamos autoBindingView
     */
    private ActivityListViewEjemploBinding binding;

    /*
    Para mostrar elementos dentro de un ListView
    necesitamos los siguientes elementos:
    1.- Referencia a un ListView (id) [N/A si usas bindingView]
    2.- Colección de datos (List<String>)
    3.- Adaptador del tipo de la colección
        3.1.- Contexto
        3.2.- Layout del diseño de cada elemento (item)
        3.3.- Referencia a la colección de datos
    4.- Conectar la referencia del ListView con el adaptador
     */
    private List<String> mpios;

    //Los adaptadores y la colección deben
    //ser del mismo tipo de dato
    private ArrayAdapter<String> adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ligamos a binding con la vista (XML)
        binding = ActivityListViewEjemploBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
        Vamos a crear elementos para nuestra colección de datos
         */
        mpios = new ArrayList<>();
        for (int i = 0; i <= 1000; i++)
        {
            mpios.add("["+i+"] Querétaro");
            mpios.add("["+i+"] Corregidora");
            mpios.add("["+i+"] El Marqués");
            mpios.add("["+i+"] Colón");
            mpios.add("["+i+"] Jalpan de Serra");
            mpios.add("["+i+"] Pinal de Amoles");
        }


        /*
        Inicializamos el adaptador con sus respectiso parámetros
        1.- Contexto
        2.- Layout del diseño de cada elemento (item)
        3.- Referencia a la colección de datos
         */
        adaptador = new ArrayAdapter<>(
            ListViewEjemploActivity.this,
            android.R.layout.simple_expandable_list_item_1,
            mpios
        );

        //Conectamos el adaptador al componente
        binding.lvEjemploLocal.setAdapter(adaptador);

    }
}