package edu.coderhouse.example.service;

import edu.coderhouse.example.entity.Invoice;
import edu.coderhouse.example.entity.InvoiceItem;
import edu.coderhouse.example.entity.Producto;
import edu.coderhouse.example.repository.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository repository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private TimeApiService timeApiService;

    public Invoice save(Invoice invoice) {
        if (invoice.getDate() == null) {
            invoice.setDate(timeApiService.getCurrentDateTime()); // Asigna la fecha de TimeAPI
        }
        return repository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return repository.findAll();
    }

    public Optional<Invoice> getById(UUID id) {
        return repository.findById(id);
    }

    public List<Invoice> saveAll(Iterable<Invoice> invoices) {
        return repository.saveAll(invoices);
    }

    public void deleteById(UUID id) {
        Optional<Invoice> invoice = repository.findById(id);
        if (invoice.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Factura no encontrada con ID: " + id);
        }
    }

    @Transactional
    public void addItemToInvoice(Invoice invoice, InvoiceItem item) {
        Producto producto = item.getProducto();

        if (producto.getStock() >= item.getQuantity()) {
            producto.setStock(producto.getStock() - item.getQuantity());
            productoService.updateProductStock(producto);


            invoice.addItem(item);
            save(invoice);
            System.out.println("Item agregado correctamente, stock actualizado: " + producto.getStock());;  // Guardar la factura actualizada
        } else {
            throw new RuntimeException("No hay suficiente stock para el producto: " + producto.getId());
        }
    }
}

