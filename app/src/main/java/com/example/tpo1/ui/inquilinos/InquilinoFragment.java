package com.example.tpo1.ui.inquilinos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tpo1.R;
import com.example.tpo1.databinding.FragmentInquilinoBinding;
import com.example.tpo1.databinding.FragmentInquilinosBinding;
import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.modelo.Inquilino;

public class InquilinoFragment extends Fragment {

    private FragmentInquilinoBinding binding;
    private InquilinoViewModel vm;
    private TextView codigo, nombreYApellido, dni, mail, telefono;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(InquilinoViewModel.class);

        iniciarWidgets();
        observers();

        Bundle bundle = getArguments();
        Contrato c= (Contrato) bundle.getSerializable("contrato");
        vm.traerInquilino(c);
    }

    private void iniciarWidgets(){
        codigo = binding.tvCDigoInquilino;
        nombreYApellido = binding.tvNombreApellidoInquilino;
        dni = binding.tvDniInquilino;
        mail = binding.tvEmailInquilino;
        telefono = binding.tvTelefonoInquilino;
    }

    private void observers(){
        vm.getInquilino().observe(this, new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino i) {
                codigo.setText(String.valueOf(i.getIdInquilino()));
                nombreYApellido.setText(i.getNombre() + " " + i.getApellido());
                dni.setText(String.valueOf(i.getDNI()));
                mail.setText(i.getEmail());
                telefono.setText(i.getTelefono());
            }
        });
    }

}