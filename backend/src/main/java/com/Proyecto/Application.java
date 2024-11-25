package com.Proyecto;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.Proyecto.Services.CompraService;
import com.Proyecto.Services.LibrosService;
import com.Proyecto.Services.UsuarioService;
import com.Proyecto.Services.VentaService;
import com.Proyecto.domain.Categoria;
import com.Proyecto.domain.Compra;
import com.Proyecto.domain.Libros;
import com.Proyecto.domain.Usuario;
import com.Proyecto.domain.Venta;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner initUsuarioData(UsuarioService usuarioService) {
        return args -> {
            Usuario usuario1 = new Usuario(49333225L, "Carlos", "carlos@gmail.com", "654109671", "av.Carlos III", null,
                    null);
            Usuario usuario2 = new Usuario(49333195L, "Ana", "ana@gmail.com", "654141031", "av. Nacional", null, null);
            usuarioService.añadir(usuario1);
            usuarioService.añadir(usuario2);
        };
    }

    @Bean
    CommandLineRunner initLibrosData(LibrosService librosService, CompraService compraService) {
        return args -> {
            Compra compra1 = new Compra(18L, LocalDateTime.of(2023, 9, 19, 11, 30), 60f, null);
            Compra compra2 = new Compra(1L, LocalDateTime.of(2024, 11, 9, 20, 00), 5f, null);
            compraService.añadir(compra1);
            compraService.añadir(compra2);

            Libros libro1 = new Libros(3L, "Lolita", "Vladimir Nabokov", Categoria.NOVELA, "castellano", 14.95f,
                    compra1);
            Libros libro2 = new Libros(5L, "La metamorfosis", "Frank Kafka", Categoria.FANTASÍA, "castellano", 9.99f,
                    compra2);
            librosService.añadir(libro1);
            librosService.añadir(libro2);
        };
    }

    @Bean
    CommandLineRunner initCompraData(CompraService compraService) {
        return args -> {
            Compra compra1 = new Compra(18L, LocalDateTime.of(2023, 9, 19, 11, 30), 60f, null);
            Compra compra2 = new Compra(1L, LocalDateTime.of(2024, 11, 9, 20, 00), 5f, null);
            compraService.añadir(compra1);
            compraService.añadir(compra2);
        };
    }

    @Bean
    CommandLineRunner initVentaData(VentaService ventaService, UsuarioService usuarioService,
            LibrosService librosService) {
        return args -> {
            Venta venta1 = new Venta(null, LocalDateTime.of(2024, 6, 1, 21, 47), 100f, null, null);
            Venta venta2 = new Venta(null, LocalDateTime.of(2024, 2, 12, 13, 20), 35f, null, null);

            ventaService.añadir(venta1);
            ventaService.añadir(venta2);

            Usuario usuario1 = usuarioService.obtenerPorId(49333225L);
            Usuario usuario2 = usuarioService.obtenerPorId(49333195L);
            Libros libro1 = librosService.obtenerPorId(3L);
            Libros libro2 = librosService.obtenerPorId(5L);

            venta1.setUsuario(usuario1);
            venta1.setLibro(libro1);

            venta2.setUsuario(usuario2);
            venta2.setLibro(libro2);

            ventaService.añadir(venta1);
            ventaService.añadir(venta2);
        };
    }
}
