package com.example.hitomagicwater_maria.Clases;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class User {
    @Id
    private String pf_nik;
    private String password;
    private boolean activo;
    private String permiso;
    private String categoria;
    private String nombre;
    private String apellidos;
    private String email;
    private String tlf;

    public User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "pf_nik='" + pf_nik + '\'' +
                ", password='" + password + '\'' +
                ", activo=" + activo +
                ", permiso='" + permiso + '\'' +
                ", categoria='" + categoria + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", tlf='" + tlf + '\'' +
                '}';
    }

    public Object getPermiso() {
        return permiso;
    }
}