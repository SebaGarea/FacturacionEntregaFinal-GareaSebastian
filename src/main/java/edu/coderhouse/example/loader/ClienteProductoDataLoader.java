package edu.coderhouse.example.loader;

import edu.coderhouse.example.entity.Cliente;
import edu.coderhouse.example.service.ClienteService;
import edu.coderhouse.example.entity.Producto;
import edu.coderhouse.example.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ClienteProductoDataLoader implements CommandLineRunner {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    public ClienteProductoDataLoader(ClienteService clienteService, ProductoService productoService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Creación de datos de clientes
        Map<String, Cliente> clientes = new HashMap<>();
        clientes.put("cliente1", new Cliente("Carlos", "Gómez", "100204030", "LEG001"));
        clientes.put("cliente2", new Cliente("María", "Fernández", "20030040", "LEG002"));
        clientes.put("cliente3", new Cliente("Luis", "Martínez", "30040050", "LEG003"));
        clientes.put("cliente4", new Cliente("Ana", "Rodríguez", "40050060", "LEG004"));

        // Guardar clientes en la base de datos
        for (Map.Entry<String, Cliente> entry : clientes.entrySet()) {
            clienteService.save(entry.getValue());
        }

        // Creación de datos de productos
        Map<String, Producto> productos = new HashMap<>();
        productos.put("producto1", new Producto("Televisor", "Televisor Smart 55 pulgadas 4K", 1500.00, 5,"Tecnologia"));
        productos.put("producto2", new Producto("Tablet", "Tablet con pantalla táctil de 10 pulgadas", 300.00, 25,"Tecnologia"));
        productos.put("producto3", new Producto("Impresora", "Impresora multifunción con conexión Wi-Fi", 120.00, 15,"Tecnologia"));
        productos.put("producto4", new Producto("Mesa Guayubira Maciza", "Mesa de Guayubira maciza, estilo Idustrial", 75.00, 30,"Muebles"));
        productos.put("producto5", new Producto("Silla Oslo", "Silla Nordica", 450.00, 10,"Muebles"));

        // Guardar productos en la base de datos
        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            productoService.save(entry.getValue());
        }
    }
}
