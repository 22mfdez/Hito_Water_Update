package com.example.hitomagicwater_maria.repositorios;

import com.example.hitomagicwater_maria.Clases.User; // Import the User class
import org.springframework.data.jpa.repository.JpaRepository; // importamos el jpa repository para poder usar los metodos de crud
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUsuario extends JpaRepository<User, String> {




}
