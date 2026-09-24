package com.techfix.beta2.domain.pagamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "condicoes_pagamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondicaoPagamento {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_fpg")
    private FormaPagamento idFpg;

    private String condicao;
    private int quantidadeParcelas;

    @OneToMany(mappedBy = "idCondicao")
    private List<DiasCondicaoPagamento> DiasCondicaoPagamentos;
}
