package com.techfix.beta2.domain.agregados_produto.codigoBarras;

import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.agregados_produto.unidadeMedida.UnidadeMedida;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "codigos_barras")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodigoBarras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private String gtin;

    @ManyToOne
    @JoinColumn(name = "id_unidade_medida")
    private UnidadeMedida unidadeMedida;

}
