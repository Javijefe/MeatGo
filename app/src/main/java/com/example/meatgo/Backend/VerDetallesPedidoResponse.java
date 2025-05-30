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

        @SerializedName("productoId")
        private int productoId;

        @SerializedName("cantidad")
        private int cantidad;

        @SerializedName("precio")
        private double precio;

        @SerializedName("subtotal")
        private double subtotal;

        public DetallePedido(int productoId, int cantidad, double precio, double subtotal) {
            this.productoId = productoId;
            this.cantidad = cantidad;
            this.precio = precio;
            this.subtotal = subtotal;
        }

        public int getProductoId() {
            return productoId;
        }

        public void setProductoId(int productoId) {
            this.productoId = productoId;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
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
    }
}
