package com.Proyecto.Services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.Proyecto.domain.Usuario;

@Service
public class UsuarioService {
    private List<Usuario> repositorio = new ArrayList<>();

    public List<Usuario> obtenerTodos() {
        return repositorio;
    }

    public Usuario obtenerPorId(long id) {
        for (Usuario usuario : repositorio)
            if (usuario.getDni() == id)
                return usuario;
        return null; // Lanza excepcion si no encuentra el usuario
    }

    public Usuario añadir(Usuario usuario) {
        if (repositorio.contains(usuario))
            return null; // Muestra el usuario ya existente
        repositorio.add(usuario);
        return usuario; // Devolverá nada, o boolean, etc.
    }

    public Usuario editar(Usuario usuario) {
        int pos = repositorio.indexOf(usuario);
        if (pos == -1)
            return null; // Lanza excepcion si no encuentra el usuario
        repositorio.set(pos, usuario); // Si lo encuentra, lo sustituye
        return usuario;
    }

    public void borrar(Long id) {
        Usuario usuario = this.obtenerPorId(id);
        if (usuario != null) {
            repositorio.remove(usuario); // Devolverá boolean
        }
    }
}