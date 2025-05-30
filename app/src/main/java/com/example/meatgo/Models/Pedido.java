package com.example.meatgo.Models;

import com.google.gson.annotations.SerializedName;

public class Pedido {

    @SerializedName("idPedido")
    private int idPedidos;

    @SerializedName("idUsuario")
    private int usuarioId;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("estado")
    private String estado;

    @SerializedName("total")
    private double total;

    public Pedido(int idPedidos, int usuarioId, String fecha, String estado, double total) {
        this.idPedidos = idPedidos;
        this.usuarioId = usuarioId;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    public int getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(int idPedidos) {
        this.idPedidos = idPedidos;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedidos=" + idPedidos +
                ", usuarioId=" + usuarioId +
                ", fecha='" + fecha + '\'' +
                ", estado='" + estado + '\'' +
                ", total=" + total +
                '}';
    }
}
