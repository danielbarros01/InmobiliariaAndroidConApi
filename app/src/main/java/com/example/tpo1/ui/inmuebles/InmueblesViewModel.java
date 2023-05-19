package com.example.tpo1.ui.inmuebles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.request.ApiClient;

import java.util.List;

public class InmueblesViewModel extends ViewModel {

    private MutableLiveData<List<Inmueble>> inmuebles;
    private ApiClient api = ApiClient.getApi();

    public InmueblesViewModel() {
        inmuebles = new MutableLiveData<>();
    }

    public LiveData<List<Inmueble>> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(){
        inmuebles.setValue(api.obtnerPropiedades());
    }
}