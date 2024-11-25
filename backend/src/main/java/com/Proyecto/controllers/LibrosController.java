package com.Proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Proyecto.Services.LibrosService;
import com.Proyecto.domain.Libros;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")
public class LibrosController {

    @Autowired
    private LibrosService librosService;

    // Obtener todos los libros
    @GetMapping
    public List<Libros> getAllLibros() {
        return librosService.obtenerTodos();
    }

    // Crear un nuevo libro
    @PostMapping
    public Libros createLibro(@Valid @RequestBody Libros librosForm) {
        return librosService.añadir(librosForm);
    }

    // Obtener un libro por su ID
    @GetMapping("/{id}")
    public Libros getLibroById(@PathVariable long id) {
        return librosService.obtenerPorId(id);
    }

    // Editar un libro 
    @PutMapping("/{id}")
    public Libros updateLibro(@PathVariable long id, @Valid @RequestBody Libros librosForm) {
        Libros libroExistente = librosService.obtenerPorId(id);
        if (libroExistente != null) {
            librosForm.setId(id);
            return librosService.editar(librosForm);
        }
        throw new RuntimeException("Libro no encontrado");
    }

    // Eliminar un libro
    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable long id) {
        librosService.borrar(id);
    }
}
