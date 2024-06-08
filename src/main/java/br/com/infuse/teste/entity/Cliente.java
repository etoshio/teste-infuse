package br.com.infuse.teste.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
}
