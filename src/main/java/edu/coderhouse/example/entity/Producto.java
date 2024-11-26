package edu.coderhouse.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GenerationType;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTOS")
@Schema(description = "Entidad producto")
public class Producto {

    @Schema(description = "Id del Producto")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //----> Clave Primaria

    @Schema(description = "Nombre del producto", example = "Producto 1")
    @Column(nullable = false)
    private String nombre;

    @Schema(description = "Descripcion del producto", example = "Descripcion del producto 1")
    @Column
    private String descripcion;

    @Schema(description = "Precio del producto", example = "10.0")
    @Column(nullable = false, columnDefinition = "DOUBLE")
    private double precio;

    @Schema(description = "Stock del producto", example = "100")
    @Column(nullable = false)
    private int stock;

    @Schema(description = "Categoria del producto", example = "Mesas")
    @Column
    private String categoria;

    public Producto(String nombre, String descripcion, double precio, int stock, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }
}
