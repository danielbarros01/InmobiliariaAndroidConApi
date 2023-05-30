package com.example.tpo1.modelo;

public class PasswordsPropietario {
    public String contraseñaActual;
    private String contraseñaNueva;

    public PasswordsPropietario(String contraseñaActual, String contraseñaNueva) {
        this.contraseñaActual = contraseñaActual;
        this.contraseñaNueva = contraseñaNueva;
    }

    public String getContraseñaActual() {
        return contraseñaActual;
    }

    public void setContraseñaActual(String contraseñaActual) {
        this.contraseñaActual = contraseñaActual;
    }

    public String getContraseñaNueva() {
        return contraseñaNueva;
    }

    public void setContraseñaNueva(String contraseñaNueva) {
        this.contraseñaNueva = contraseñaNueva;
    }
}
