package com.example.meatgo.Models;

import com.google.gson.annotations.SerializedName;

public class TipoCarne {

    @SerializedName("id_tipos_carne")
    private int idTiposCarne;

    @SerializedName("nombre")
    private String nombre;

    public TipoCarne(int idTiposCarne, String nombre) {
        this.idTiposCarne = idTiposCarne;
        this.nombre = nombre;
    }

    public int getIdTiposCarne() {
        return idTiposCarne;
    }

    public void setIdTiposCarne(int idTiposCarne) {
        this.idTiposCarne = idTiposCarne;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoCarne{" +
                "idTiposCarne=" + idTiposCarne +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
