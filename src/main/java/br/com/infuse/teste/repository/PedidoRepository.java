package br.com.infuse.teste.repository;

import br.com.infuse.teste.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<List<Pedido>> findByNumeroControle(Long numero);

    Optional<List<Pedido>> findByDataCadastroAndNumeroControle(LocalDate dataCadastro, Long numeroControle);

    Optional<List<Pedido>> findByDataCadastro(LocalDate dataCadastro);
}
