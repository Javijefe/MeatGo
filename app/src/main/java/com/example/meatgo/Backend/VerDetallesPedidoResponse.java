package com.example.meatgo.Backend;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VerDetallesPedidoResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("detalles")
    private List<DetallePedido> detalles;

    public VerDetallesPedidoResponse(String status, List<DetallePedido> detalles) {
        this.status = status;
        this.detalles = detalles;
    }

    public String getStatus() {
        return status;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    public static class DetallePedido {

        @SerializedName("producto_id")
        private int producto_id;

        @SerializedName("cantidad")
        private int cantidad;

        @SerializedName("precio")
        private double precio;

        @SerializedName("subtotal")
        private double subtotal;

        public DetallePedido(int producto_id, int cantidad, double precio, double subtotal) {
            this.producto_id = producto_id;
            this.cantidad = cantidad;
            this.precio = precio;
            this.subtotal = subtotal;
        }

        public int getProducto_id() {
            return producto_id;
        }

        public int getCantidad() {
            return cantidad;
        }

        public double getPrecio() {
            return precio;
        }

        public double getSubtotal() {
            return subtotal;
        }

        public void setProducto_id(int producto_id) {
            this.producto_id = producto_id;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public void setSubtotal(double subtotal) {
            this.subtotal = subtotal;
        }
    }
}