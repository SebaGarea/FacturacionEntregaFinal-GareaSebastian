package edu.coderhouse.example.controller;

import edu.coderhouse.example.entity.Cliente;
import edu.coderhouse.example.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;

    public ClienteController(ClienteService service){
        this.service = service;
    }
    @GetMapping(produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity <Iterable<Cliente>> getAll(){
        Iterable<Cliente> clientes = service.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Cliente>> getById(@PathVariable Long id){
        Optional<Cliente>cliente = service.getById(id);

        if(cliente.isPresent()){
            return  ResponseEntity.ok(cliente);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = service.save(cliente);
            return ResponseEntity.ok(nuevoCliente);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
