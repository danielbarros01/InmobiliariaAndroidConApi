package com.example.tpo1.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tpo1.R;
import com.example.tpo1.databinding.FragmentContratoBinding;
import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;

public class ContratoFragment extends Fragment {

    private FragmentContratoBinding binding;
    private ContratoViewModel vm;
    private TextView codigo, fechaInicio, fechaFinalizacion, monto, inquilino, inmueble;
    private Button btnVerPagos;

    public static ContratoFragment newInstance() {
        return new ContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(ContratoViewModel.class);

        iniciarWidgets();
        observers();

        Bundle bundle = getArguments();
        Inmueble i = (Inmueble) bundle.getSerializable("inmueble");
        vm.traerContrato(i);
    }

    private void iniciarWidgets() {
        codigo = binding.tvCodigoContrato;
        fechaInicio = binding.tvFechaInicioContrato;
        fechaFinalizacion = binding.tvFechaFinalizacionContrato;
        monto = binding.tvMontoAlquilerContrato;
        inquilino = binding.tvInquilinoContrato;
        inmueble = binding.tvInmuebleContrato;
        btnVerPagos = binding.btnVerPagos;
    }

    private void observers() {
        vm.getContrato().observe(this, new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato c) {
                codigo.setText(String.valueOf(c.getIdContrato()));
                fechaInicio.setText(c.getFechaInicio());
                fechaFinalizacion.setText(c.getFechaFin());
                monto.setText(String.valueOf(c.getMontoAlquiler()));
                inquilino.setText(c.getInquilino().getNombre() + " " + c.getInquilino().getApellido());
                inmueble.setText("Inmueble en " + c.getInmueble().getDireccion());

                btnVerPagos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("contrato", c);
                        Navigation.findNavController(view).navigate(R.id.pagosFragment, bundle);
                    }
                });
            }
        });
    }
}