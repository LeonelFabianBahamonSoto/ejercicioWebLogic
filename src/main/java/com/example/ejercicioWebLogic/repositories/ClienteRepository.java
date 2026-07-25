package com.example.ejercicioWebLogic.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicioWebLogic.entities.Clientes;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {
    Optional<Clientes> findByClienteId(Long clienteId);
}
