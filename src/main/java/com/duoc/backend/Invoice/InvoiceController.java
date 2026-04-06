package com.duoc.backend.Invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para manejar las operaciones CRUD de las facturas
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    // Inyección del servicio de facturas
    @Autowired
    private InvoiceService invoiceService;

    // Endpoint para obtener todas las facturas
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return (List<Invoice>) invoiceService.getAllInvoices();
    }

    // Endpoint para obtener una factura por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        if (invoice == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoice);
    }

    // Endpoint para crear una nueva factura
    @PostMapping
    public ResponseEntity<?> saveInvoice(@RequestBody Invoice invoice) {
        try {
            Invoice saved = invoiceService.saveInvoice(invoice);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Endpoint para actualizar una factura existente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }
}
