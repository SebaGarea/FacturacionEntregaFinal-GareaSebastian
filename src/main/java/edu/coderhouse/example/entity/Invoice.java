package edu.coderhouse.example.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Entidad que representa una factura" , example = "{\"clientId\":\"00000000-0000-0000-0000-000000000000\",\"date\":\"2023-01-01T00:00:00\"}")
@Table(name = "invoice")
public class Invoice {
    @Schema(description = "Id de la factura" , example = "00000000-0000-0000-0000-000000000000")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Schema(description = "Cliente de la factura" , example = "{\"dni\":\"12345678\",\"name\":\"Juan\",\"lastname\":\"Perez\",\"mail\":\"juanperez@gmail.com\",\"telefono\":\"+56987654321\"}")
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Cliente cliente;

    @Schema(description = "Fecha de la factura" , example = "2023-01-01T00:00:00")
    @Column(nullable = false)
    private LocalDateTime date;

    // Métodos manuales para date
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Schema(description = "Lista de productos de la factura" , example = "[{\"id\":\"00000000-0000-0000-0000-000000000000\",\"name\":\"Producto 1\",\"description\":\"Descripcion del producto 1\",\"price\":10.0,\"stock\":100}]")
    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InvoiceItem> items = new ArrayList<>();

    @Column(nullable = false)
    private Double totalAmount;

    // methods

    public Invoice(Cliente cliente, LocalDateTime date, Double totalAmount) {
        this.cliente = cliente;
        this.date = date;
        this.totalAmount = totalAmount;
        this.items = new ArrayList<>();
    }

    public void clearItems() {
        this.items.clear();
        this.totalAmount = 0.0;
    }


    public void addItem(InvoiceItem item) {
        // Agregar el ítem a la lista de items y calcular el subtotal
        items.add(item);
        item.setInvoice(this);
        totalAmount += item.getSubtotal();
    }


    public void removeItem(InvoiceItem item) {
        items.remove(item);
        item.setInvoice(null);
        totalAmount -= item.getSubtotal();
    }

    public void calculateTotal () {
        totalAmount = items.stream()
                .mapToDouble(InvoiceItem::getSubtotal)
                .sum();
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public Cliente getClient() {
        return cliente;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public UUID getId() {
        return id;
    }
}