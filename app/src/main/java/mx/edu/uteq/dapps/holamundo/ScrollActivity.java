package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

public class ScrollActivity extends AppCompatActivity {

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        actionBar = getSupportActionBar();
        //si el actiobar existe, mostrmoas el boton
        //de regreso (home/back)
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    } // oncreate

    //click de los elementos de ActionBar

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //si se presiona el botón de hom/back
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}