package com.Proyecto.Services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.Proyecto.domain.Venta;

@Service
public class VentaService {
    private List<Venta> repositorio = new ArrayList<>();

    public List<Venta> obtenerTodos() {
        return repositorio;
    }

    public Venta obtenerPorId(long id) {
        for (Venta venta : repositorio)
            if (venta.getId() == id)
                return venta;
        return null; // Lanza excepcion si no encuentra la venta
    }

    public Venta añadir(Venta venta) {
        if (repositorio.contains(venta))
            return null; // Muestra la venta ya existente
        repositorio.add(venta);
        return venta; // Devolverá nada, o boolean, etc.
    }

    public Venta editar(Venta venta) {
        int pos = repositorio.indexOf(venta);
        if (pos == -1)
            return null; // Lanza excepcion si no encuentra la venta
        repositorio.set(pos, venta); // Si lo encuentra, lo sustituye
        return venta;
    }

    public void borrar(Long id) {
        Venta venta = this.obtenerPorId(id);
        if (venta != null) {
            repositorio.remove(venta); // Devolverá boolean
        }
    }
}