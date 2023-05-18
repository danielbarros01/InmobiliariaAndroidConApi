package com.example.tpo1;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpo1.modelo.Propietario;
import com.example.tpo1.request.ApiClient;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient api = ApiClient.getApi();
    private MutableLiveData<Propietario> propietario= new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getUsuario(){
        if(propietario == null){
            propietario = new MutableLiveData<>();
        }

        return propietario;
    }

    public void setUsuario(){
        propietario.setValue(api.obtenerUsuarioActual());
    }
}
