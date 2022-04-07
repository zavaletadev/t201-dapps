package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.fragments;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.Helper;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.databinding.FragmentLoginBinding;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.PublicActivity;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private NavController navController;
    private RequestQueue conServ;
    private ProgressDialog progress;
    private final String END_POINT = Helper.baseUrl() + "back/usuarios/acceso";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        conServ = Volley.newRequestQueue(getActivity());
        progress = new ProgressDialog(getActivity());

        navController = Navigation.findNavController(
                getActivity(),
                R.id.host_public_fragments
        );

        binding.mbRegistro.setOnClickListener(view -> {
            navController.navigateUp();
            navController.navigate(R.id.RegistroFragment);
        });

        binding.mbLogin.setOnClickListener(view -> {
            progress.setTitle("Autenticando");
            progress.setMessage("Por favor espera...");
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();

            final StringRequest petServ = new StringRequest(
                Request.Method.POST,
                END_POINT,
                response -> {
                    try {
                        JSONObject objRespuesta = new JSONObject(response);

                        if (objRespuesta.getBoolean("resultado")) {

                            final JSONObject objUsuario = objRespuesta.getJSONObject("usuario");
                            final String token = objRespuesta.getString("token");

                            Snackbar.make(
                                view,
                                objRespuesta.getString("mensaje"),
                                Snackbar.LENGTH_LONG
                            ).setBackgroundTint(getResources().getColor(R.color.success))
                            .show();

                            SharedPreferences sPrefs = getActivity().getSharedPreferences(
                                "dapps_201",
                                Context.MODE_PRIVATE
                            );

                            //Guardamos en las preferencias el token del usuario
                            //tambien guardamos el id encriptado del usuario
                            SharedPreferences.Editor sPrefsEditor = sPrefs.edit();
                            sPrefsEditor.putString("uat", token);
                            sPrefsEditor.putString(
                                "uai",
                                Helper.MD5Hash(objUsuario.getString("idusuario"))
                            );
                            //Guardamos el id del usuario son encriptar
                            sPrefsEditor.putString(
                                    "idusuario",
                                    objUsuario.getString("idusuario")
                            );
                            sPrefsEditor.commit();
                        }

                        else {
                            Snackbar.make(
                                view,
                                objRespuesta.getString("mensaje"),
                                Snackbar.LENGTH_INDEFINITE
                            ).setBackgroundTint(getResources().getColor(R.color.danger))
                            .setActionTextColor(getResources().getColor(R.color.light))
                            .setAction("Corregir", SnackView -> {
                                binding.tietPassword.setText("");
                                binding.tietUsuario.requestFocus();
                            })
                            .show();
                        }
                    }

                    catch(Exception e) {
                        Snackbar.make(
                            view,
                            "Ocurrió un error",
                            Snackbar.LENGTH_INDEFINITE
                        ).setBackgroundTint(getResources().getColor(R.color.danger))
                        .setActionTextColor(getResources().getColor(R.color.light))
                        .setAction("Continuar", SnackView -> {
                            binding.tietUsuario.setText("");
                            binding.tietPassword.setText("");
                            binding.tietUsuario.requestFocus();
                        })
                        .show();
                    }

                    finally {
                        progress.hide();
                    }
                },
                error -> {
                    Toast.makeText(
                        getActivity(),
                        error.toString(),
                        Toast.LENGTH_SHORT
                    ).show();
                    progress.hide();
                }
            )
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> parametros = new HashMap<>();
                    parametros.put(
                        "correo",
                        binding.tietUsuario.getText().toString()
                    );

                    parametros.put(
                        "contra",
                        Helper.MD5Hash(
                                binding.tietPassword.getText().toString()
                        )
                    );

                    return parametros;
                }
            };
            conServ.add(petServ);
        });

        return binding.getRoot();
    }

}