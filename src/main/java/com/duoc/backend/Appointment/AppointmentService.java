package com.duoc.backend.Appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Clase de servicio para manejar la lógica de negocio relacionada con las citas
@Service
public class AppointmentService {

    // Inyección de dependencia del repositorio de citas
    @Autowired
    private AppointmentRepository appointmentRepository;

    // Método para obtener todas las citas
    public Iterable<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Método para obtener una cita por su ID
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    // Método para guardar una nueva cita o actualizar una existente
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Método para eliminar una cita por su ID
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}