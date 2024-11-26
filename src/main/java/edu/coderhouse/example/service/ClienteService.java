package edu.coderhouse.example.service;

import edu.coderhouse.example.entity.Cliente;
import edu.coderhouse.example.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository ClienteRepository;

    public Cliente save(Cliente cliente){
        return ClienteRepository.save(cliente);
    }

    public List<Cliente> getClientes(){
        return ClienteRepository.findAll();
    }

    public ClienteService(ClienteRepository repository){
        this.ClienteRepository = repository;
    }

    public Optional<Cliente> getById(UUID id){
        return ClienteRepository.findById(id);
    }

    public void deleteById(UUID id) {
        Optional<Cliente> cliente = ClienteRepository.findById(id);
        if (cliente.isPresent()) {
            ClienteRepository.deleteById(id);
        } else {
            throw new RuntimeException(" Cliente no encontrado con ID: " + id);
        }
    }

    public Optional<Cliente> getByDni(String dni) {
        return ClienteRepository.findByDni(String.valueOf(dni));
    }

    public Optional<Cliente> getByMail(String legajo) {
        return ClienteRepository.findByLegajo(legajo);
    }
    public Optional<Cliente> findById(UUID id) {
        return ClienteRepository.findById(id);
    }

}