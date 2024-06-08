package br.com.infuse.teste.repository;

import br.com.infuse.teste.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
