package com.example.ejercicioWebLogic.services.servicesImpl;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ejercicioWebLogic.entities.Clientes;
import com.example.ejercicioWebLogic.repositories.ClienteRepository;
import com.example.ejercicioWebLogic.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(
        ClienteRepository clienteRepository
    ) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Clientes obtenerClienteByClienteId(Long clienteId) throws Exception {
        Optional<Clientes> cliente = this.clienteRepository.findByClienteId(clienteId);

        if(cliente.isEmpty()) {
            throw new Exception("El cliente con id: " + clienteId + " no existe.");
        }

        return cliente.get();
    }

}
