package com.example.meatgo.Backend;

public class MisPedidosAcabadosRequest {
    private String token;

    public MisPedidosAcabadosRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}