package com.example.petcarepro.model;

public class Usuario {
    // id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT, password TEXT
    private String nombre;
    private String email;
    private String password;

    private int idUsuario;

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(String nombre, String email, String password, int idUsuario) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
