package com.techfix.beta2.domain.agregados_produto.garantia;

import com.techfix.beta2.domain.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "garantias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garantia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Pessoa fornecedor;

    private String detalhesGarantia;
    private int tempoGarantiaDias;


}
