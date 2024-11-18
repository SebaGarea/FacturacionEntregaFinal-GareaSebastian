package edu.coderhouse.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
@Table(name = "VENTA")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //----> Clave Primaria

    public Venta(){}

    public Venta(String nombre, LocalDateTime fecha_venta, int total, String metodo_pago) {
        this.nombre = nombre;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.metodo_pago = metodo_pago;
    }

    @Column(name="NOMBRE")
    private String nombre;

    @Column(name = "FECHA_VENTA")
    private LocalDateTime fecha_venta;

    @Column(name="TOTAL")
    private int total;

    @Column(name="METODO_PAGO")
    private String metodo_pago;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}