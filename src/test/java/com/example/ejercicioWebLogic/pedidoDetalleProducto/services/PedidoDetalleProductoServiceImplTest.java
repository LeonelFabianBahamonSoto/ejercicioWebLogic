package com.example.ejercicioWebLogic.pedidoDetalleProducto.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ejercicioWebLogic.entities.Clientes;
import com.example.ejercicioWebLogic.entities.Pedido;
import com.example.ejercicioWebLogic.estadoPedido.EstadoPedido;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.PedidoDetalleProducto;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.dtos.PedidoDetalleProductoDto;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.repositories.PedidoDetalleProductoRepository;
import com.example.ejercicioWebLogic.producto.Producto;

@ExtendWith(MockitoExtension.class)
public class PedidoDetalleProductoServiceImplTest {
    @InjectMocks
    private PedidoDetalleProductoServiceImpl pedidoDetalleProductoServiceImpl;

    @Mock
    private PedidoDetalleProductoRepository pedidoDetalleProductoRepository;

    private Clientes clienteEntityTest;
    private Pedido pedidoEntityTest;
    private Producto productoEntityTest;
    private PedidoDetalleProducto pedidoDetalleProductoEntityTest;
    private EstadoPedido estadoPedidoEntityTest;
    private List<PedidoDetalleProducto> pedidoDetalleProductoList = new ArrayList<PedidoDetalleProducto>();;

    private Integer cantidadTest = 1;
    private BigDecimal precioUnitarioTest = BigDecimal.valueOf(2500);

    @BeforeEach
    void setup() {
        clienteEntityTest = crearCliente();
        pedidoDetalleProductoEntityTest = crearPedidoDetalleProducto();
        pedidoEntityTest = crearPedido();
        productoEntityTest = crearProducto();
        estadoPedidoEntityTest = crearEstadoPedido();

        this.pedidoDetalleProductoList.add(pedidoDetalleProductoEntityTest);
    }

    @DisplayName("Se crea un PedidoDetalleProducto desde pedidos satisfactoriamente")
    @Test
    void whenCrearPedidoDetalleProductoByPedidos_returnNewPedidoDetalleProductoDto() throws Exception
    {
        when(this.pedidoDetalleProductoRepository.save(any(PedidoDetalleProducto.class)))
            .thenReturn(pedidoDetalleProductoEntityTest);

        PedidoDetalleProductoDto nuevoPedidoDetalleProducto =
            this.pedidoDetalleProductoServiceImpl.crearPedidoDetalleProductoByPedidos(cantidadTest, precioUnitarioTest, pedidoEntityTest, productoEntityTest);

        // --- Verificación con ArgumentCaptor ---
        ArgumentCaptor<PedidoDetalleProducto> captor = ArgumentCaptor.forClass(PedidoDetalleProducto.class);

        verify(this.pedidoDetalleProductoRepository).save(captor.capture());

        PedidoDetalleProducto guardado = captor.getValue();

        assertEquals(cantidadTest, guardado.getCantidad());
        assertEquals(precioUnitarioTest, guardado.getPrecioUnitario());
        assertEquals(pedidoEntityTest.getPedidoId(), guardado.getPedido().getPedidoId());
        assertEquals(productoEntityTest.getProductoId(), guardado.getProducto().getProductoId());
    }

    @DisplayName("Se consultas todos los PedidosDetallesProductos")
    @Test
    void whenObtenerTodosPedidosDetallesProductos_returnAllPedidoDetalleProducto() throws Exception {
        when(this.pedidoDetalleProductoRepository.findAll())
            .thenReturn(pedidoDetalleProductoList);

        List<PedidoDetalleProducto> resultado = this.pedidoDetalleProductoServiceImpl.obtenerAllPedidosDetalleProducto();

        verify(pedidoDetalleProductoRepository).findAll();

        assertNotNull(resultado);
        assertEquals(pedidoDetalleProductoList.size(), resultado.size());
        assertEquals(pedidoDetalleProductoList, resultado);
    }

    private Clientes crearCliente() {
        Clientes nuevoCliente = Clientes.builder()
            .clienteId(Long.valueOf(1))
            .nombre("Cliente Testing")
            .email("clienteTesting@email.com")
            .fechaRegistro(LocalDate.of(2000, 1, 1))
            .build();

        return nuevoCliente;
    }

    private Pedido crearPedido() {
        Pedido nuevoPedido = Pedido.builder()
            .pedidoId(Long.valueOf(1))
            .fechaPedido(LocalDate.of(2017, 1, 1))
            .estadoPedido(estadoPedidoEntityTest)
            .cliente(this.crearCliente())
            .build();

        return nuevoPedido;
    }

    private Producto crearProducto() {
        Producto producto = Producto.builder()
            .productoId(Long.valueOf(1))
            .nombreProducto("Producto Testing Name")
            .precio(BigDecimal.valueOf(2500))
            .stock(Integer.valueOf(1))
            .build();

        return producto;
    }

    private PedidoDetalleProducto crearPedidoDetalleProducto() {
        PedidoDetalleProducto pedidoDetalleProducto = PedidoDetalleProducto.builder()
            .detalleId(Long.valueOf(1))
            .cantidad(1)
            .precioUnitario(BigDecimal.valueOf(2500))
            .pedido(this.crearPedido())
            .producto(this.crearProducto())
            .build();

        return pedidoDetalleProducto;
    }

    private EstadoPedido crearEstadoPedido() {
        EstadoPedido estadoPedido = EstadoPedido.builder()
            .estadoId(1)
            .nombreEstado("PENDIENTE")
            .build();

        return estadoPedido;
    }
}
