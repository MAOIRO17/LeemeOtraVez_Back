package com.Proyecto.domain;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Venta {
    @Id
    private Long id;
    private LocalDateTime fecha;
    private float precioTotal;

    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Libros libro;
}
