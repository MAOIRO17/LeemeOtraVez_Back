package com.Proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Proyecto.Services.CompraService;
import com.Proyecto.domain.Compra;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    // Obtener todas las compras
    @GetMapping
    public List<Compra> getAllCompras() {
        return compraService.obtenerTodos();
    }

    // Crear una nueva compra
    @PostMapping
    public Compra createCompra(@Valid @RequestBody Compra compraForm) {
        return compraService.añadir(compraForm);
    }

    // Obtener una compra por su ID
    @GetMapping("/{id}")
    public Compra getCompraById(@PathVariable long id) {
        Compra compra = compraService.obtenerPorId(id);
        if (compra != null) {
            return compra;
        } else {
            throw new RuntimeException("Compra no encontrada con ID: " + id);
        }
    }

    // Editar una compra
    @PutMapping("/{id}")
    public Compra updateCompra(@PathVariable long id, @Valid @RequestBody Compra compraForm) {
        Compra compraExistente = compraService.obtenerPorId(id);
        if (compraExistente != null) {
            compraForm.setId(id); // Asegura que el ID coincida
            return compraService.editar(compraForm);
        } else {
            throw new RuntimeException("Compra no encontrada con ID: " + id);
        }
    }

    // Eliminar una compra
    @DeleteMapping("/{id}")
    public void deleteCompra(@PathVariable long id) {
        compraService.borrar(id);
    }
}
