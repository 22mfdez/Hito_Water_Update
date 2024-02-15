package com.example.hitomagicwater_maria.Clases;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data // getters and setters
@Table(name = "tarea")
public class Task {
    @Id
    private Long id_tarea;
    private String descripcion;
    private LocalDate inicioprevisto;
    private LocalDate finprevisto;
    private LocalDate inicioreal;
    private LocalDate finreal;
    private String estado;
    @ManyToOne
    @JoinColumn(name = "pfNik") // foreign key
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_proyecto")
    private Project project;
   ;
    // getters and setters
}