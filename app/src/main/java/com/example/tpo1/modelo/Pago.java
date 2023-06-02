package com.example.tpo1.modelo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pago implements Serializable {

    private int numeroPago;
    private int contratoId;

    @SerializedName("contrato")
    private Contrato contrato;
    private String fecha;

    public Pago() {}

    public Pago(int numeroPago, int contratoId, Contrato contrato, String fecha) {
        this.numeroPago = numeroPago;
        this.contratoId = contratoId;
        this.contrato = contrato;
        this.fecha = fecha;
    }

    public int getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(int numeroPago) {
        this.numeroPago = numeroPago;
    }

    public int getContratoId() {
        return contratoId;
    }

    public void setContratoId(int contratoId) {
        this.contratoId = contratoId;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public String getFecha() {
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = inputFormat.parse(fecha);

            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return fecha;
        }
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
