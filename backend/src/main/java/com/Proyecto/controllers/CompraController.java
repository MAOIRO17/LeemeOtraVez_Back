package com.Proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Proyecto.Services.CompraService;
import com.Proyecto.domain.Compra;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/compra")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaCompra", compraService.obtenerTodos());
        return "compra/listView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("compraForm", new Compra());
        return "compra/newFormView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Compra compraForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/compra/new";
        compraService.añadir(compraForm);
        return "redirect:/compra/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Compra compra = compraService.obtenerPorId(id);
        if (compra != null) {
            model.addAttribute("compraForm", compra);
            return "compra/editFormView";
        } else
            return "redirect:/compra/list";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid Compra compraForm,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            compraService.editar(compraForm);
        return "redirect:/compra/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        compraService.borrar(id);
        return "redirect:/compra/list";
    }
}