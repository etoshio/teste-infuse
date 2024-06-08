package br.com.infuse.teste.domain.request;

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
public class RequestPedidoDto {
    private Long numeroControle;
    private LocalDate dataCadastro;
    private String nomeProduto;
    private BigDecimal valor;
    private Integer quantidade = 1;
    private Long clienteId;

    public BigDecimal getValorTotalDesconto() {
        BigDecimal total = this.valor.multiply(BigDecimal.valueOf(quantidade));
        return calcularDesconto(total);
    }

    private BigDecimal calcularDesconto(BigDecimal total) {
        if (this.quantidade >= 10) {
            return total.subtract(total.multiply(BigDecimal.valueOf(0.10)));
        } else if (this.quantidade > 5) {
            return total.subtract(total.multiply(BigDecimal.valueOf(0.05)));
        } else {
            return total;
        }
    }
}
