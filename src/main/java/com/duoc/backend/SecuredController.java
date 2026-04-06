package com.duoc.backend;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Controlador REST que maneja las solicitudes a la ruta "/greetings"
@RestController
public class SecuredController {

    // Método que responde a las solicitudes GET a "/greetings" y devuelve un saludo personalizado
    @RequestMapping("greetings")
    public String greetings(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello {" + name + "}";
    }
}