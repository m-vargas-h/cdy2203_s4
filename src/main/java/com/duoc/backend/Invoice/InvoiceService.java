package com.duoc.backend.Invoice;

import com.duoc.backend.Care.Care;
import com.duoc.backend.Care.CareRepository;
import com.duoc.backend.Medication.Medication;
import com.duoc.backend.Medication.MedicationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

// Servicio para manejar la lógica de negocio relacionada con las facturas
@Service
public class InvoiceService {

    // Inyección de los repositorios necesarios para acceder a la base de datos
    @Autowired
    private InvoiceRepository invoiceRepository;

    // Repositorios para validar y cargar medicamentos y servicios/cuidados
    @Autowired
    private MedicationRepository medicationRepository;

    // Repositorio para validar y cargar servicios/cuidados
    @Autowired
    private CareRepository careRepository;

    public Iterable<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public Invoice saveInvoice(Invoice invoice) {
        // Validar y cargar medicamentos desde la BD
        List<Medication> validMedications = StreamSupport.stream(
                medicationRepository.findAllById(
                        invoice.getMedications().stream()
                               .map(Medication::getId)
                               .collect(Collectors.toList())
                ).spliterator(), false
        ).collect(Collectors.toList());

        if (validMedications.size() != invoice.getMedications().size()) {
            throw new IllegalArgumentException("Algunos medicamentos no existen en la base de datos.");
        }

        // Validar y cargar servicios/cuidados desde la BD
        List<Care> validCares = StreamSupport.stream(
                careRepository.findAllById(
                        invoice.getCares().stream()
                               .map(Care::getId)
                               .collect(Collectors.toList())
                ).spliterator(), false
        ).collect(Collectors.toList());

        if (validCares.size() != invoice.getCares().size()) {
            throw new IllegalArgumentException("Algunos servicios no existen en la base de datos.");
        }

        // Calcular costo total de servicios
        double totalCareCost = validCares.stream()
                .mapToDouble(Care::getCost)
                .sum();

        // Calcular costo total de medicamentos
        double totalMedicationCost = validMedications.stream()
                .mapToDouble(Medication::getCost)
                .sum();

        // Incluir cargos adicionales si existen
        double additionalCharges = 0.0;
        if (invoice.getAdditionalCharges() != null && invoice.getAdditionalCharges() > 0) {
            additionalCharges = invoice.getAdditionalCharges();
        }

        // Calcular y asignar costo total
        invoice.setTotalCost(totalCareCost + totalMedicationCost + additionalCharges);
        invoice.setCares(validCares);
        invoice.setMedications(validMedications);

        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}
