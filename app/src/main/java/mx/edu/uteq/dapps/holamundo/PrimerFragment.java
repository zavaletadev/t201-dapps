package mx.edu.uteq.dapps.holamundo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class PrimerFragment extends Fragment {

    private Button btnFragment2;
    private Button btnFragment3;
    private MaterialButton mbToast;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*
        Los Fragmentos no permiten utilizar directamente los métodos de AppCompat, es necesario
        indicar que se está generando una instancia del Fragment y su Activity desde una variable

        Los fragmentos no tienen contexto (Activity) por lo tanto el método
        findViewById no está disponible para la clase PERO SI PARA LA INSTANCIA
        DEL ACTIVITY

        Guardamos la instancia del fragmento en una view
         */
        View fragmento = inflater.inflate(R.layout.fragment_primer, container, false);

        //Fragmento nos permite acceder a los componentes visuales
        btnFragment2 = fragmento.findViewById(R.id.btn_fragment2);
        btnFragment3 = fragmento.findViewById(R.id.btn_fragment3);
        mbToast = fragmento.findViewById(R.id.mb_toast);

        /*
        Click al boton toast
         */
        mbToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Cuando necesites invocar al contexto del Activity, recuerda
                que los fragmentos no tienen, pero el método getActivity()
                invoca al contexto de quien contiene al fragmento
                 */
                Toast.makeText(
                        getActivity(),
                        "Hola desde mi fragmento",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Podemos generar link predeterminados desde el archivo
                nav_*.xml y solo invocarlos

                Para invocarlo:
                NavHostFragment
                    .findNavController(Fragmento)
                    .navigate(ID_DEL_ACTION);
                 */
                NavHostFragment
                        .findNavController(PrimerFragment.this)
                        .navigate(R.id.ac_f1_f2);
            }
        });

        btnFragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PrimerFragment.this).navigate(R.id.ac_f1_f3);
            }
        });

        /*
        ESTA SIEMPRE DEBE SER LA ULTIMA LINEA DE CÓDIGO DEL FRAGMENTO
        Retornamos la instancia del fragmento con su programación
         */
        return fragmento;
    }
}