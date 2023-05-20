package com.example.tpo1.ui.contratos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.modelo.Inquilino;
import com.example.tpo1.request.ApiClient;

public class ContratoViewModel extends ViewModel {
    private MutableLiveData<Contrato> contrato;
    private ApiClient api;

    public ContratoViewModel() {
        contrato = new MutableLiveData<>();
        api = ApiClient.getApi();
    }

    public MutableLiveData<Contrato> getContrato() {
        if(contrato == null){
            contrato = new MutableLiveData<>();
        }
        return contrato;
    }

    public void traerContrato(Inmueble in){
        contrato.setValue(api.obtenerContratoVigente(in));
    }
}