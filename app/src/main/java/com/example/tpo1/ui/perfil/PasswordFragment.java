package com.example.tpo1.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tpo1.R;
import com.example.tpo1.databinding.FragmentPasswordBinding;
import com.example.tpo1.databinding.FragmentPerfilBinding;
import com.example.tpo1.modelo.PasswordsPropietario;
import com.example.tpo1.ui.login.LoginActivity;

public class PasswordFragment extends Fragment {

    private FragmentPasswordBinding binding;
    private PasswordViewModel vm;
    private EditText passwordActual, passwordNew, passwordNewRepetida;
    private Button button;


    public static PasswordFragment newInstance() {
        return new PasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(PasswordViewModel.class);

        iniciarComponentes();
        listenerButton();
        observers();
    }

    private void listenerButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contraseñaActual = passwordActual.getText().toString();
                String contraseñaNueva = passwordNew.getText().toString();
                String contraseñaRepetida = passwordNewRepetida.getText().toString();

                vm.cambiarContraseña(contraseñaActual, contraseñaNueva, contraseñaRepetida);
            }
        });
    }

    private void observers(){
        vm.getFinishActivityEvent().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                // Cierra todas las actividades actuales
                getActivity().finishAffinity();

                // Inicia la actividad de inicio de sesión
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void iniciarComponentes(){
        passwordActual = binding.etPassActual;
        passwordNew = binding.etNuevaPass;
        passwordNewRepetida = binding.etPassRepetida;
        button = binding.btnPassEdit;
    }
}