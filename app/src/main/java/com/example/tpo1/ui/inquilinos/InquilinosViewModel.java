package com.example.tpo1.ui.inquilinos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.request.ApiClient;

import java.util.List;

public class InquilinosViewModel extends ViewModel {
    private MutableLiveData<List<Inmueble>> inmueblesAlquilados;
    private ApiClient api = ApiClient.getApi();

    public InquilinosViewModel() {
        inmueblesAlquilados = new MutableLiveData<>();
    }

    public LiveData<List<Inmueble>> getInmuebles() {
        if(inmueblesAlquilados == null){
            inmueblesAlquilados = new MutableLiveData<>();
        }
        return inmueblesAlquilados;
    }

    public void setInmuebles() {
        inmueblesAlquilados.setValue(api.obtenerPropiedadesAlquiladas());
    }
}