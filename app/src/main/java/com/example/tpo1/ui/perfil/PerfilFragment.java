package com.example.tpo1.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.tpo1.databinding.ActivityLoginBinding;
import com.example.tpo1.databinding.FragmentPerfilBinding;
import com.example.tpo1.modelo.Propietario;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;
    private EditText etDni, etNombre, etApellido, etEmail, etPass, etTelefono;
    private Button button;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(PerfilViewModel.class);

        iniciarComponentes();
        listenerButton();

        observers();

        vm.setUsuario();
    }

    private void observers() {
        vm.getPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario propietario) {
                etDni.setText(propietario.getDni().toString());
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etEmail.setText(propietario.getEmail());
                etPass.setText(propietario.getContraseña());
                etTelefono.setText(propietario.getTelefono());
            }
        });

        vm.getStatus().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean b) {
                etDni.setEnabled(b);
                etNombre.setEnabled(b);
                etApellido.setEnabled(b);
                etEmail.setEnabled(b);
                etPass.setEnabled(b);
                etTelefono.setEnabled(b);
            }
        });

        vm.getTextBtn().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                button.setText(s);
            }
        });
    }

    private void iniciarComponentes(){
        etDni = binding.etDni;
        etNombre = binding.etNombre;
        etApellido = binding.etApellido;
        etEmail = binding.etEmailProfile;
        etPass = binding.etPassProfile;
        etTelefono = binding.etTelefono;
        button = binding.btnPerfil;
    }

    private void listenerButton(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoBtn = button.getText().toString();
                vm.editarOGuardar(textoBtn, crearPropietario());
            }
        });
    }

    private Propietario crearPropietario() {
        Propietario propietario = new Propietario();
        propietario.setDni(Long.parseLong(etDni.getText().toString()));
        propietario.setNombre(etNombre.getText().toString());
        propietario.setApellido(etApellido.getText().toString());
        propietario.setEmail(etEmail.getText().toString());
        propietario.setContraseña(etPass.getText().toString());
        propietario.setTelefono(etTelefono.getText().toString());

        return propietario;
    }
}