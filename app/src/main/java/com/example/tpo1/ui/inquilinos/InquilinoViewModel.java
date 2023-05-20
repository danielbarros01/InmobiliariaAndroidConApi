package com.example.tpo1.ui.inquilinos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.modelo.Inquilino;
import com.example.tpo1.request.ApiClient;

public class InquilinoViewModel extends ViewModel {
    private MutableLiveData<Inquilino> inquilino;
    private ApiClient api;

    public InquilinoViewModel() {
        inquilino = new MutableLiveData<>();
        api = ApiClient.getApi();
    }

    public MutableLiveData<Inquilino> getInquilino() {
        if(inquilino == null){
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }

    public void traerInquilino(Inmueble in){
        inquilino.setValue(api.obtenerInquilino(in));
    }
}