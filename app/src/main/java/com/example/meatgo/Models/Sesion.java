package com.example.meatgo.Models;

import com.google.gson.annotations.SerializedName;

public class Sesion {

    @SerializedName("id_sesiones")
    private int idSesiones;

    @SerializedName("token")
    private String token;

    @SerializedName("ultima_actividad")
    private String ultimaActividad;

    public Sesion(int idSesiones, String token, String ultimaActividad) {
        this.idSesiones = idSesiones;
        this.token = token;
        this.ultimaActividad = ultimaActividad;
    }

    public int getIdSesiones() {
        return idSesiones;
    }

    public void setIdSesiones(int idSesiones) {
        this.idSesiones = idSesiones;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUltimaActividad() {
        return ultimaActividad;
    }

    public void setUltimaActividad(String ultimaActividad) {
        this.ultimaActividad = ultimaActividad;
    }

    @Override
    public String toString() {
        return "Sesion{" +
                "idSesiones=" + idSesiones +
                ", token='" + token + '\'' +
                ", ultimaActividad='" + ultimaActividad + '\'' +
                '}';
    }
}
