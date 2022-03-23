package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.databinding.FragmentCatalogoBinding;

public class CatalogoFragment extends Fragment {

    private FragmentCatalogoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCatalogoBinding.inflate(inflater, container, false);

        /*
        Ponemos en modo carga el swipeRefresh
         */
        binding.srlCategorias.post(() -> {
            cargaCategorias();
        });

        return binding.getRoot();
    }

    public void cargaCategorias() {
        binding.srlCategorias.setRefreshing(true);
    }
}