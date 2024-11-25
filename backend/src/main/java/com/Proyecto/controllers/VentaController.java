package com.Proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.Proyecto.Services.VentaService;
import com.Proyecto.domain.Venta;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Obtener todas las ventas
    @GetMapping
    public List<Venta> getAllVentas() {
        return ventaService.obtenerTodos();
    }

    // Crear una nueva venta
    @PostMapping
    public Venta createVenta(@Valid @RequestBody Venta ventaForm) {
        return ventaService.añadir(ventaForm);
    }

    // Obtener una venta por su ID
    @GetMapping("/{id}")
    public Venta getVentaById(@PathVariable long id) {
        Venta venta = ventaService.obtenerPorId(id);
        if (venta != null) {
            return venta;
        } else {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
    }

    // Editar una venta
    @PutMapping("/{id}")
    public Venta updateVenta(@PathVariable long id, @Valid @RequestBody Venta ventaForm) {
        Venta ventaExistente = ventaService.obtenerPorId(id);
        if (ventaExistente != null) {
            ventaForm.setId(id); // Asegura que el ID coincida
            return ventaService.editar(ventaForm);
        } else {
            throw new RuntimeException("Venta no encontrada con ID: " + id);
        }
    }

    // Eliminar una venta
    @DeleteMapping("/{id}")
    public void deleteVenta(@PathVariable long id) {
        ventaService.borrar(id);
    }
}
