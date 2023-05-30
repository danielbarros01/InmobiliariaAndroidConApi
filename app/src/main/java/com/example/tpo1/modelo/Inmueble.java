package com.example.tpo1.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Inmueble implements Serializable {

    private int id;
    private String direccion;
    private String usoNombre;
    private TipoInmueble tipoInmueble;
    private int cantidadAmbientes;
    private double precio;
    private Propietario propietario;
    private boolean disponible;
    private String urlImagen;

    public Inmueble(int id, String direccion, String usoNombre, TipoInmueble tipo, int ambientes, double precio, Propietario propietario, boolean estado, String imagen) {
        this.id = id;
        this.direccion = direccion;
        this.usoNombre = usoNombre;
        this.tipoInmueble = tipo;
        this.cantidadAmbientes = ambientes;
        this.precio = precio;
        this.propietario = propietario;
        this.disponible = estado;
        this.urlImagen = imagen;
    }
    public Inmueble() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUso() {
        return usoNombre;
    }

    public void setUso(String usoNombre) {
        this.usoNombre = usoNombre;
    }

    public TipoInmueble getTipo() {
        return tipoInmueble;
    }

    public void setTipo(TipoInmueble tipo) {
        this.tipoInmueble = tipo;
    }

    public int getAmbientes() {
        return cantidadAmbientes;
    }

    public void setAmbientes(int ambientes) {
        this.cantidadAmbientes = ambientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public boolean isEstado() {
        return disponible;
    }

    public void setEstado(boolean estado) {
        this.disponible = estado;
    }

    public String getImagen() {
        return urlImagen;
    }

    public void setImagen(String imagen) {
        this.urlImagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inmueble inmueble = (Inmueble) o;
        return id == inmueble.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
