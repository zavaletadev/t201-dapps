package dev.zavaletazea.uteq.dapps.t201.proyectofinal.pub.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dev.zavaletazea.uteq.dapps.t201.proyectofinal.R;
import dev.zavaletazea.uteq.dapps.t201.proyectofinal.databinding.FragmentRegistroBinding;

public class RegistroFragment extends Fragment {

    private FragmentRegistroBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistroBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(
                getActivity(),
                R.id.host_public_fragments
        );

        binding.mbLogin.setOnClickListener(view -> {
            navController.navigateUp();
            navController.navigate(R.id.LoginFragment);
        });

        return binding.getRoot();
    }
}