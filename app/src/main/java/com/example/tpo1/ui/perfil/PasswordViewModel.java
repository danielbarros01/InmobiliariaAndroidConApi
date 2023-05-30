package com.example.tpo1.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tpo1.modelo.PasswordsPropietario;
import com.example.tpo1.modelo.Propietario;
import com.example.tpo1.request.ApiClientRetrofit;
import com.example.tpo1.request.SpToken;
import com.example.tpo1.ui.login.LoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordViewModel extends AndroidViewModel {

    private Context context;
    private ApiClientRetrofit.EndPointsInmobiliaria api;
    private String token;
    private MutableLiveData<Boolean> finishActivityEvent;

    public PasswordViewModel(@NonNull Application application) {
        super(application);

        api = ApiClientRetrofit.getApi();
        this.context = application.getApplicationContext();
        token = SpToken.leerToken(context);
        finishActivityEvent = new MutableLiveData<>();
    }

    public void cambiarContraseña(String contraseñaActual, String contraseñaNueva, String contraseñaNuevaRepetida) {
        if (contraseñaActual.isEmpty() || contraseñaNueva.isEmpty() || contraseñaNuevaRepetida.isEmpty()) {
            Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_LONG).show();
            return;
        }

        if (!contraseñaNueva.equals(contraseñaNuevaRepetida)) {
            Toast.makeText(context, "Las nuevas contraseñas no coinciden", Toast.LENGTH_LONG).show();
            return;
        }

        PasswordsPropietario passwordsPropietario = new PasswordsPropietario(contraseñaActual, contraseñaNueva);
        Call<Void> call = api.editarPassword(token, passwordsPropietario);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Contraseña Cambiada", Toast.LENGTH_LONG).show();
                    finishActivityEvent.setValue(true);
                    SpToken.eliminarToken(context);
                } else {
                    Toast.makeText(context, "Error al cambiar la contraseña", Toast.LENGTH_LONG).show();
                    Log.d("Error al cambiar la contraseña", response.toString());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context, "Algo salio mal al intentar actualizar la contraseña", Toast.LENGTH_LONG).show();
                Log.d("salida", t.getMessage());
            }
        });
    }

    public LiveData<Boolean> getFinishActivityEvent() {
        return finishActivityEvent;
    }

}