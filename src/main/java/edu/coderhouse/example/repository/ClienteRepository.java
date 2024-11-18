package edu.coderhouse.example.repository;


import edu.coderhouse.example.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

