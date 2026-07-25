package com.example.ejercicioWebLogic.services.servicesImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ejercicioWebLogic.dtos.PedidoNuevoDto;
import com.example.ejercicioWebLogic.entities.Clientes;
import com.example.ejercicioWebLogic.entities.Pedido;
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

    public PedidoServiceImp (
        PedidoRepository pedidoRepository,
        ClienteService clienteService,
        ProductoService productoService,
        PedidoDetalleProductoService pedidoDetalleProductoService
    ) {
        this.pedidoRepository = pedidoRepository;
        this.clienteService   = clienteService;
        this.productoService  = productoService;
        this.pedidoDetalleProductoService = pedidoDetalleProductoService;
    };

    @Override
    public PedidoNuevoDto crearPedido(PedidoNuevoDto pedidoNuevoDto) throws Exception {
        Clientes cliente = this.clienteService.obtenerClienteByClienteId(pedidoNuevoDto.getClienteId());
        Producto producto = this.productoService.obtenerProductoByProductoId(pedidoNuevoDto.getProductoId());

        Pedido nuevoPedido = Pedido.builder()
            .fechaPedido(pedidoNuevoDto.getFechaPedido())
            .estadoPedido("Pendiente")
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
    public Optional<Pedido> obtenerPedidoByPedidoId(Long pedidoId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obtenerPedidoByPedidoId'");
    }

    @Override
    public Boolean eliminarPedidoByPedidoId(Long pedidoId) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPedidoByPedidoId'");
    }
    // private final 
}
