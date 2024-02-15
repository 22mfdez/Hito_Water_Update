package com.example.hitomagicwater_maria.control;

import com.example.hitomagicwater_maria.repositorios.RepositorioTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.Authentication;

import com.example.hitomagicwater_maria.Clases.User;
import com.example.hitomagicwater_maria.Clases.Task;
import com.example.hitomagicwater_maria.repositorios.RepositorioUsuario;

import java.util.List;
import java.util.Optional;


@Controller
public class Controlador {
    @Autowired
    private RepositorioUsuario reUsuario; // Repositorio de usuarios
    @Autowired
    private RepositorioTarea reTarea; // Repositorio de tareas

    private Task task; // Representa la tarea que ha iniciado sesión.

    // Representa al usuario que ha iniciado sesión.
    private User login;

    @RequestMapping("/")
    public ModelAndView peticionRaiz(Authentication aut) {
        ModelAndView mv = new ModelAndView();

        if (aut == null) {
            mv.addObject("user", null);
        }
        else {
            Optional<User> usuarioOptional = reUsuario.findById(aut.getName());
            if (usuarioOptional.isPresent()) {
                login = usuarioOptional.get();
                mv.addObject("user", login);
                mv.addObject("username", login.getNombre()); // Agregar el nombre del usuario al modelo
                System.out.println(login);
            }
        }

        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView peticionSesion() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }


    @RequestMapping("/trabajador") // sirve para que el trabajador pueda ver sus tareas
    public ModelAndView trabajadorView(Authentication aut) {
        ModelAndView mv = new ModelAndView();

        if (aut != null) {
            Optional<User> usuarioOptional = reUsuario.findById(aut.getName()); // obtengo el usuario que ha iniciado sesion
            if (usuarioOptional.isPresent()) { // si el usuario existe
                User login = usuarioOptional.get(); // lo guardo en la variable login
                if (login.getPermiso().equals("trabajador")) { // si el usuario es un trabajador
                    List<Task> tasks = reTarea.findByUser(login); // obtengo las tareas del trabajador
                    mv.addObject("tasks", tasks); // las guardo en el modelo
                    mv.setViewName("trabajador"); // redirijo a la vista trabajador
                }
            }
        }
        return mv;
    }

    @RequestMapping("/tarea")
    public ModelAndView mostrarTareasUsuario() {
        ModelAndView mv = new ModelAndView();
        System.out.println(("Requerimiento /tarea"));
        // Obtener las tareas del usuario actual
        List<Task> tareas = reTarea.findByUser(login);

        // Agregar las tareas al modelo para pasarlas a la vista
        mv.addObject("tareas", tareas);

        mv.setViewName("tarea");
        return mv;
    }

    @RequestMapping("/prueba")
    public ModelAndView pruebaRequest() {
        ModelAndView mv = new ModelAndView();
        List <Task> tareas = reTarea.findByUser(login);
        System.out.println(tareas);
        mv.setViewName("tarea");
        return mv;
    }
}





