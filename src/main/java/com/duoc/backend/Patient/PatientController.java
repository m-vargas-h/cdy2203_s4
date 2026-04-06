package com.duoc.backend.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controlador REST para manejar las operaciones relacionadas con los pacientes
@RestController
@RequestMapping("/patient")
public class PatientController {

    // Inyección de la dependencia del servicio de pacientes
    @Autowired
    private PatientService patientService;

    // Endpoint de prueba para verificar que el controlador está funcionando correctamente
    @GetMapping("/register")
    public String greetings(@RequestParam(value="name", defaultValue="World") String name) {
        return "Hello {" + name + "}";
    }

    // Endpoint para obtener la lista de todos los pacientes registrados en la clínica veterinaria
    @GetMapping
    public List<Patient> getAllPatients() {
        return (List<Patient>) patientService.getAllPatients();
    }

    // Endpoint para obtener un paciente específico por su ID
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // Endpoint para guardar un nuevo paciente en la base de datos
    @PostMapping
    public Patient savePatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    // Endpoint para eliminar un paciente específico por su ID
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }
    
}




