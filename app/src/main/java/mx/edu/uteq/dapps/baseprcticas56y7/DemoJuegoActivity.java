package mx.edu.uteq.dapps.baseprcticas56y7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class DemoJuegoActivity extends AppCompatActivity {

    private ActionBar actionBar;

    private final int NUM_MIN = 1;
    private final int NUM_MAX = 100;
    private final int NUMERO_ADIVINAR = (int) Math.floor(Math.random() * (NUM_MAX - NUM_MIN + 1) + NUM_MIN);
    private int vidas;

    private TextView tvVidas;
    private ImageView ivMensaje;
    private TextView tvMensaje;
    private TextInputEditText tietNumero;
    private Button btnAdivinar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_juego);

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tvVidas = findViewById(R.id.tv_vidas);
        ivMensaje = findViewById(R.id.iv_respuesta);
        tvMensaje = findViewById(R.id.tv_mensaje);
        tietNumero = findViewById(R.id.tiet_numero);
        btnAdivinar = findViewById(R.id.btn_adivinar);
        vidas = 4;

        Snackbar.make(
                findViewById(android.R.id.content),
                "El numero a adivinar es " + NUMERO_ADIVINAR,
                Snackbar.LENGTH_INDEFINITE
        ).setAction("Ok", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();

        btnAdivinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numeroSeleccionado = Integer.parseInt(tietNumero.getText().toString());

                if (numeroSeleccionado == NUMERO_ADIVINAR){
                    ivMensaje.setImageDrawable(getResources().getDrawable(R.drawable.check));
                    tvMensaje.setText("¡Si!, el número es " + NUMERO_ADIVINAR);
                }

                else {
                    vidas--;
                    ivMensaje.setImageDrawable(getResources().getDrawable(R.drawable.cancel));

                    String numeroBuscarEs = NUMERO_ADIVINAR > numeroSeleccionado ? "mayor" : "menor";

                    tvMensaje.setText("Lo siento, el número es " + numeroBuscarEs + " que " + numeroSeleccionado);

                    String textoVidas = "";
                    for (int i = 1; i <= vidas; i++) {
                        textoVidas += "♥";
                    }

                    tvVidas.setText(textoVidas);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}