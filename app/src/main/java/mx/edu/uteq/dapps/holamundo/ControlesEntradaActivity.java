package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ControlesEntradaActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controles_entrada);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /*
    Para crear un menú independiente debemos
    realizar lo siguinete:
    1.- Asegurarnos de contar con la carpeta "menu"
        dentro de res
    2.- Crear un layout (XML) con la estructura del
        menú
    3.- Agregar items de menú y definir si los elementos de nuestro menu serán
        contextuales o acciones
    4.- Cargar / vincular el menu en el
        activity correspondiente
    5.- Programar las acciones de cada elemento del menu
        por medio de su ID
     */

    /*
    En este método cargamos el menú
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Indicamos la ruta de nuestro menu y la
        //agregamo al Screen
        //necesitamos dos parámetros
        //      1.- El layout (XML) de nuestro menú
        //      2.- Un argumento de menú para controlar las acciones
        getMenuInflater().inflate(R.menu.menu_controles, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    Método encargado de evaluar el click de TODOS los elementos del
    menu vinculado a estre screen
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            finish();
            break;

            /*
            Menu 1
             */
            case R.id.menu_m1:
                Toast.makeText(
                        this,
                        "Menú 1 seleccionado",
                        Toast.LENGTH_SHORT
                ).show();
            break;

            /*
            Menu Guardar
             */
            case R.id.menu_guardar:
                /*
                Android permite mostrar diálogos emergentes
                para indicar acciones o prevenir de ciertas acciones
                para que el usuario necesite confirmar o cancelar
                 */
                AlertDialog.Builder alerta;
                alerta = new AlertDialog.Builder(ControlesEntradaActivity.this);

                /*
                Una alerta / diálogo tiene las siguientes secciones:
                1.- Título
                2.- Texto o cuerpo de mensaje
                3.- Ìcono
                4.- Tres tipos de botones
                        Positive button
                        Negative button
                        Neutral  button
                5.- Si es cancelable (si al tocar el overlay/fondo se cierra)
                 */
                alerta.setTitle("Continuar");
                alerta.setMessage("¿Seguro que deseas continuar?");
                alerta.setIcon(R.drawable.ic_star);
                /*
                Las posiciones Positive, Negative y Neutral hacen referencia
                a las acciones que vas  a programar
                    Postive:  realizar acciones que agreguen,
                              generen o confirmen
                    Negative: realizar acciones que destruyan, cancelen,
                              nieguen
                    Neutral:  realizan acciones defecto sin desición

                La accion defecto de un botón es cerrar la ventana emergente
                */

                alerta.setNegativeButton(
                    "No",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(
                                new Intent(
                                        ControlesEntradaActivity.this,
                                        MenuActivity.class
                                )
                            );
                        }
                });
                alerta.setNeutralButton("Quedarme", null);
                alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,
                                        int i) {
                        startActivity(
                            new Intent(
                                    ControlesEntradaActivity.this,
                                    ControlesSeleccionActivity.class
                            )
                        );
                    }
                });

                //Evitamos que al tocar el Overlay la ventana desparezca
                alerta.setCancelable(false);
                /*
                Mostramos la alerta
                 */
                alerta.show();
            break;
        }


        return super.onOptionsItemSelected(item);
    }
}