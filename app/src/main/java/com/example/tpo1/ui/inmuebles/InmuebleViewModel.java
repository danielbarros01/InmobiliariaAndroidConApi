package com.example.tpo1.ui.inmuebles;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.request.ApiClient;

public class InmuebleViewModel extends ViewModel {
    private MutableLiveData<Inmueble> inmueble;
    private ApiClient api = ApiClient.getApi();
    public InmuebleViewModel() {
        this.inmueble = new MutableLiveData<>();
    }

    public LiveData<Inmueble> getInmueble() {
        if(inmueble == null){
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void traerInmueble(Bundle bundle){
        Inmueble i = (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(i);
    }

    public void actualizarEstadoInmueble(Inmueble i){
        api.actualizarInmueble(i);
        inmueble.setValue(i);
    }
}