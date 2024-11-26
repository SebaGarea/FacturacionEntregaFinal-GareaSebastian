package edu.coderhouse.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Entidad que representa a un cliente")
@Table(name = "CLIENTE")
public class Cliente {

    public Cliente(UUID id){
        this.id = id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cliente(String nombre, String apellido, String dni, String legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.legajo = legajo;
    }

    @Schema (description = "Id del cliente")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column (name="id", updatable = false, nullable = false)
    private UUID id; //----> Clave Primaria


    @Schema(description = "Nombre del Cliente", example = "Manolo")
    @Column(nullable = false)
    private String nombre;

    @Schema(description = "Apellido del Cliente", example = "Perez")
    @Column(nullable = false)
    private String apellido;

    @Schema(description = "DNI del Cliente", example = "37958698")
    @Column(nullable = false)
    private String dni;

    @Schema(description = "Legajo del Cliente")
    @Column(nullable = false, unique = true)
    private String legajo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public UUID getId() {
        return id;
    }

}