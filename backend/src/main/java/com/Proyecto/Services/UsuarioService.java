package com.Proyecto.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Proyecto.domain.Usuario;
import com.Proyecto.repositorios.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new UsuarioNotFoundException("Usuario con id " + id + " no encontrado");
        }
    }

    public Usuario añadir(Usuario usuario) {
        if (usuarioRepository.existsById(usuario.getDni())) {
            throw new UsuarioAlreadyExistsException("El usuario con el DNI " + usuario.getDni() + " ya existe.");
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario editar(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getDni())) {
            throw new UsuarioNotFoundException("Usuario con id " + usuario.getDni() + " no encontrado");
        }
        return usuarioRepository.save(usuario);
    }

    public void borrar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNotFoundException("Usuario con id " + id + " no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
