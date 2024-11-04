package com.Proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Proyecto.Services.VentaService;
import com.Proyecto.domain.Venta;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/venta")
public class VentaControler {
    @Autowired
    private VentaService ventaService;

    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaVenta", ventaService.obtenerTodos());
        return "venta/listView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("ventaForm", new Venta());
        return "venta/newFormView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Venta ventaForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/venta/new";
        ventaService.añadir(ventaForm);
        return "redirect:/venta/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Venta venta = ventaService.obtenerPorId(id);
        if (venta != null) {
            model.addAttribute("ventaForm", venta);
            return "venta/editFormView";
        } else
            return "redirect:/venta/list";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid Venta ventaForm,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            ventaService.editar(ventaForm);
        return "redirect:/venta/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        ventaService.borrar(id);
        return "redirect:/venta/list";
    }
}