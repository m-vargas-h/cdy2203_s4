package com.duoc.backend.Care;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador para manejar las solicitudes relacionadas con los cuidados de mascotas
@RestController
@RequestMapping("/care")
public class CareController {

    // Inyección de la dependencia del repositorio de cuidados
    @Autowired
    private CareRepository careRepository;

    // Endpoint para obtener todos los cuidados de mascotas
    @GetMapping
    public List<Care> getAllCares() {
        return (List<Care>) careRepository.findAll();
    }

    // Endpoint para obtener un cuidado de mascota por su ID
    @GetMapping("/{id}")
    public Care getCareById(@PathVariable Long id) {
        return careRepository.findById(id).orElse(null);
    }

    // Endpoint para crear un nuevo cuidado de mascota
    @PostMapping
    public Care saveCare(@RequestBody Care service) {
        return careRepository.save(service);
    }

    // Endpoint para eliminar un cuidado de mascota por su ID
    @DeleteMapping("/{id}")
    public void deleteCare(@PathVariable Long id) {
        careRepository.deleteById(id);
    }
}