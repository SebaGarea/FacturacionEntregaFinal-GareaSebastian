package edu.coderhouse.example.service;

import edu.coderhouse.example.entity.Producto;
import edu.coderhouse.example.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository repository){
        this.productoRepository= repository;
    }

    public Producto save(Producto producto){
        return productoRepository.save(producto);
    }

    public List <Producto> getProducts(){
        return productoRepository.findAll();
    }

    public Optional<Producto> getById(Long id){
        return productoRepository.findById(id);
    }

    public void deleteById(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }
    public void updateProductStock(Producto producto) {
        System.out.println("Actualizando stock para el producto ID: " + producto.getId());
        if (productoRepository.existsById(producto.getId())) {
            productoRepository.save(producto);
            System.out.println("Stock actualizado para el producto: " + producto.getId());
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + producto.getId());
        }
    }

}