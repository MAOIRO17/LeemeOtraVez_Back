package com.Proyecto.domain;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    private Long dni;
    private String Nombre;
    private String email;
    private String tlf;
    private String direccion;

    @OneToMany(mappedBy = "usuario")
    private List<Venta> ventas;
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioLibro> usuarioLibros;

}
