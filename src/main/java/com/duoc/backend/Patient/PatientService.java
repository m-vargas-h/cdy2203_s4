package com.duoc.backend.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Servicio que maneja la lógica de negocio relacionada con los pacientes en la clínica veterinaria
@Service
public class PatientService {

    // Inyección de la dependencia del repositorio de pacientes
    @Autowired
    private PatientRepository patientRepository;

    // Método para obtener la lista de todos los pacientes registrados en la clínica veterinaria
    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Método para obtener un paciente específico por su ID
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    // Método para guardar un nuevo paciente en la base de datos
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Método para eliminar un paciente específico por su ID
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}