package com.example.ejercicioWebLogic.estadoPedido.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicioWebLogic.estadoPedido.EstadoPedido;

public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Integer> {
    Optional<EstadoPedido> findByEstadoId(Integer estadoId);
}
