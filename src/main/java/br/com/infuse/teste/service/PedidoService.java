package br.com.infuse.teste.service;

import br.com.infuse.teste.domain.request.RequestPedidoDto;
import br.com.infuse.teste.domain.response.ResponsePedidoDto;
import br.com.infuse.teste.entity.Cliente;
import br.com.infuse.teste.entity.Pedido;
import br.com.infuse.teste.exception.NotFoundException;
import br.com.infuse.teste.exception.ValidacaoException;
import br.com.infuse.teste.mapper.PedidoMapper;
import br.com.infuse.teste.repository.ClienteRepository;
import br.com.infuse.teste.repository.PedidoRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    public ResponseEntity<Void> inserirPedidos(List<RequestPedidoDto> pedidoDtoList) {
        if(pedidoDtoList != null && pedidoDtoList.size() > 10) {
            throw new ValidacaoException("A lista tem que possuir até 10 pedidos");
        }
        if(pedidoDtoList == null || pedidoDtoList.size() == 0) {
            throw new ValidacaoException("Nenhum pedido para processar");
        }
        pedidoDtoList.forEach(pedido -> {
            if(validarCamposObrigarios(pedido) && !validarPedidoNumeroCadastrado(pedido.getNumeroControle()) && validarClienteExistente(pedido.getClienteId())) {
                Pedido pedidoDb = PedidoMapper.INSTANCE.covert(pedido);
                pedidoDb.setValorTotal(pedido.getValorTotalDesconto());
                pedidoDb.setDataCadastro(ObjectUtils.isEmpty(pedido.getDataCadastro()) ? LocalDate.now() : pedido.getDataCadastro());
                pedidoDb.setQuantidade(ObjectUtils.isEmpty(pedido.getQuantidade()) || pedido.getQuantidade() == 0 ? 1 : pedido.getQuantidade());
                repository.save(pedidoDb);
            }
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Boolean validarCamposObrigarios(RequestPedidoDto pedido) {
        if(ObjectUtils.isEmpty(pedido.getNumeroControle()) || ObjectUtils.isEmpty(pedido.getNomeProduto()) ||
                ObjectUtils.isEmpty(pedido.getValor()) || ObjectUtils.isEmpty(pedido.getClienteId())) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private Boolean validarClienteExistente(Long clienteId) {
        Cliente clientes = clienteRepository.findById(clienteId).orElse(null);

        if(clientes == null) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private Boolean validarPedidoNumeroCadastrado(Long numero) {
        List<Pedido> pedidos = repository.findByNumeroControle(numero).orElse(null);

        if(pedidos == null || pedidos.size() == 0) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }


    public List<ResponsePedidoDto> buscarPedidos(Long numeroControle, LocalDate dataCadastro) {
        List<ResponsePedidoDto> pedidoDtoList;
        List<Pedido> pedidoList = new ArrayList<>();
        if(ObjectUtils.isEmpty(numeroControle) && ObjectUtils.isEmpty(dataCadastro)) {
            pedidoList = repository.findAll();
        } else if (ObjectUtils.isNotEmpty(numeroControle) && ObjectUtils.isNotEmpty(dataCadastro)) {
            pedidoList = repository.findByDataCadastroAndNumeroControle(dataCadastro, numeroControle)
                    .orElse(null);;
        } else if (ObjectUtils.isEmpty(dataCadastro) && ObjectUtils.isNotEmpty(numeroControle) && numeroControle > 0) {
            pedidoList = repository.findByNumeroControle(numeroControle).orElse(null);
        } else if (ObjectUtils.isEmpty(numeroControle) && ObjectUtils.isNotEmpty(dataCadastro)) {
            pedidoList = repository.findByDataCadastro(dataCadastro).orElse(null);
        }

        if(pedidoList == null || pedidoList.isEmpty()) {
            throw new NotFoundException("Nenhum registro encontrado");
        }

        pedidoDtoList = pedidoList.stream().map(pedido -> PedidoMapper.INSTANCE.toResponseDto(pedido)).toList();
        return pedidoDtoList;
    }
}
