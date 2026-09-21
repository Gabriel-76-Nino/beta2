package com.techfix.beta2.domain.agregadosProduto.compatibilidade;

import com.techfix.beta2.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "compatibilidades")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Compatibilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    private String compativel;

}
