package com.example.meatgo.Models;

import com.google.gson.annotations.SerializedName;

public class Usuario {

    @SerializedName("id_usuarios")
    private int idUsuarios;

    @SerializedName("email")
    private String email;

    @SerializedName("contraseña")
    private String contraseña;

    @SerializedName("creado_en")
    private String creadoEn;

    @SerializedName("rol_id")
    private int rolId;

    public Usuario(int idUsuarios, String email, String contraseña, String creadoEn, int rolId) {
        this.idUsuarios = idUsuarios;
        this.email = email;
        this.contraseña = contraseña;
        this.creadoEn = creadoEn;
        this.rolId = rolId;
    }

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(String creadoEn) {
        this.creadoEn = creadoEn;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuarios=" + idUsuarios +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", creadoEn='" + creadoEn + '\'' +
                ", rolId=" + rolId +
                '}';
    }
}
