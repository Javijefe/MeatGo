package com.example.meatgo.Backend;

public class MisPedidosRequest {
    private String token;

    public MisPedidosRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}