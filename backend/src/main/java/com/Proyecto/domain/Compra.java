package com.Proyecto.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Compra {
    @Id
    private Long id;
    private LocalDateTime fecha;
    private float precioTotal;

    @OneToMany(mappedBy = "compra")
    private List<Libros> libros;
}
