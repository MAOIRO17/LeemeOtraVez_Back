package com.Proyecto.domain;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import jakarta.persistence.Entity;
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
}