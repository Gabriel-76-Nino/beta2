package com.techfix.beta2.domain.pagamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dias_condicoes_pagamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiasCondicaoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_condicao")
    private CondicaoPagamento idCondicao;

    private int dias;

}
