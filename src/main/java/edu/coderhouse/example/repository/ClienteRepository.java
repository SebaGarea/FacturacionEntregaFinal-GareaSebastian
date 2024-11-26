package edu.coderhouse.example.repository;


import edu.coderhouse.example.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional <Cliente> findByDni(String dni);
    Optional <Cliente> findByLegajo(String legajo);
    void deleteById(UUID id);

    Optional<Cliente> findById(UUID id);
}

