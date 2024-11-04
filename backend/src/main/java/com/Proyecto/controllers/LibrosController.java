package com.Proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Proyecto.Services.LibrosService;
import com.Proyecto.domain.Libros;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/libros")
public class LibrosController {
    @Autowired
    private LibrosService librosService;

    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaLibros", librosService.obtenerTodos());
        return "libros/listView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("librosForm", new Libros());
        return "libros/newFormView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Libros librosForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/libros/new";
        librosService.añadir(librosForm);
        return "redirect:/libros/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Libros libros = librosService.obtenerPorId(id);
        if (libros != null) {
            model.addAttribute("librosForm", libros);
            return "libros/editFormView";
        } else
            return "redirect:/libros/list";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid Libros librosForm,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            librosService.editar(librosForm);
        return "redirect:/libros/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        librosService.borrar(id);
        return "redirect:/libros/list";
    }
}