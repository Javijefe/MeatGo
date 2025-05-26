package com.example.meatgo.Backend;

import java.util.List;

public class CestaResponse {
    private List<ProductoCesta> productos;

    public List<ProductoCesta> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoCesta> productos) {
        this.productos = productos;
    }

    public static class ProductoCesta {
        private int id_detalle;
        private String producto;
        private double cantidad;
        private double subtotal;

        public int getId_detalle() {
            return id_detalle;
        }

        public void setId_detalle(int id_detalle) {
            this.id_detalle = id_detalle;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public double getCantidad() {
            return cantidad;
        }

        public void setCantidad(double cantidad) {
            this.cantidad = cantidad;
        }

        public double getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(double subtotal) {
            this.subtotal = subtotal;
        }
    }
}
