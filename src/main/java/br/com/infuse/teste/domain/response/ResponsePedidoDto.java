package br.com.infuse.teste.domain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePedidoDto {

    private Long numeroControle;
    private LocalDate dataCadastro;
    private String nomeProduto;
    private BigDecimal valor;
    private Integer quantidade = 1;
    private BigDecimal valorTotal;
    private ResponseClienteDto cliente;
}
