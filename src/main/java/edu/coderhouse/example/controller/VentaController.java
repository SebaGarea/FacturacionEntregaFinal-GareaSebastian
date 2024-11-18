package edu.coderhouse.example.controller;

import edu.coderhouse.example.entity.Venta;
import edu.coderhouse.example.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    @Autowired
    private VentaService service;

    public VentaController(VentaService service){
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<Venta>> getAll() {
        Iterable<Venta> ventas = service.getVentas();
        return ResponseEntity.ok(ventas);
    }
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Venta>> getById(@PathVariable Long id) {
        Optional <Venta> venta = service.getById(id);

        if (venta.isPresent()) {
            // Si la venta existe
            return ResponseEntity.ok(venta);
        } else {
            // Si la venta no existe
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Venta> create(@RequestBody Venta venta) {
        try {
            Venta nuevaVenta = service.save(venta);
            return ResponseEntity.ok(nuevaVenta);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
