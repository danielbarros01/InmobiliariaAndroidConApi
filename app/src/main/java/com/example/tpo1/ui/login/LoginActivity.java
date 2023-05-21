package com.example.tpo1.ui.login;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tpo1.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {

    private ActivityLoginBinding binding;
    private LoginActivityViewModel vm;
    private EditText email, password;
    private Button btnLogin;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private static final int SHAKE_THRESHOLD = 500; // Umbral de agitación
    private long lastShakeTime; // Tiempo del último evento de agitación,evitar desencadenar eventos de agitación continuamente en un corto período de tiempo.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        iniciarComponentes();
        listenerBtnLogin();

        solicitarPermisos();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE); //devuelve una instancia del servicio del sensor
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //devuelve el acelerómetro del dispositivo.
    }

    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL); //registramos el escuchador del sensor, permite comenzar a recibir eventos del acelerómetro.
    }

    private void iniciarComponentes() {
        email = binding.etEmail;
        password = binding.etPassword;
        btnLogin = binding.btnLogin;
    }

    private void listenerBtnLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.login(
                        email.getText().toString(),
                        password.getText().toString()
                );
            }
        });
    }

    public void solicitarPermisos() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && (checkSelfPermission(CALL_PHONE) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissions(new String[]{CALL_PHONE}, 1000);
        }
    }

    //para detectar la agitación del dispositivo.
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //valores de aceleración en los ejes X, Y y Z
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Calcula la aceleración resultante
            float acceleration = (float) Math.sqrt(x * x + y * y + z * z);

            // Comprueba si se ha agitado lo suficiente
            long currentTime = System.currentTimeMillis();

           /*Comparamos la aceleración resultante con el umbral de agitación definido.
            Si se supera el umbral y ha pasado suficiente tiempo desde el último evento de agitación, llamamos al método llamar*/
            if (acceleration > SHAKE_THRESHOLD && currentTime - lastShakeTime > 1000) {
                lastShakeTime = currentTime;
                // Realiza la llamada telefónica
                llamar();
            }
        }
    }

    private void llamar() {
        // Código para realizar la llamada telefónica
        int permissionCheck = ContextCompat.checkSelfPermission(LoginActivity.this, CALL_PHONE);
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.d("Agite", "Permission");
            Toast.makeText(LoginActivity.this, "Llama", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + "3544562721"));
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Permiso para llamadas denegado", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, 1);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}