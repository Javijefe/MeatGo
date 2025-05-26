package com.example.meatgo.Backend;

public class AdminCambiarEstadoPedidoRequest {
    private String token;
    private int id_pedido;

    public AdminCambiarEstadoPedidoRequest(String token, int id_pedido) {
        this.token = token;
        this.id_pedido = id_pedido;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }
}


