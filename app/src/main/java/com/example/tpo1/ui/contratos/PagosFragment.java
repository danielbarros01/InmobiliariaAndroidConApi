package com.example.tpo1.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tpo1.databinding.FragmentPagosBinding;
import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.modelo.Pago;

import java.util.List;


public class PagosFragment extends Fragment {

    private FragmentPagosBinding binding;
    private PagosViewModel vm;

    public static PagosFragment newInstance() {
        return new PagosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPagosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(PagosViewModel.class);


        Bundle bundle = getArguments();
        Contrato c= (Contrato) bundle.getSerializable("contrato");
        vm.setPagos(c);

        vm.getPagos().observe(this, new Observer<List<Pago>>() {
            @Override
            public void onChanged(List<Pago> pagos) {
                RecyclerView rv = binding.rvPagos;
                GridLayoutManager grilla = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);
                PagosAdapter adapter = new PagosAdapter(getContext(), pagos, getLayoutInflater());
                rv.setAdapter(adapter);
            }
        });
    }

}