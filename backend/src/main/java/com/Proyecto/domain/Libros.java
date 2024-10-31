package com.Proyecto.domain;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Libros{
    @Id
    private Long id;
    private String titulo;
    private  String autor;
    private String categoria;
    private String idioma;
    private float precio;
}