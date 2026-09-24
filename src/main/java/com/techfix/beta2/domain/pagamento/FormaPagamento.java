package com.techfix.beta2.domain.pagamento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "formas_pagamento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFpg;
    private String descricaoFpg;

    @OneToMany(mappedBy = "idFpg")
    private List<CondicaoPagamento> condicaoPagamento;

}
