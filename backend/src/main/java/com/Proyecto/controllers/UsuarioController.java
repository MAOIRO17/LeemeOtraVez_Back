package com.Proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Proyecto.Services.UsuarioService;
import com.Proyecto.domain.Usuario;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping({ "/", "/list" })
    public String showList(Model model) {
        model.addAttribute("listaUsuarios", usuarioService.obtenerTodos());
        return "usuario/listView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("usuarioForm", new Usuario());
        return "usuario/newFormView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid Usuario usuarioForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/user/new";
        usuarioService.añadir(usuarioForm);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        if (usuario != null) {
            model.addAttribute("usuarioForm", usuario);
            return "usuario/editFormView";
        } else
            return "redirect:/user/list";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid Usuario usuarioForm,
            BindingResult bindingResult) {
        if (!bindingResult.hasErrors())
            usuarioService.editar(usuarioForm);
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        usuarioService.borrar(id);
        return "redirect:/user/list";
    }
}