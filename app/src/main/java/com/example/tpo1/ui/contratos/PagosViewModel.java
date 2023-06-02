package com.example.tpo1.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpo1.modelo.Contrato;
import com.example.tpo1.modelo.Pago;
import com.example.tpo1.request.ApiClientRetrofit;
import com.example.tpo1.request.SpToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {
    private Context context;
    private String token;
    private ApiClientRetrofit.EndPointsInmobiliaria api;
    private MutableLiveData<List<Pago>> pagos;

    public PagosViewModel(@NonNull Application application) {
        super(application);

        this.context = application.getApplicationContext();
        api = ApiClientRetrofit.getApi();
        pagos = new MutableLiveData<>();
        token = SpToken.leerToken(context);
    }

    public LiveData<List<Pago>> getPagos() {
        if (pagos == null) {
            pagos = new MutableLiveData<>();
        }
        return pagos;
    }

    public void setPagos(Contrato c) {
        Call<List<Pago>> call = api.obtenerPagos(token, c.getIdContrato());

        call.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        pagos.setValue(response.body());
                    }
                }else{
                    Log.d("Error al obtener respuesta", response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Pago>> call, Throwable t) {
                Toast.makeText(context, "Algo salio mal al intentar obtener los pagos del contrato", Toast.LENGTH_LONG).show();
                Log.d("salida", t.getMessage());
            }
        });
    }
}