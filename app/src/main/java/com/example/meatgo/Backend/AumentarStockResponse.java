package com.example.meatgo.Backend;

public class AumentarStockResponse {
    private String status;
    private String mensaje;
    private double nuevo_stock;

    public AumentarStockResponse(String status, String mensaje, double nuevo_stock) {
        this.status = status;
        this.mensaje = mensaje;
        this.nuevo_stock = nuevo_stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public double getNuevo_stock() {
        return nuevo_stock;
    }

    public void setNuevo_stock(double nuevo_stock) {
        this.nuevo_stock = nuevo_stock;
    }
}