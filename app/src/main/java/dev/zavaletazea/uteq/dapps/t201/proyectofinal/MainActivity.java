package dev.zavaletazea.uteq.dapps.t201.proyectofinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.priv.HomeActivity;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.PublicActivity;

public class MainActivity extends AppCompatActivity {

    private final String END_POINT = Helper.baseUrl() + "back/usuarios/get_user_token";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Abrimos un espacio de trabajo llamado dapps_201
        final SharedPreferences sPrefs = getSharedPreferences(
                "dapps_201",
                Context.MODE_PRIVATE
        );

        //Revisamos si tenemos guardados un calor para el token y uno
        //para el id del usuario encriptado
        final String userAuthToken = sPrefs.getString("uat", null);
        final String userAuthId = sPrefs.getString("uai", null);

        if (userAuthToken != null && userAuthId != null) {
            final RequestQueue conServ = Volley.newRequestQueue(MainActivity.this);
            final StringRequest petServ = new StringRequest(
                    Request.Method.POST,
                    END_POINT,
                    response -> {
                        try {
                            JSONObject objRespuesta = new JSONObject(response);

                            //Si resultado es verdadero enviamos al home
                            if (objRespuesta.getBoolean("resultado")) {
                                finish();
                                startActivity(
                                        new Intent(
                                                MainActivity.this,
                                                HomeActivity.class
                                        )
                                );
                            }

                            //Si no encontramos ese token para ese usuario
                            // direccionamos a la sección pública
                            else {
                                finish();
                                startActivity(
                                        new Intent(
                                                MainActivity.this,
                                                PublicActivity.class
                                        )
                                );
                            }
                        }

                        catch (Exception e) {
                            finish();
                            startActivity(
                                    new Intent(
                                            MainActivity.this,
                                            PublicActivity.class
                                    )
                            );
                        }

                    },
                    error -> {
                        finish();
                        startActivity(
                                new Intent(
                                        MainActivity.this,
                                        PublicActivity.class
                                )
                        );
                    }
            )
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put("token", userAuthToken);
                    parametros.put("md5_idusuario", userAuthId);

                    return parametros;
                }
            };
            conServ.add(petServ);
        }

        //Si no encontramos guardados los valores en las preferencias
        //direccionamos al sitio público
        else {
            finish();
            startActivity(
                    new Intent(
                            MainActivity.this,
                            PublicActivity.class
                    )
            );
        }
    }


}