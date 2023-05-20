package com.example.tpo1.ui.contratos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.modelo.Pago;
import com.example.tpo1.request.ApiClient;

import java.util.List;

public class PagosViewModel extends ViewModel {
    private MutableLiveData<List<Pago>> pagos;
    private ApiClient api = ApiClient.getApi();

    public PagosViewModel() {
        pagos = new MutableLiveData<>();
    }

    public LiveData<List<Pago>> getPagos() {
        if (pagos == null) {
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }

    public void setPagos(Contrato c) {
        pagos.setValue(api.obtenerPagos(c));
    }
}