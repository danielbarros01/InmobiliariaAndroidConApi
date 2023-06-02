package com.example.tpo1.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.request.ApiClient;
import com.example.tpo1.request.ApiClientRetrofit;
import com.example.tpo1.request.SpToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinosViewModel extends AndroidViewModel {
    private Context context;
    private String token;
    private ApiClientRetrofit.EndPointsInmobiliaria api;
    private MutableLiveData<List<Contrato>> contratosVigentes; //el contrato contiene al inmueble

    public InquilinosViewModel(@NonNull Application application) {
        super(application);

        this.context = application.getApplicationContext();
        api = ApiClientRetrofit.getApi();
        contratosVigentes = new MutableLiveData<>();
        token = SpToken.leerToken(context);
    }


    public LiveData<List<Contrato>> getInmuebles() {
        if(contratosVigentes == null){
            contratosVigentes = new MutableLiveData<>();
        }
        return contratosVigentes;
    }

    public void setInmuebles() {
        Call<List<Contrato>> call = api.obtenerContratosVigentes(token);

        call.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null) {
                        contratosVigentes.setValue(response.body());
                    }
                }else{
                    Log.d("Error al obtener respuesta", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Toast.makeText(context, "Algo salio mal al intentar obtener los inmuebles con contratos vigentes", Toast.LENGTH_LONG).show();
                Log.d("salida", t.getMessage());
            }
        });
    }
}