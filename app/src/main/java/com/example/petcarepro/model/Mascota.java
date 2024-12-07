package com.example.petcarepro.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Mascota implements Serializable {
    // Implementamos Serializable para poder pasar objetos de esta clase entre activities
    private String nombre;
    private String especie;
    private String raza;
    private String fechaNacimiento;

    private int idUsuario;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mascota(String nombre, String especie, String raza, String fechaNacimiento, int idUsuario) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.idUsuario = idUsuario;
    }


    public Mascota(Mascota mascota, int idMascota) {
        this.nombre = mascota.getNombre();
        this.especie = mascota.getEspecie();
        this.raza = mascota.getRaza();
        this.fechaNacimiento = mascota.getFechaNacimiento();
        this.idUsuario = mascota.getIdUsuario();

        this.id = idMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    @NonNull
    @Override
    public String toString() {
        return "Mascota{" +
                "nombre='" + nombre + '\'' +
                ", especie='" + especie + '\'' +
                ", raza='" + raza + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", idUsuario=" + idUsuario +
                ", id=" + id +
                '}';
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
