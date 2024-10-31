package com.Proyecto.Services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.Proyecto.domain.Compra;

@Service
public class CompraService {
    private List<Compra> repositorio = new ArrayList<>();

    public List<Compra> obtenerTodos() {
        return repositorio;
    }

    public Compra obtenerPorId(long id) {
        for (Compra compra : repositorio)
            if (compra.getId() == id)
                return compra;
        return null; // Lanza excepcion si no encuentra la compra
    }

    public Compra añadir(Compra compra) {
        if (repositorio.contains(compra))
            return null; // Muestra la compra ya existente
        repositorio.add(compra);
        return compra; // Devolverá nada, o boolean, etc.
    }

    public Compra editar(Compra compra) {
        int pos = repositorio.indexOf(compra);
        if (pos == -1)
            return null; // Lanza excepcion si no encuentra la compra
        repositorio.set(pos, compra); // Si lo encuentra, lo sustituye
        return compra;
    }

    public void borrar(Long id) {
        Compra compra = this.obtenerPorId(id);
        if (compra != null) {
            repositorio.remove(compra); // Devolverá boolean
        }
    }
}