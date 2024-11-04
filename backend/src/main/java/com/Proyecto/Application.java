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
	CommandLineRunner initData(UsuarioService usuarioService) {
		return args -> {
			usuarioService.añadir(new Usuario(49333225L, "Carlos", "carlos@gmail.com", "654109671", "av.Carlos III"));
			usuarioService.añadir(new Usuario(49333195L, "Ana", "ana@gmail.com", "654141031", "av. Nacional"));
		};
	}

	@Bean
	CommandLineRunner initData(LibrosService librosService) {
		return args -> {
			librosService.añadir(new Libros(3L, "Lolita", "Vladimir Nabokov", "novela", "castellano", 14.95f));
			librosService.añadir(new Libros(5L, "La metamorfosis", "Frank Kafka", "novela corta", "castellano", 9.99f));
		};
	}

	@Bean
	CommandLineRunner initData(CompraService compraService) {
		return args -> {
			compraService.añadir(new Compra(18L, LocalDateTime.of(2023, 9, 19, 11, 30), 60f));
			compraService.añadir(new Compra(1L, LocalDateTime.of(2024, 11, 9, 20, 00), 5f));
		};
	}

	@Bean
	CommandLineRunner initData(VentaService ventaService) {
		return args -> {
			ventaService.añadir(new Venta(11L, LocalDateTime.of(2024, 6, 1, 21, 47), 100f));
			ventaService.añadir(new Venta(12L, LocalDateTime.of(2024, 2, 12, 13, 20), 35f));
		};
	}

}
