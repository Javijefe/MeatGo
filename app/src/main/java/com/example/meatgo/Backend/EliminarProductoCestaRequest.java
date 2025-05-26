package com.example.meatgo.Backend;

public class EliminarProductoCestaRequest {
    private String token;
    private int id_detalle;

    public EliminarProductoCestaRequest(String token, int id_detalle) {
        this.token = token;
        this.id_detalle = id_detalle;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }
}