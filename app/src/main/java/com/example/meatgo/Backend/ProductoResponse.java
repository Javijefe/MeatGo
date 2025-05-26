package com.example.meatgo.Backend;

import com.example.meatgo.Models.Producto;
import java.util.List;

public class ProductoResponse {

    private List<Producto> productos;

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}