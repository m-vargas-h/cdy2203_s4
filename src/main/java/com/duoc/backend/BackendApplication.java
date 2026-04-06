package com.duoc.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Clase principal de la aplicación Spring Boot. Esta clase se encarga de iniciar la aplicación 
// y configurar el contexto de Spring. La anotación @SpringBootApplication es una combinación de 
// @Configuration, @EnableAutoConfiguration y @ComponentScan, lo que facilita la configuración y 
// el escaneo de componentes en la aplicación.
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
