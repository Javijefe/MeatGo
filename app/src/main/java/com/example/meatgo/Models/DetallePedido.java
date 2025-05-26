package com.example.meatgo.Models;

import com.google.gson.annotations.SerializedName;

public class DetallePedido {

    @SerializedName("id_detalle_pedido")
    private int idDetallePedido;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("pedido_id")
    private int pedidoId;

    @SerializedName("producto_id")
    private int productoId;

    @SerializedName("cantidad")
    private double cantidad;

    @SerializedName("precio")
    private double precio;

    @SerializedName("subtotal")
    private double subtotal;

    public DetallePedido(int idDetallePedido, String fecha, int pedidoId, int productoId,
                         double cantidad, double precio, double subtotal) {
        this.idDetallePedido = idDetallePedido;
        this.fecha = fecha;
        this.pedidoId = pedidoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precio = precio;
        this.subtotal = subtotal;
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "idDetallePedido=" + idDetallePedido +
                ", fecha='" + fecha + '\'' +
                ", pedidoId=" + pedidoId +
                ", productoId=" + productoId +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", subtotal=" + subtotal +
                '}';
    }
}
