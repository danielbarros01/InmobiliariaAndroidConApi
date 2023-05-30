package com.example.tpo1.request;

import com.example.tpo1.modelo.Inmueble;
import com.example.tpo1.modelo.PasswordsPropietario;
import com.example.tpo1.modelo.Propietario;
import com.example.tpo1.modelo.LoginView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public class ApiClientRetrofit {

    private static final String PATH = "http://192.168.0.103:5029/api/";

    private static EndPointsInmobiliaria endPointsInmobiliaria;

    public static EndPointsInmobiliaria getApi() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        endPointsInmobiliaria = retrofit.create(EndPointsInmobiliaria.class);

        return endPointsInmobiliaria;
    }


    public interface EndPointsInmobiliaria{
        @POST("propietarios/login")
        Call<String> login(@Body LoginView user);

        @GET("propietarios/perfil")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @PUT("propietarios/perfil")
        Call<Propietario> editarPerfil(@Header("Authorization") String token,@Body Propietario propietario);

        @PUT("propietarios/perfil/password")
        Call<Void> editarPassword(@Header("Authorization") String token, @Body PasswordsPropietario passwords);

        @GET("inmuebles/de")
        Call<List<Inmueble>> obtenerInmuebles(@Header("Authorization") String token);
    }
}
