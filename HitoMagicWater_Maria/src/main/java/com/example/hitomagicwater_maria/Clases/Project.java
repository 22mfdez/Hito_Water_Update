package com.example.hitomagicwater_maria.Clases;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Table(name = "proyecto")
@Entity
@Data
public class Project {
    @Id

    private Long idproyecto;
    private String nombre;
    private String descripcion;
    private String zona;
    private LocalDate fecha;
    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
    // getters and setters
}
