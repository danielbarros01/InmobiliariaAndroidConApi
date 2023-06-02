package com.example.tpo1.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.modelo.Inquilino;
import com.example.tpo1.request.ApiClient;
import com.example.tpo1.request.ApiClientRetrofit;
import com.example.tpo1.request.SpToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private Context context;
    private ApiClientRetrofit.EndPointsInmobiliaria api;
    private String token;
    private MutableLiveData<Inquilino> inquilino;

    public InquilinoViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
        inquilino = new MutableLiveData<>();
        api = ApiClientRetrofit.getApi();
        token = SpToken.leerToken(context);
    }


    public MutableLiveData<Inquilino> getInquilino() {
        if(inquilino == null){
            inquilino = new MutableLiveData<>();
        }
        return inquilino;
    }

    public void traerInquilino(Contrato c){
        Call<Inquilino> call = api.obtenerInquilinoDeContrato(token, c.getIdContrato());

        call.enqueue(new Callback<Inquilino>() {
            @Override
            public void onResponse(Call<Inquilino> call, Response<Inquilino> response) {
                if(response.isSuccessful()) {
                    if(response.body() != null){
                        inquilino.setValue(response.body());
                    }
                }else{
                    Log.d("Error al obtener respuesta en obtener inquilino", response.message());
                }
            }

            @Override
            public void onFailure(Call<Inquilino> call, Throwable t) {
                Toast.makeText(context, "Algo salio mal al intentar obtener el inquilino", Toast.LENGTH_LONG).show();
                Log.d("salida", t.getMessage());
            }
        });
    }
}