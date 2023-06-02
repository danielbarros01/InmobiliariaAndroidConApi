package com.example.tpo1;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpo1.modelo.Propietario;
import com.example.tpo1.request.ApiClientRetrofit;
import com.example.tpo1.request.SpToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;
    //private ApiClient api = ApiClient.getApi();
    private ApiClientRetrofit.EndPointsInmobiliaria api;
    private MutableLiveData<Propietario> propietario = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        api = ApiClientRetrofit.getApi();
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getUsuario() {
        if (propietario == null) {
            propietario = new MutableLiveData<>();
        }

        return propietario;
    }

    public void setUsuario() {
        String token = SpToken.leerToken(context);

        Call<Propietario> call = api.obtenerPerfil(token);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        propietario.setValue(response.body());
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
}
