package edu.coderhouse.example.controller;


import edu.coderhouse.example.entity.Invoice;
import edu.coderhouse.example.entity.InvoiceItem;
import edu.coderhouse.example.entity.Producto;
import edu.coderhouse.example.service.InvoiceService;
import edu.coderhouse.example.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Create Invoice" , description = "Create a new invoice")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
        try {
            Invoice newInvoice = invoiceService.save(invoice);
            return ResponseEntity.status(HttpStatus.CREATED).body(newInvoice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Get all invoices" , description = "Get all invoices")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Iterable<Invoice>> getAll() {
        Iterable<Invoice> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @Operation(summary = "Get by id invoice" , description = "Get an invoice by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Optional<Invoice>> getById(@PathVariable UUID id) {
        Optional<Invoice> invoice = invoiceService.getById(id);
        if (invoice.isPresent()) {
            return ResponseEntity.ok(invoice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Add items to invoice", description = "Add items to an invoice")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @PostMapping("/{invoiceId}/items")
    public ResponseEntity<?> addItemsToInvoice(
            @PathVariable UUID invoiceId,
            @RequestBody List<InvoiceItem> request) {

        Optional<Invoice> invoiceOpt = invoiceService.getById(invoiceId);

        if (!invoiceOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }

        Invoice invoice = invoiceOpt.get();
        invoice.clearItems();

        try {
            for (InvoiceItem item : request) {

                if (item.getProducto() == null || item.getProducto().getId() == null) {
                    throw new RuntimeException("Product not found with ID");
                }

                Optional<Producto> productOpt = productoService.getById(item.getProducto().getId());

                if (!productOpt.isPresent()) {
                    throw new RuntimeException("Product not found with ID: " + item.getProducto().getId());
                }


                item.setProducto(productOpt.get());
                item.setInvoice(invoice);
                item.setSubtotal();


                invoiceService.addItemToInvoice(invoice, item);
            }

            invoice.calculateTotal();
            Invoice updatedInvoice = invoiceService.save(invoice);

            return ResponseEntity.status(HttpStatus.CREATED).body(updatedInvoice);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding items");
        }
    }
    @Operation(summary = "Update invoice", description = "Update an existing invoice by ID")
    @ApiResponse(responseCode = "200", description = "Invoice updated successfully")
    @ApiResponse(responseCode = "404", description = "Invoice not found for the provided ID")
    @ApiResponse(responseCode = "400", description = "Invalid invoice data")
    @PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Invoice> updateInvoice(@PathVariable UUID id, @RequestBody Invoice invoice) {
        Optional<Invoice> existingInvoice = invoiceService.getById(id);
        if (!existingInvoice.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        invoice.setId(id);
        Invoice updatedInvoice = invoiceService.save(invoice);
        return ResponseEntity.ok(updatedInvoice);
    }
    @Operation(summary = "Delete invoice by id", description = "Delete a specific invoice by ID")
    @ApiResponse(responseCode = "204", description = "Invoice deleted successfully")
    @ApiResponse(responseCode = "404", description = "Invoice not found for the provided ID")
    @ApiResponse(responseCode = "400", description = "Invalid invoice data")
    @DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Void> deleteInvoice(@PathVariable UUID id) {
        try {
            invoiceService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}










