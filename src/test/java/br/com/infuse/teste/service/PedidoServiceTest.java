package br.com.infuse.teste.service;

import br.com.infuse.teste.domain.request.RequestPedidoDto;
import br.com.infuse.teste.domain.response.ResponsePedidoDto;
import br.com.infuse.teste.entity.Pedido;
import br.com.infuse.teste.exception.NotFoundException;
import br.com.infuse.teste.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PedidoServiceTest {
    @Mock
    PedidoRepository repository;
    @InjectMocks
    PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInserirPedido() {
        when(repository.findByNumeroControle(anyLong())).thenReturn(Optional.empty());

        ResponseEntity result = pedidoService.inserirPedidos(List.of(new RequestPedidoDto(1L,
                LocalDate.now(), "Produto", BigDecimal.TEN, 0, 1L)));
        Assertions.assertNotNull( result);
    }

    @Test
    void testInserirPedido_NumeroControleJaCadastrado() {
        when(repository.findByNumeroControle(anyLong())).thenReturn(Optional.of(List.of(mock(Pedido.class))));

        ResponseEntity result = pedidoService.inserirPedidos(List.of(new RequestPedidoDto(1L,
                LocalDate.now(), "Produto", BigDecimal.TEN, 0, 1L)));
        Assertions.assertNotNull( result);
    }

    @Test
    void testInserirPedido_MaiorQueCincoQuantidade() {
        when(repository.findByNumeroControle(anyLong())).thenReturn(Optional.empty());

        ResponseEntity result = pedidoService.inserirPedidos(List.of(new RequestPedidoDto(1L,
                LocalDate.now(), "Produto", BigDecimal.TEN, 6, 1L)));
        Assertions.assertNotNull( result);
    }


    @Test
    void testInserirPedido_DezQuantidade() {
        when(repository.findByNumeroControle(anyLong())).thenReturn(Optional.empty());

        ResponseEntity result = pedidoService.inserirPedidos(List.of(new RequestPedidoDto(1L,
                LocalDate.now(), "Produto", BigDecimal.TEN, 10, 1L)));
        Assertions.assertNotNull( result);
    }

    @Test
    void testBuscarPedidos_BuscaPorNumeroControleDataCadastro() {
        when(repository.findByDataCadastroAndNumeroControle(any(), anyLong())).thenReturn(Optional.of(List.of(mock(Pedido.class))));
        List<ResponsePedidoDto> result =  pedidoService.buscarPedidos(Long.valueOf(1), LocalDate.now());
        Assertions.assertNotNull(result);
    }

    @Test
    void testBuscarPedidos_BuscaPorDataCadastro() {
        when(repository.findByDataCadastro(any())).thenReturn(Optional.of(List.of(mock(Pedido.class))));
        List<ResponsePedidoDto> result =  pedidoService.buscarPedidos(null, LocalDate.now());
        Assertions.assertNotNull(result);
    }

    @Test
    void testBuscarPedidos_BuscaPorNumeroControle() {
        when(repository.findByNumeroControle(any())).thenReturn(Optional.of(List.of(mock(Pedido.class))));
        List<ResponsePedidoDto> result =  pedidoService.buscarPedidos(Long.valueOf(1), null);
        Assertions.assertNotNull(result);
    }


    @Test
    void testBuscarPedidos_NaoEncontrado() {
        when(repository.findByNumeroControle(anyLong())).thenReturn(Optional.empty());
        when(repository.findByDataCadastroAndNumeroControle(any(), anyLong())).thenReturn(Optional.empty());
        when(repository.findByDataCadastro(any())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            pedidoService.buscarPedidos(Long.valueOf(1), LocalDate.now());
        });
    }

    @Test
    void testBuscarPedidos_BuscaTodos() {
        when(repository.findAll()).thenReturn(List.of(mock(Pedido.class)));

        List<ResponsePedidoDto> result = pedidoService.buscarPedidos(null, null);
        Assertions.assertNotNull(result);
    }
}
