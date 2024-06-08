package br.com.infuse.teste.controller;

import br.com.infuse.teste.domain.request.RequestPedidoDto;
import br.com.infuse.teste.domain.response.ResponsePedidoDto;
import br.com.infuse.teste.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity inserirPedidos(@RequestBody @Valid List<RequestPedidoDto> pedidoDtoList){
        return service.inserirPedidos(pedidoDtoList);
    }

    @GetMapping("/filtro")
    public List<ResponsePedidoDto> buscarPedidosFiltros(@RequestParam(name="numeroControle", required = false) Long numeroControle,
                                                        @RequestParam(name="dataCadastro", required = false)
                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataCadastro) {

        return service.buscarPedidos(numeroControle, dataCadastro);

    }
}
