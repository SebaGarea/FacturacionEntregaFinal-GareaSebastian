package edu.coderhouse.example.service;

import edu.coderhouse.example.entity.Venta;
import edu.coderhouse.example.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository repository;

    public VentaService(VentaRepository repository){
        this.repository = repository;
    }
    public Venta save(Venta venta){
        return  repository.save(venta);
    }

    public List<Venta> getVentas(){
        return repository.findAll();
    }

    public Optional<Venta> getById(Long id){
        return  repository.findById(id);
    }

}