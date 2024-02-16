package com.example.hitomagicwater_maria.repositorios;

import com.example.hitomagicwater_maria.Clases.Task; // Import the User class
import com.example.hitomagicwater_maria.Clases.User;
import org.springframework.data.jpa.repository.JpaRepository; // importamos el jpa repository para poder usar los metodos de crud
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioTarea extends JpaRepository<Task, String> {
     List<Task> findByUser(User login);
}