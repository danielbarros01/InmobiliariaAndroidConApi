package com.example.tpo1.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.request.ApiClientRetrofit;
import com.example.tpo1.request.SpToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Inmueble>> inmuebles;
    private ApiClientRetrofit.EndPointsInmobiliaria api;
    private String token;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);

        inmuebles = new MutableLiveData<>();
        api = ApiClientRetrofit.getApi();
        this.context = application.getApplicationContext();
        token = SpToken.leerToken(context);
    }


    public LiveData<List<Inmueble>> getInmuebles() {
        return inmuebles;
    }

    public void setInmuebles(){
        Call<List<Inmueble>> call = api.obtenerInmuebles(token);

        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null){
                        inmuebles.setValue(response.body());
                    }
                }else{
                    Log.d("Error al obtener respuesta", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Toast.makeText(context, "Algo salio mal al intentar obtener los inmuebles", Toast.LENGTH_LONG).show();
                Log.d("salida", t.getMessage());
            }
        });
    }
}