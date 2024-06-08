package br.com.infuse.teste.controller;

import br.com.infuse.teste.domain.request.RequestPedidoDto;
import br.com.infuse.teste.domain.response.ResponseClienteDto;
import br.com.infuse.teste.domain.response.ResponsePedidoDto;
import br.com.infuse.teste.service.PedidoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class PedidoControllerTest {
    @Mock
    PedidoService service;
    @InjectMocks
    PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInserirPedidos() {
        when(service.inserirPedidoa(any())).thenReturn(ResponseEntity.ok().build());

        ResponseEntity result = pedidoController.inserirPedidos(List.of(new RequestPedidoDto(1L, LocalDate.now(),
                "nomeProduto", BigDecimal.TEN, 0, 1L)));
        Assertions.assertNotNull(result);
    }

    @Test
    void testBuscarPedidosFiltros() {
        when(service.buscarPedidos(anyLong(), any())).thenReturn(List.of(new ResponsePedidoDto(1L, LocalDate.now(),
                "nomeProduto", BigDecimal.TEN, 0, BigDecimal.ONE, new ResponseClienteDto(1L, "Cliente"))));

        List<ResponsePedidoDto> result = pedidoController.buscarPedidosFiltros(Long.valueOf(1), LocalDate.of(2024, Month.JUNE, 7));
        Assertions.assertNotNull(result);
    }
}
