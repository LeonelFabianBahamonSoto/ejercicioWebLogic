package com.example.ejercicioWebLogic.pedidoDetalleProducto.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ejercicioWebLogic.pedidoDetalleProducto.PedidoDetalleProducto;
import com.example.ejercicioWebLogic.pedidoDetalleProducto.services.PedidoDetalleProductoService;

@WebMvcTest(PedidoDetalleProductoController.class)
public class PedidoDetalleProductoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoDetalleProductoService pedidoDetalleProductoService;

    @DisplayName("")
    @Test
    void whenObtenerTodos_returnListWithStatus200() throws Exception {
        PedidoDetalleProducto detalle = crearPedidoDetalleProductoTest();

        when(this.pedidoDetalleProductoService.obtenerAllPedidosDetalleProducto())
            .thenReturn(List.of(detalle));

        this.mockMvc.perform(get("/pedidoDetalleProducto/todosPedidoDetalleProductos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].detalleId").value(1))
            .andExpect(jsonPath("$[0].cantidad").value(2));

        verify(this.pedidoDetalleProductoService, times(1)).obtenerAllPedidosDetalleProducto();
    }

    private PedidoDetalleProducto crearPedidoDetalleProductoTest() {
        return PedidoDetalleProducto.builder()
            .detalleId(1L)
            .cantidad(2)
            .precioUnitario(BigDecimal.valueOf(2500))
            .build();
    }
}
