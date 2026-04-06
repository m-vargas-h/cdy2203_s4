package com.duoc.backend.Invoice;

import com.duoc.backend.Care.Care;
import com.duoc.backend.Medication.Medication;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

// Clase que representa una factura médica, incluyendo los cuidados y medicamentos asociados, 
// así como cargos adicionales.
@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String patientName;
    private LocalDate date;
    private LocalTime time;

    // Relación Many-to-Many con cuidados y medicamentos, ya que una factura puede incluir 
    // múltiples cuidados y medicamentos, y un cuidado o medicamento puede estar asociado a múltiples facturas.
    @ManyToMany
    @JoinTable(
        name = "invoice_cares",
        joinColumns = @JoinColumn(name = "invoice_id"),
        inverseJoinColumns = @JoinColumn(name = "care_id")
    )
    private List<Care> cares;

    // Relación Many-to-Many con medicamentos, ya que una factura puede incluir múltiples medicamentos, 
    // y un medicamento puede estar asociado a múltiples facturas
    @ManyToMany
    @JoinTable(
        name = "invoice_medications",
        joinColumns = @JoinColumn(name = "invoice_id"),
        inverseJoinColumns = @JoinColumn(name = "medication_id")
    )
    private List<Medication> medications;

    // Cargos adicionales (ej: hospitalización, exámenes extra)
    private Double additionalCharges;

    // Descripción de los cargos adicionales
    private String additionalChargesDescription;

    private Double totalCost;

    // Getters and Setters
    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getPatientName() { 
        return patientName; 
    }

    public void setPatientName(String patientName) { 
        this.patientName = patientName; 
    }

    public LocalDate getDate() { 
        return date; 
    }

    public void setDate(LocalDate date) { 
        this.date = date; 
    }

    public LocalTime getTime() { 
        return time; 
    }

    public void setTime(LocalTime time) { 
        this.time = time; 
    }

    public List<Care> getCares() { 
        return cares; 
    }

    public void setCares(List<Care> cares) { 
        this.cares = cares; 
    }

    public List<Medication> getMedications() { 
        return medications; 
    }

    public void setMedications(List<Medication> medications) { 
        this.medications = medications; 
    }

    public Double getAdditionalCharges() { 
        return additionalCharges; 
    }

    public void setAdditionalCharges(Double additionalCharges) { 
        this.additionalCharges = additionalCharges; 
    }

    public String getAdditionalChargesDescription() { 
        return additionalChargesDescription; 
    }

    public void setAdditionalChargesDescription(String additionalChargesDescription) {
        this.additionalChargesDescription = additionalChargesDescription;
    }

    public Double getTotalCost() {
        return totalCost; 
    }

    public void setTotalCost(Double totalCost) { 
        this.totalCost = totalCost; 
    }
}
