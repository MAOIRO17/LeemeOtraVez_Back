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
public class Usuario{
    @Id
    private Long dni;
    private String Nombre;
    private String email;
    private String tlf;
    private String direccion;
}