package com.example.tpo1.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tpo1.MainActivity;
import com.example.tpo1.modelo.LoginView;
import com.example.tpo1.request.ApiClientRetrofit;
import com.example.tpo1.request.SpToken;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClientRetrofit.EndPointsInmobiliaria api;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        api = ApiClientRetrofit.getApi();
        this.context = application.getApplicationContext();
    }

    public void login(String email, String password) {
        try {
            LoginView usuario = new LoginView(email, password);

            if (usuario != null) {
                Call<String> call = api.login(usuario);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()) {
                            if(response.body() != null) {
                                SpToken.guardarToken(context, response.body());

                                Intent intent = new Intent(context, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("login", true);
                                context.startActivity(intent);
                            }
                        }else{
                            Toast.makeText(context, "Algun dato es incorrecto", Toast.LENGTH_LONG).show();
                            Log.d("Salida", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context, "Algo salio mal, vuelve a intentar más tarde", Toast.LENGTH_LONG).show();
                        Log.d("salida", t.getMessage());
                    }
                });
            } else {
                Toast.makeText(context, "Propietario incorrecto o inexistente", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al iniciar sesion" + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("Error Login", e.getMessage());
        }
    }
}
