package com.example.meatgo.Backend;

import com.google.gson.annotations.SerializedName;

public class AumentarStockRequest {

    @SerializedName("productoId")
    private int producto_id;

    private double cantidad;
    private String token;

    public AumentarStockRequest(int producto_id, double cantidad, String token) {
        this.producto_id = producto_id;
        this.cantidad = cantidad;
        this.token = token;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}