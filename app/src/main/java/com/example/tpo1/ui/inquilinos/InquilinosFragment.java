package com.example.tpo1.ui.inquilinos;

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

import com.example.tpo1.R;
import com.example.tpo1.databinding.FragmentInmueblesBinding;
import com.example.tpo1.databinding.FragmentInquilinosBinding;
import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.ui.inmuebles.InmueblesAdapter;

import java.util.List;

public class InquilinosFragment extends Fragment {

    private FragmentInquilinosBinding binding;
    private InquilinosViewModel vm;

    public static InquilinosFragment newInstance() {
        return new InquilinosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInquilinosBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(InquilinosViewModel.class);
        vm.setInmuebles();

        vm.getInmuebles().observe(this, new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                RecyclerView rv = binding.rvInmueblesAlquilados;
                GridLayoutManager grilla = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
                rv.setLayoutManager(grilla);
                InquilinosAdapter adapter = new InquilinosAdapter(getContext(), contratos, getLayoutInflater());
                rv.setAdapter(adapter);
            }
        });
    }

}