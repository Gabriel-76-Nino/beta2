package com.techfix.beta2.domain.produto;

import com.techfix.beta2.domain.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "garantia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garatia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Pessoa fornecedor;
    private String detalhesGarantia;
    private int tempoGarantiaDias;


}
