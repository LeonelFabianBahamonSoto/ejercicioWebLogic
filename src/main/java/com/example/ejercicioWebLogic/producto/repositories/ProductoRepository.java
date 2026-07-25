package com.example.ejercicioWebLogic.producto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicioWebLogic.producto.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Optional<Producto> findByProductoId(Long productoId);
}
