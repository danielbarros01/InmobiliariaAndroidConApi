package com.example.tpo1.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tpo1.MainActivity;
import com.example.tpo1.modelo.Propietario;
import com.example.tpo1.request.ApiClient;

public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;
    private ApiClient api;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        api = ApiClient.getApi();
        this.context = application.getApplicationContext();
    }

    public void login(String email, String password) {
        try {
            Propietario p = api.login(email, password);

            if (p != null) {
                Toast.makeText(context, "Iniciando sesion", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("login", true);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Propietario incorrecto o inexistente", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error al iniciar sesion" + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("Error Login", e.getMessage());
        }
    }
}
