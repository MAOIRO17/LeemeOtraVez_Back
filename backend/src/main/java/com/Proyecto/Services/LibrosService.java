package com.Proyecto.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Proyecto.domain.Libros;

@Service
public class LibrosService {
    private List<Libros> repositorio = new ArrayList<>();

    public List<Libros> obtenerTodos() {
        return repositorio;
    }

    public Libros obtenerPorId(long id) {
        for (Libros libros : repositorio)
            if (libros.getId() == id)
                return libros;
        return null; // Lanza excepcion si no encuentra el libro
    }

    public Libros añadir(Libros libros) {
        if (repositorio.contains(libros))
            return null; // Muestra el libro ya existente
        repositorio.add(libros);
        return libros; // Devolverá nada, o boolean, etc.
    }

    public Libros editar(Libros libros) {
        int pos = repositorio.indexOf(libros);
        if (pos == -1)
            return null; // Lanza excepcion si no encuentra el libro
        repositorio.set(pos, libros); // Si lo encuentra, lo sustituye
        return libros;
    }

    public void borrar(Long id) {
        Libros libros = this.obtenerPorId(id);
        if (libros != null) {
            repositorio.remove(libros); // Devolverá boolean
        }
    }
}