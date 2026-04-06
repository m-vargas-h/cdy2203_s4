package com.duoc.backend.Appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador para manejar las solicitudes relacionadas con las citas (appointments)
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    // Inyección de dependencia del servicio de citas
    @Autowired
    private AppointmentService appointmentService;

    // Endpoint para obtener todas las citas
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return (List<Appointment>) appointmentService.getAllAppointments();
    }

    // Endpoint para obtener una cita por su ID
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    // Endpoint para guardar una nueva cita
    @PostMapping
    public Appointment saveAppointment(@RequestBody Appointment appointment) {
        return appointmentService.saveAppointment(appointment);
    }

    // Endpoint para eliminar una cita por su ID
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
}
