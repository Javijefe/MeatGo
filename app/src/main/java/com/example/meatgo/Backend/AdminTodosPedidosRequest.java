package com.example.meatgo.Backend;

public class AdminTodosPedidosRequest {

    private String token;

    public AdminTodosPedidosRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

