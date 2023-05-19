package com.example.tpo1.ui.perfil;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Propietario;
import com.example.tpo1.request.ApiClient;

public class PerfilViewModel extends ViewModel {
    private ApiClient api = ApiClient.getApi();
    private MutableLiveData<Boolean> status;
    private MutableLiveData<String> textBtn;
    private MutableLiveData<Propietario> propietario;


    public void editarOGuardar(String btnText, Propietario p){
        if(btnText.equals("Editar")){
            textBtn.setValue("Guardar");
            status.setValue(true);
        }else{
            textBtn.setValue("Editar");
            status.setValue(false);
            api.actualizarPerfil(p);
        }
    }

    public void setUsuario(){
        propietario.setValue(api.obtenerUsuarioActual());
    }

    public MutableLiveData<Boolean> getStatus() {
        if(status == null){
            status = new MutableLiveData<>();
        }

        return status;
    }

    public MutableLiveData<String> getTextBtn() {
        if(textBtn == null){
            textBtn = new MutableLiveData<>();
        }

        return textBtn;
    }

    public MutableLiveData<Propietario> getPropietario() {
        if(propietario == null){
            propietario = new MutableLiveData<>();
        }

        return propietario;
    }
}