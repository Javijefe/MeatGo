package com.example.meatgo.Backend;


import java.util.List;

public class MisPedidosResponse {
    private Integer id_pedido;
    private String estado;
    private List<CestaResponse.ProductoCesta> productos;
    private Float total;

    public MisPedidosResponse(Integer id_pedido, String estado, List<CestaResponse.ProductoCesta> productos, Float total) {
        this.id_pedido = id_pedido;
        this.estado = estado;
        this.productos = productos;
        this.total = total;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<CestaResponse.ProductoCesta> getProductos() {
        return productos;
    }

    public void setProductos(List<CestaResponse.ProductoCesta> productos) {
        this.productos = productos;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}