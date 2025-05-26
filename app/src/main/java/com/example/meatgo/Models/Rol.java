package com.example.meatgo.Models;

import com.google.gson.annotations.SerializedName;

public class Rol {

    @SerializedName("id_roles")
    private int idRoles;

    @SerializedName("nombre")
    private String nombre;

    public Rol(int idRoles, String nombre) {
        this.idRoles = idRoles;
        this.nombre = nombre;
    }

    public int getIdRoles() {
        return idRoles;
    }

    public void setIdRoles(int idRoles) {
        this.idRoles = idRoles;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "idRoles=" + idRoles +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
