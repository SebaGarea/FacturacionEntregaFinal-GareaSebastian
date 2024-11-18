package edu.coderhouse.example.service;

import edu.coderhouse.example.entity.Cliente;
import edu.coderhouse.example.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }
    public Cliente save(Cliente cliente){
        return  repository.save(cliente);
    }

    public List<Cliente> getClientes(){
        return repository.findAll();
    }

    public Optional<Cliente> getById(Long id){
        return repository.findById(id);
    }
}