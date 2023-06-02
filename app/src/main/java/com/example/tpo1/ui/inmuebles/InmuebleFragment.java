package com.example.tpo1.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.tpo1.Config;
import com.example.tpo1.databinding.FragmentInmuebleBinding;
import com.example.tpo1.modelo.Inmueble;

public class InmuebleFragment extends Fragment {

    private InmuebleViewModel vm;
    private FragmentInmuebleBinding binding;

    private ImageView imagenInmueble;
    private TextView tvPrecio, tvDireccion, tvCodigo, tvTipo, tvAmbientes, tvUso;
    private CheckBox cbEstado;

    private Inmueble inmueble;

    public InmuebleFragment() {
    }


    public static InmuebleFragment newInstance() {
        return new InmuebleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(InmuebleViewModel.class);

        iniciarWidgets();
        observers();
        listeners();

        vm.traerInmueble(getArguments()); //Serializable "inmueble" que traigo del adapter
    }

    private void observers(){
        vm.getInmueble().observe(this, new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble i) {
                inmueble = i;

                Glide.with(getContext())
                        .load(Config.API_URL + inmueble.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imagenInmueble);

                tvPrecio.setText("$" + inmueble.getPrecio());
                tvDireccion.setText(inmueble.getDireccion());
                tvCodigo.setText(String.valueOf(inmueble.getId()));
                tvTipo.setText(inmueble.getTipo().getTipo());
                tvAmbientes.setText(String.valueOf(inmueble.getAmbientes()));
                tvUso.setText(inmueble.getUso());
                cbEstado.setChecked(inmueble.isEstado());
            }
        });
    }

    private void listeners(){
        cbEstado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                inmueble.setEstado(isChecked);
                vm.actualizarEstadoInmueble(inmueble);
            }
        });
    }
    private void iniciarWidgets(){
        imagenInmueble = binding.ivInmuebleDetalles;
        tvPrecio = binding.tvPrecioInmuebleDetalles;
        tvDireccion = binding.tvDireccionInmuebleDetalles;
        tvCodigo = binding.tvCodigoInmueble;
        tvTipo = binding.tvTipoInmueble;
        tvAmbientes = binding.tvAmbientesInmueble;
        tvUso = binding.tvUsoInmueble;
        cbEstado = binding.checkBoxDisponibilidadInmueble;
    }

}