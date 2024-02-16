package com.example.hitomagicwater_maria.control;

import com.example.hitomagicwater_maria.repositorios.RepositorioTarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        } else {
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
                    List<Task> tasks = reTarea.findAll(); // obtengo las tareas del trabajador
                    mv.addObject("user", login); // las guardo en el modelo
                    mv.addObject("tasks", tasks); // las guardo en el modelo
                    mv.setViewName("trabajador"); // redirijo a la vista trabajador
                }
            }
        }
        return mv;
    }

    @RequestMapping("/tarea")
    public ModelAndView mostrarTareasUsuario(Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if (aut != null) {
            Optional<User> usuarioOptional = reUsuario.findById(aut.getName());
            if (usuarioOptional.isPresent()) {
                User login = usuarioOptional.get();
                List<Task> tasks = reTarea.findByUser(login);
                mv.addObject("tasks", tasks);
                mv.setViewName("tarea");
            }
        }
        return mv;
    }





    // Añadir tarea
    @RequestMapping(value = "/editarTarea/{nif}/{idtarea}", method = RequestMethod.GET)
    public ModelAndView editTaskForm(@PathVariable("nif") String nif, @PathVariable("idtarea") String idtarea, Authentication aut) {
        ModelAndView mv = new ModelAndView();
        if (aut != null) {
            Optional<User> usuarioOptional = reUsuario.findById(aut.getName());
            if (usuarioOptional.isPresent()) {
                User login = usuarioOptional.get();
                Optional<Task> taskOptional = reTarea.findById(idtarea);
                if (taskOptional.isPresent()) {
                    Task task = taskOptional.get();
                    if (task.getUser().equals(login)) {
                        mv.addObject("task", task);
                        mv.setViewName("editarTarea");
                    } else {
                        mv.setViewName("error");
                    }
                }
            }
        }
        return mv;
    }


    // Editar tarea
    @RequestMapping(value = "/editarTarea/{nif}/{idtarea}", method = RequestMethod.POST)
    public ModelAndView editTask(@PathVariable("nif") String nif, @PathVariable("idtarea") String idtarea, @ModelAttribute Task updatedTask, Authentication aut) {
        ModelAndView mv = new ModelAndView(); // creo un objeto de tipo ModelAndView
        if (aut != null) {
            Optional<User> usuarioOptional = reUsuario.findById(aut.getName()); // obtengo el usuario que ha iniciado sesion
            if (usuarioOptional.isPresent()) {
                User login = usuarioOptional.get();
                Optional<Task> taskOptional = reTarea.findById(idtarea);
                if (taskOptional.isPresent()) {
                    Task task = taskOptional.get();
                    if (task.getUser().equals(login)) {
                        task.setDescripcion(updatedTask.getDescripcion());
                        reTarea.save(task);
                        mv.setViewName("redirect:/tarea");
                    } else {
                        mv.setViewName("error");
                    }
                }
            }
        }
        return mv;
    }

    @RequestMapping(value = "/eliminarTarea/{nif}/{idtarea}", method = RequestMethod.GET)
    public ModelAndView deleteTask(@PathVariable("nif") String nif, @PathVariable("idtarea") String idtarea, Authentication aut) { // metodo para eliminar una tarea
        ModelAndView mv = new ModelAndView(); // creo un objeto de tipo ModelAndView
        if (aut != null) {
            Optional<User> usuarioOptional = reUsuario.findById(aut.getName());
            if (usuarioOptional.isPresent()) { // si el usuario existe
                User login = usuarioOptional.get(); // obtengo el usuario que ha iniciado sesion
                Optional<Task> taskOptional = reTarea.findById(idtarea); // obtengo la tarea
                if (taskOptional.isPresent()) { // si la tarea existe
                    Task task = taskOptional.get(); // la guardo en la variable task
                    if (task.getUser().equals(login)) { // si el usuario que ha iniciado sesion es el mismo que el usuario que ha creado la tarea
                        reTarea.delete(task); // la elimino
                        mv.setViewName("redirect:/tarea"); // redirijo a la vista tarea
                    } else {
                        mv.setViewName("error");
                    }
                }
            }
        }
        return mv;
    }

}





