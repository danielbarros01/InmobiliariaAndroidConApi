package com.example.tpo1.ui.contratos;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Contrato;

public class ContratoViewModel extends ViewModel {
    private MutableLiveData<Contrato> contrato;

    public ContratoViewModel() {
        contrato = new MutableLiveData<>();
    }

    public MutableLiveData<Contrato> getContrato() {
        if(contrato == null){
            contrato = new MutableLiveData<>();
        }
        return contrato;
    }

    public void setContrato(Contrato c){
        contrato.setValue(c);
    }
}