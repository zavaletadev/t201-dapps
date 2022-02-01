package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class LinearLayoutActivity extends AppCompatActivity {

    //Atributo para controlar el ActionBar
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        //Inicializamos el ActionBar
        actionBar = getSupportActionBar();
        //Solo si tenemos ActionBar mostramos el boton home (back)
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    } //onCreate

    //Programamos el clic del boton back
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Si el boton presionado es home (back)
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}