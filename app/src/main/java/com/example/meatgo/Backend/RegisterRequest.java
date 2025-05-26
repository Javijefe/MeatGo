package com.example.meatgo.Backend;

public class RegisterRequest {
    private String email;
    private String contraseña;
    private int rol_id;

    public RegisterRequest(String email, String contraseña, int rol_id) {
        this.email = email;
        this.contraseña = contraseña;
        this.rol_id = rol_id;
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

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }
}