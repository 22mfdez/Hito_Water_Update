package com.example.hitomagicwater_maria.repositorios;

import com.example.hitomagicwater_maria.Clases.Project; // Import the User class
import org.springframework.data.jpa.repository.JpaRepository; // importamos el jpa repository para poder usar los metodos de crud
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProyecto extends JpaRepository<Project, String> {
}
