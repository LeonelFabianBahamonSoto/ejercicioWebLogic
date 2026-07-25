package com.example.ejercicioWebLogic.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicioWebLogic.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findAll();

    Optional<Pedido> findByPedidoId(Long pedidoId);
}
