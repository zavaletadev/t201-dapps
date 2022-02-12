package mx.edu.uteq.dapps.holamundo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.core.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ControlesSeleccionActivity extends AppCompatActivity {

    private ActionBar actionBar;

    /*
    Agregamos un atributo para controlar
    nuestro AutoCompleteTextView
     */
    private AutoCompleteTextView actvLenProg;

    /*
    Arreglo de valores para el ACTV
     */
    private final String[] LENGUAJES = new String[] {
            "Java", "PHP", "Javacript", "Kotlin",
            "ReactNative", "Swift", "Python", "C++", "C"
    };

    /*
    Creamos un adaptador para pasar el arreglo de lenguajes
    a la vista
     */
    private ArrayAdapter<String> adaptador;

    /*
    Atributos para DatePicker
     */
    private TextInputLayout tilDatepicker;
    private TextInputEditText tietDatepicker;

    private MaterialDatePicker.Builder datePickerBuilder;
    private MaterialDatePicker datePicker;

    /*
    Atributos para DatepickerRange
     */
    private MaterialButton mbDatepickerRange;
    private MaterialDatePicker.Builder<Pair<Long, Long>> datePickerRangeBuilder;
    private MaterialDatePicker datePickerRange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controles_seleccion);

        actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /*
        Ligamos el ACTV de la vista al atributo
         */
        actvLenProg = findViewById(R.id.actv_len_prog);

        /*
        Inicializamos el adaptador indicando lo siguiente:
        1.- Contexto
        2.- Estilo y diseño del adaptador
            *NOTA: puedes usar el estilo defecto de Android
        3.- Los datos a compartir con la vista (arreglo)
         */
        adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                LENGUAJES
        );

        /*
        Indicamos el contenido al ACTV
         */
        actvLenProg.setAdapter(adaptador);

        /*
        Ligamos los componentes del Datepicker
         */
        tilDatepicker = findViewById(R.id.til_datepicker);
        tietDatepicker = findViewById(R.id.tiet_datepicker);
        datePickerBuilder = MaterialDatePicker.Builder.datePicker();

        /*
        Ligamos los componentes del DatePicker
         */
        mbDatepickerRange = findViewById(R.id.mb_datepicker_range);
        datePickerRangeBuilder = MaterialDatePicker.Builder.dateRangePicker();
        datePickerRangeBuilder.setTitleText("Selecciona un rango de fechas");
        datePickerRange = datePickerRangeBuilder.build();

        /*
        Los datepickers simples (una sola fecha)
        tienen 2 parámetros configurables:
        1.- Título
        2.- fecha de inicio
         */
        datePickerBuilder.setTitleText("Selecciona una fecha");

        //Ponemos fecha defecto (hoy)
        //datePickerBuilder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());

        //construirmos la ventana
        datePicker = datePickerBuilder.build();

        /*
        Programamos el click en el icono de la derecha (start)
        del TextInpuLayout
         */
        tilDatepicker.setStartIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Borramos el texto del TextInputEditText
                tietDatepicker.setText("");
            }
        });

        /*
        Programamos el click en el icono de la izquierda (end)
        del TextInputLayout
         */
        tilDatepicker.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Debemos la ventana del DatePicker para poderla mostrar
                 */
                datePicker.show(getSupportFragmentManager(), "date");
            }
        });

        /*
        Click del boton date picker range
         */
        mbDatepickerRange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Mostramos el Datepicker previamente construido
                 */
                datePickerRange.show(getSupportFragmentManager(), "range_date");
            }
        });

    } // oncreate

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}