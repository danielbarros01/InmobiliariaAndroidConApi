package com.example.tpo1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.tpo1.ui.login.LoginActivity;

public class Salir extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle("Cierre de sesion")
                .setMessage("Esta seguro que desea cerrar sesion?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (getActivity() != null) {
                            // Cierra todas las actividades actuales
                            getActivity().finishAffinity();

                            // Inicia la actividad de inicio de sesión
                            Intent intent = new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Cerrar el diálogo y volver al menú principal
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }
}
