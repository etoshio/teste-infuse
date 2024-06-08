package br.com.infuse.teste.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name ="pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_controle")
    private Long numeroControle;
    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;
    @Column(name = "nome_produto")
    private String nomeProduto;
    private BigDecimal valor;
    private Integer quantidade;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
