package mx.edu.uteq.dapps.ejemplofragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import mx.edu.uteq.dapps.ejemplofragment.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    /*
    Binding es un método inteligente de vinculación
    de capas

    Binding se encarga de ligar automaticamente todos los elementos
    de la vista con el controlador (hace el findViewById por ti)

    Para usarlo:
    binding.ID_DEL_COMPONENTE
     */
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this).navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        /*
        Creamos el evento click del boton fragment tres

         */
        binding.btnFragmentTres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Vamos a navegar a otro fragmento

                Para navegar entre fragmentos necesitas encontrarte en el Activiy contenedor
                del FragmentHost

                Para navegar necesitas:
                - Un NavController = Activity, ID_DEL_HOST_FRAGMENT
                - Eliminar el cache de navegación
                - navigate(ID_FRAGMENTO_DONDE_QUIERO_IR)
                 */

                //- Un NavController = Activity, ID_DEL_HOST_FRAGMENT
                NavController navController = Navigation.findNavController(
                        getActivity(),
                        R.id.nav_host_fragment_content_main
                );

                //- Eliminar el cache de navegación
                navController.navigateUp();

                //- navigamos a donde queremos ir(ID_FRAGMENTO_DONDE_QUIERO_IR)
                // Es el que le pusimo en nav_graph.xml
                navController.navigate(R.id.TresFragment);

            }
        });

        /*
        Click a otro Screen
         */
        binding.btnOtroactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Invocamos a startActivity PEEEEEERO DESDE NUESTRO ACTIVITY PADRE
                 */
                startActivity(
                        new Intent(
                                getActivity(),
                                OtraPnatallaActivity.class
                        )
                );
            }
        });

    } // oncreate

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}