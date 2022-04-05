package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.databinding.ActivityPublicBinding;

public class PublicActivity extends AppCompatActivity {

    private ActivityPublicBinding binding;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPublicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
        Creamos un contorlador del contenido
        del fragmento
         */
        navController = Navigation.findNavController(
                PublicActivity.this,
                R.id.host_public_fragments
        );

        /*
        Controlamos el actionbar desde las acciones
        de los hijos del fragmento
         */
        appBarConfiguration = new AppBarConfiguration.Builder(
                navController.getGraph()
        ).build();

        /*
        Indicamos quien controla el Actionbar
         */
        NavigationUI.setupActionBarWithNavController(
                PublicActivity.this,
                navController,
                appBarConfiguration
        );

        /*
        Click del FAB del carrito
         */
        binding.fabCarrito.setOnClickListener(view -> {
            //Mostramos el fragment del carrito
            navController.navigateUp();
            navController.navigate(R.id.CarritoFragment);
        });

    } // oncreate

    /*
    Programamos el cambio del actionbar
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    /*
    Ligamos el menú principal a este activity
    Necesitamos un menu en res/menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_public, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    Click de cada elemento del menu
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*
        Dependiendo del ID del menu, indicamos que fragmento se muestra
         */
        switch(item.getItemId()) {
            case R.id.menu_pub_catalogo:
                //Mostramos el fragmento del catálogo
                navController.navigateUp();
                navController.navigate(R.id.CatalogoFragment);
            break;

            case R.id.menu_pub_login:
                //Param pasar datos
                Bundle datos = new Bundle();
                datos.putString("nombre", "raul");
                datos.putString("matricula", "2007313037");
                navController.navigateUp();
                navController.navigate(R.id.LoginFragment, datos);
            break;
        }
        return super.onOptionsItemSelected(item);
    }
}