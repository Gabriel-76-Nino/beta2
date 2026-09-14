package com.techfix.beta2.domain.venda;

import jakarta.persistence.*;

@Entity
@Table(name = "formas_pagamento")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFpg;
    private String descricaoFpg;


}
