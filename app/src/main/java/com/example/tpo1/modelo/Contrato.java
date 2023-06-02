package com.example.tpo1.modelo;

import java.io.Serializable;
import java.util.Objects;

public class Contrato implements Serializable {

    private int id;
    private String desde;
    private String hasta;
    private double monto;
    private Inquilino inquilino;
    private Inmueble inmueble;

    public Contrato() {}
    public Contrato(int idContrato, String fechaInicio, String fechaFin, double montoAlquiler, Inquilino inquilino, Inmueble inmueble) {
        this.id = idContrato;
        this.desde = fechaInicio;
        this.hasta = fechaFin;
        this.monto = montoAlquiler;
        this.inquilino = inquilino;
        this.inmueble = inmueble;
    }

    public int getIdContrato() {
        return id;
    }

    public void setIdContrato(int idContrato) {
        this.id = idContrato;
    }

    public String getFechaInicio() {
        return desde;
    }

    public void setFechaInicio(String fechaInicio) {
        this.desde = fechaInicio;
    }

    public String getFechaFin() {
        return hasta;
    }

    public void setFechaFin(String fechaFin) {
        this.hasta = fechaFin;
    }

    public double getMontoAlquiler() {
        return monto;
    }

    public void setMontoAlquiler(double montoAlquiler) {
        this.monto = montoAlquiler;
    }


    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contrato contrato = (Contrato) o;
        return id == contrato.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
