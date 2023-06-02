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

import com.example.tpo1.databinding.FragmentContratosBinding;
import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.ui.inquilinos.InquilinosAdapter;

import java.util.List;

public class ContratosFragment extends Fragment {

    private FragmentContratosBinding binding;
    private ContratosViewModel vm;

    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentContratosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(ContratosViewModel.class);
        vm.setInmuebles();

        vm.getInmuebles().observe(this, new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                RecyclerView rv = binding.rvContratos;
                GridLayoutManager grilla = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);
                ContratosAdapter adapter = new ContratosAdapter(getContext(), contratos, getLayoutInflater());
                rv.setAdapter(adapter);
            }
        });
    }


}