package com.Proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Proyecto.Services.UsuarioService;
import com.Proyecto.domain.Usuario;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todos los usuarios
    @GetMapping
    public List<Usuario> getAllUsers() {
        return usuarioService.obtenerTodos();
    }

    // Crear un nuevo usuario
    @PostMapping
    public Usuario createUser(@Valid @RequestBody Usuario usuarioForm) {
        return usuarioService.añadir(usuarioForm);
    }

    // Obtener un usuario por su dni
    @GetMapping("/{id}")
    public Usuario getUserById(@PathVariable long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario != null) {
            return usuario;
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

    // Editar un usuario
    @PutMapping("/{id}")
    public Usuario updateUser(@PathVariable long id, @Valid @RequestBody Usuario usuarioForm) {
        Usuario usuarioExistente = usuarioService.obtenerPorId(id);
        if (usuarioExistente != null) {
            usuarioForm.setDni(id); 
            return usuarioService.editar(usuarioForm);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
    }

    // Eliminar un usuario
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        usuarioService.borrar(id);
    }
}
