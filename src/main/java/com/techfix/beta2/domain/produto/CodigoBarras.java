package com.techfix.beta2.domain.produto;

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
    private Produto produto;

    private String gtin;

    @ManyToOne
    @JoinColumn(name = "id")
    private UnidadeMedida unidadeMedida;

}
