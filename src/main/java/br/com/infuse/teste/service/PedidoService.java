package br.com.infuse.teste.service;

import br.com.infuse.teste.domain.request.RequestPedidoDto;
import br.com.infuse.teste.domain.response.ResponsePedidoDto;
import br.com.infuse.teste.entity.Pedido;
import br.com.infuse.teste.exception.NotFoundException;
import br.com.infuse.teste.exception.ValidacaoException;
import br.com.infuse.teste.mapper.PedidoMapper;
import br.com.infuse.teste.repository.PedidoRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public ResponseEntity inserirPedidoa(List<RequestPedidoDto> pedidoDtoList) {
        if(pedidoDtoList != null && pedidoDtoList.size() > 10) {
            throw new ValidacaoException("A lista tem que possuir até 10 pedidos");
        }
        if(pedidoDtoList == null || pedidoDtoList.size() == 0) {
            throw new ValidacaoException("Nenhum pedido para processar");
        }
        pedidoDtoList.forEach(pedido -> {
            if(!validarPedidoNumeroCadastrado(pedido.getNumeroControle())) {
                Pedido pedidoDb = PedidoMapper.INSTANCE.covert(pedido);
                pedidoDb.setValorTotal(pedido.getValorTotalDesconto());
                pedidoDb.setQuantidade(pedido.getQuantidade() == null || pedido.getQuantidade() == 0 ? 1 : pedido.getQuantidade());
                repository.save(pedidoDb);
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Boolean validarPedidoNumeroCadastrado(Long numero) {
        List<Pedido> pedido = repository.findByNumeroControle(numero).orElse(null);

        if(pedido == null || pedido.size() == 0) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }


    public List<ResponsePedidoDto> buscarPedidos(Long numeroControle, LocalDate dataCadastro) {
        List<ResponsePedidoDto> pedidoDtoList;
        List<Pedido> pedidoList = new ArrayList<>();
        if(ObjectUtils.isEmpty(numeroControle) && ObjectUtils.isEmpty(dataCadastro)) {
            pedidoList = repository.findAll();
        } else if (dataCadastro != null && numeroControle != null) {
            pedidoList = repository.findByDataCadastroAndNumeroControle(dataCadastro, numeroControle)
                    .orElse(null);;
        } else if (dataCadastro == null && numeroControle != null && numeroControle > 0) {
            pedidoList = repository.findByNumeroControle(numeroControle).orElse(null);
        } else if (numeroControle == null && dataCadastro != null) {
            pedidoList = repository.findByDataCadastro(dataCadastro).orElse(null);
        }

        if(pedidoList == null || pedidoList.isEmpty()) {
            throw new NotFoundException("Nenhum registro encontrado");
        }

        pedidoDtoList = pedidoList.stream().map(pedido -> PedidoMapper.INSTANCE.toResponseDto(pedido))
                .collect(Collectors.toList());
        return pedidoDtoList;
    }
}
