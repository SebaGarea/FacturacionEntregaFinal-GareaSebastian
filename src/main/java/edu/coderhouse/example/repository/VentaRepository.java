package edu.coderhouse.example.repository;

import edu.coderhouse.example.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {}