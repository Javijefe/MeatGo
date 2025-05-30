package com.example.meatgo.Backend;

public class VerDetallesPedidoRequest {

    private String token;
    private int idPedido;

    public VerDetallesPedidoRequest(String token, int idPedido) {
        this.token = token;
        this.idPedido = idPedido;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}
