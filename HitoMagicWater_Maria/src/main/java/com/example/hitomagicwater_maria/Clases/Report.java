package com.example.hitomagicwater_maria.Clases;


import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class Report { // Clase que representa el reporte de tareas de un usuario, es decir el total de tareas, las tareas completadas y las tareas por usuario
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int totalTasks;
    private int completedTasks;
    private int tasksPerUser;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}