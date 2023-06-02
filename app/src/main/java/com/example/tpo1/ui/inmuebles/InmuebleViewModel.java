package com.example.tpo1.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.request.ApiClientRetrofit;
import com.example.tpo1.request.SpToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {
    private Context context;
    private ApiClientRetrofit.EndPointsInmobiliaria api;
    private String token;
    private MutableLiveData<Inmueble> inmueble;

    public InmuebleViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
        token = SpToken.leerToken(context);
        api = ApiClientRetrofit.getApi();
        this.inmueble = new MutableLiveData<>();
    }

    public LiveData<Inmueble> getInmueble() {
        if (inmueble == null) {
            inmueble = new MutableLiveData<>();
        }
        return inmueble;
    }

    public void traerInmueble(Bundle bundle) {
        Inmueble i = (Inmueble) bundle.getSerializable("inmueble");
        inmueble.setValue(i);
    }

    public void actualizarEstadoInmueble(Inmueble i) {
        Call<Void> call = api.actualizarEstadoInmueble(token, i.getId(), i.isEstado());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    inmueble.setValue(i);
                } else {
                    Log.d("Error al obtener respuesta", response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Algo salio mal al intentar actualizar el estado del inmueble", Toast.LENGTH_LONG).show();
                Log.d("salida", t.getMessage());
            }
        });

    }
}