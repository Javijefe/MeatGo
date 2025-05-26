package com.example.meatgo.Models;

import com.google.gson.annotations.SerializedName;

public class Producto {

    @SerializedName("id_productos")
    private int idProductos;

    @SerializedName("nombre")
    private String nombre;

    @SerializedName("tipo_id")
    private int tipoId;

    @SerializedName("precio")
    private double precio;

    @SerializedName("stock")
    private double stock;

    @SerializedName("reservado_cestas")
    private double reservadoCestas;

    @SerializedName("en_preparacion")
    private double enPreparacion;

    @SerializedName("foto")
    private String foto;

    public Producto(int idProductos, String nombre, int tipoId, double precio, double stock,
                    double reservadoCestas, double enPreparacion, String foto) {
        this.idProductos = idProductos;
        this.nombre = nombre;
        this.tipoId = tipoId;
        this.precio = precio;
        this.stock = stock;
        this.reservadoCestas = reservadoCestas;
        this.enPreparacion = enPreparacion;
        this.foto = foto;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getReservadoCestas() {
        return reservadoCestas;
    }

    public void setReservadoCestas(double reservadoCestas) {
        this.reservadoCestas = reservadoCestas;
    }

    public double getEnPreparacion() {
        return enPreparacion;
    }

    public void setEnPreparacion(double enPreparacion) {
        this.enPreparacion = enPreparacion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProductos=" + idProductos +
                ", nombre='" + nombre + '\'' +
                ", tipoId=" + tipoId +
                ", precio=" + precio +
                ", stock=" + stock +
                ", reservadoCestas=" + reservadoCestas +
                ", enPreparacion=" + enPreparacion +
                ", foto='" + foto + '\'' +
                '}';
    }
}
