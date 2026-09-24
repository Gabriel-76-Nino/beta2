package com.techfix.beta2.domain.agregados_produto.referencia;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "referencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Referencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Pessoa fornecedor;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private String referencia;

}
