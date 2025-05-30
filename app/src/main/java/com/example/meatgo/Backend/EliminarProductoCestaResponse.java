package com.example.meatgo.Backend;

public class EliminarProductoCestaResponse {
    private String status;
    private String mensaje;
    private double cantidadRestauradaAStock;
    private double stockActual;
    private double reservadoCestasActual;

    public String getStatus() {
        return status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public double getCantidadRestauradaAStock() {
        return cantidadRestauradaAStock;
    }

    public double getStockActual() {
        return stockActual;
    }

    public double getReservadoCestasActual() {
        return reservadoCestasActual;
    }
}
