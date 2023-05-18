package com.example.tpo1.ui.ubicacion;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tpo1.R;
import com.example.tpo1.databinding.FragmentUbicationBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class UbicationFragment extends Fragment {
    private UbicationViewModel vm;

    public static UbicationFragment newInstance() {
        return new UbicationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ubication, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vm = new ViewModelProvider(this).get(UbicationViewModel.class);

        SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);//objeto que se encarga de gestionar el ciclo de vida del fragment
        smf.getMapAsync(new MapaActual()); //traeme el mapa cuando lo tengas
    }

    private class MapaActual implements OnMapReadyCallback {
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            vm.obtenerUbicacion(googleMap);
        }
    }
}