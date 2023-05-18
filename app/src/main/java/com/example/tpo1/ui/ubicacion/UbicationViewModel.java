package com.example.tpo1.ui.ubicacion;

import androidx.lifecycle.ViewModel;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UbicationViewModel extends ViewModel {
    private static final LatLng INMOBILIARIA = new LatLng(-33.280576, -66.332482);

    public void obtenerUbicacion(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE); //Tipo de mapa
        googleMap.addMarker(new MarkerOptions().position(INMOBILIARIA).title("Inmobiliaria La Punta")); //marcador

        MarkerOptions markerOptions = new MarkerOptions()
                .position(INMOBILIARIA)
                .title("Inmobiliaria La Punta")
                .snippet("Calle Agregada 178"); // Agrega el nombre del cartel como snippet

        googleMap.addMarker(markerOptions); // Agrega el marcador al mapa

        //Hacer mas dinamico el mapa
        CameraPosition camPos =
                new CameraPosition.Builder()
                        .target(INMOBILIARIA)
                        .zoom(19)
                        .bearing(0) //ángulo de rotación de la cámara alrededor de su eje vertical en sentido horario, medida en grados.
                        .tilt(10) //se establece en 70, lo que representa el ángulo de inclinación de la cámara en relación con la superficie del mapa.
                        .build();

        CameraUpdate update = CameraUpdateFactory.newCameraPosition(camPos);

        //Hace la animacion
        googleMap.animateCamera(update);
    }
}