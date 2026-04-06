package com.duoc.backend.Medication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Servicio para manejar la lógica de negocio relacionada con los medicamentos
@Service
public class MedicationService {

    // Inyectamos el repositorio de medicamentos para acceder a la base de datos
    @Autowired
    private MedicationRepository medicationRepository;

    // Método para obtener todos los medicamentos
    public List<Medication> getAllMedications() {
        return (List<Medication>) medicationRepository.findAll();
    }

    // Método para obtener un medicamento por su ID
    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id).orElse(null);
    }

    // Método para guardar un nuevo medicamento o actualizar uno existente
    public Medication saveMedication(Medication medication) {
        return medicationRepository.save(medication);
    }

    // Método para eliminar un medicamento por su ID
    public void deleteMedication(Long id) {
        medicationRepository.deleteById(id);
    }
}