package com.duoc.backend.Medication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// controlador REST para manejar las solicitudes relacionadas con los medicamentos
@RestController
@RequestMapping("/medication")
public class MedicationController {

    // inyecta el servicio de medicamentos para manejar la lógica de negocio
    @Autowired
    private MedicationService medicationService;

    // endpoint para obtener todos los medicamentos
    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }

    // endpoint para obtener un medicamento por su ID
    @GetMapping("/{id}")
    public Medication getMedicationById(@PathVariable Long id) {
        return medicationService.getMedicationById(id);
    }

    // endpoint para guardar un nuevo medicamento
    @PostMapping
    public Medication saveMedication(@RequestBody Medication medication) {
        return medicationService.saveMedication(medication);
    }

    // endpoint para eliminar un medicamento por su ID
    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
    }
}