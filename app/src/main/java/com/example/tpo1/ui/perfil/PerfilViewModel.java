package com.example.tpo1.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Propietario;
import com.example.tpo1.request.ApiClient;
import com.example.tpo1.request.ApiClientRetrofit;
import com.example.tpo1.request.SpToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private ApiClientRetrofit.EndPointsInmobiliaria api;
    private MutableLiveData<Boolean> status;
    private MutableLiveData<String> textBtn;
    private MutableLiveData<Propietario> propietario;
    private String token;
    private String emailPropietario;

    public PerfilViewModel(@NonNull Application application) {
        super(application);

        api = ApiClientRetrofit.getApi();
        this.context = application.getApplicationContext();
        token = SpToken.leerToken(context);
    }


    public void editarOGuardar(String btnText, Propietario p){
        if(btnText.equals("Editar")){
            textBtn.setValue("Guardar");
            status.setValue(true);
        }else{
            textBtn.setValue("Editar");
            status.setValue(false);

            p.setEmail(emailPropietario); //CREO QUE NO HACE FALTA
            Call<Propietario> call = api.editarPerfil(token, p);

            call.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if(response.isSuccessful()) {
                        if(response.body() != null) {
                            propietario.setValue(response.body());
                            Toast.makeText(context, "Propietario actualizado", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Log.d("Error al obtener respuesta", response.message());
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable t) {
                    Toast.makeText(context, "Algo salio mal al intentar actualizar el Propietario", Toast.LENGTH_LONG).show();
                    Log.d("salida", t.getMessage());
                }
            });
        }
    }

    public void setUsuario(){
        Call<Propietario> call = api.obtenerPerfil(token);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        propietario.setValue(response.body());
                        emailPropietario = response.body().getEmail();
                    }
                }else{
                    Log.d("Error al obtener respuesta", response.message());
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Toast.makeText(context, "Algo salio mal, vuelve a intentar más tarde", Toast.LENGTH_LONG).show();
                Log.d("salida", t.getMessage());
            }
        });
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