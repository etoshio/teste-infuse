package br.com.infuse.teste.mapper;

import br.com.infuse.teste.domain.request.RequestPedidoDto;
import br.com.infuse.teste.domain.response.ResponsePedidoDto;
import br.com.infuse.teste.entity.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    RequestPedidoDto convert(Pedido entity);
    @Mapping(target = "cliente.id", source = "clienteId")
    Pedido covert(RequestPedidoDto dto);

    List<RequestPedidoDto> convert(List<Pedido> list);

    ResponsePedidoDto toResponseDto(Pedido pedido);
}
