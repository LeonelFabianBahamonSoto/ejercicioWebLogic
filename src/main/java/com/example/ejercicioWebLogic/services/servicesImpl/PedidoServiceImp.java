package com.example.ejercicioWebLogic.services.servicesImpl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ejercicioWebLogic.dtos.PedidoNuevoDto;
import com.example.ejercicioWebLogic.entities.Clientes;
import com.example.ejercicioWebLogic.entities.Pedido;
import com.example.ejercicioWebLogic.estadoPedido.EstadoPedido;
import com.example.ejercicioWebLogic.estadoPedido.repositories.EstadoPedidoRepository;
import com.example.ejercicioWebLogic.exceptions.ResourceNotFoundException;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.services.PedidoDetalleProductoService;
import com.example.ejercicioWebLogic.producto.Producto;
import com.example.ejercicioWebLogic.producto.services.ProductoService;
import com.example.ejercicioWebLogic.repositories.PedidoRepository;
import com.example.ejercicioWebLogic.services.ClienteService;
import com.example.ejercicioWebLogic.services.PedidoServiceI;

@Service
public class PedidoServiceImp implements PedidoServiceI {

    private final PedidoRepository pedidoRepository;

    private final ClienteService clienteService;
    private final ProductoService productoService;
    private final PedidoDetalleProductoService pedidoDetalleProductoService;

    // Temporal
    private final EstadoPedidoRepository estadoPedidoRepository;

    public PedidoServiceImp (
        PedidoRepository pedidoRepository,
        ClienteService clienteService,
        ProductoService productoService,
        PedidoDetalleProductoService pedidoDetalleProductoService,

        EstadoPedidoRepository estadoPedidoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService   = clienteService;
        this.productoService  = productoService;
        this.pedidoDetalleProductoService = pedidoDetalleProductoService;

        this.estadoPedidoRepository = estadoPedidoRepository;
    };

    @Override
    public PedidoNuevoDto crearPedido(PedidoNuevoDto pedidoNuevoDto) throws Exception {
        Clientes cliente = this.clienteService.obtenerClienteByClienteId(pedidoNuevoDto.getClienteId());
        Producto producto = this.productoService.obtenerProductoByProductoId(pedidoNuevoDto.getProductoId());
        EstadoPedido estadoPedido = this.estadoPedidoRepository.findByEstadoId(pedidoNuevoDto.getEstadoPedidoId())
            .orElseThrow(() -> new ResourceNotFoundException("El estado no existe. ", Long.valueOf(pedidoNuevoDto.getEstadoPedidoId())));

        Pedido nuevoPedido = Pedido.builder()
            .fechaPedido(pedidoNuevoDto.getFechaPedido())
            .estadoPedido(estadoPedido)
            .cliente(cliente)
            .build();

        Pedido pedidoCreado = this.pedidoRepository.save(nuevoPedido);

        BigDecimal total = producto.getPrecio().multiply(BigDecimal.valueOf(pedidoNuevoDto.getCantidad()));
        this.pedidoDetalleProductoService.crearPedidoDetalleProductoByPedidos(pedidoNuevoDto.getCantidad(), total, pedidoCreado, producto);

        return pedidoNuevoDto;
    }

    @Override
    public PedidoNuevoDto editarPedido(PedidoNuevoDto pedidoNuevoDto) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarPedido'");
    }

    @Override
    public List<Pedido> obtenerTodosPedidos() throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerTodosPedidos'");
    }

    @Override
    public Pedido obtenerPedidoByPedidoId(Long pedidoId) throws Exception {
        Pedido pedido = this.pedidoRepository.findByPedidoId(pedidoId)
            .orElseThrow(() -> new ResourceNotFoundException("El pedido no existe.", pedidoId));

        return pedido;
    }

    @Override
    public Boolean eliminarPedidoByPedidoId(Long pedidoId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPedidoByPedidoId'");
    }

}
