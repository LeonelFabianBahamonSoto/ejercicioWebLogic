package com.example.ejercicioWebLogic.services;

import com.example.ejercicioWebLogic.entities.Clientes;

public interface ClienteService {
    Clientes obtenerClienteByClienteId(Long clienteId) throws Exception;
}
