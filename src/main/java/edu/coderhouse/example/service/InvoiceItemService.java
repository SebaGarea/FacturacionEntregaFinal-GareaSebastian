package edu.coderhouse.example.service;


import edu.coderhouse.example.entity.InvoiceItem;
import edu.coderhouse.example.entity.Producto;
import edu.coderhouse.example.repository.InvoiceItemRepository;
import edu.coderhouse.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceItemService {

    @Autowired
    private InvoiceItemRepository repository;

    @Autowired
    private ProductoRepository productoRepository;

    public InvoiceItem save(InvoiceItem item) {return repository.save(item);}

    public List<InvoiceItem> getAllItems() {return repository.findAll();}


    public Optional<Producto> getProductById(Long id) {
        return productoRepository.findById(id);}

    public List<InvoiceItem> findByInvoiceId(UUID invoiceId) {return repository.findByInvoiceId(invoiceId);}

    public void deleteById(UUID id) {
        Optional<InvoiceItem> item = repository.findById(id);
        if (item.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Item de factura no encontrado con ID: " + id);
        }
    }


}
