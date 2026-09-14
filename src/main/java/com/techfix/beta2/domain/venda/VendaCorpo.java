package com.techfix.beta2.domain.venda;

import com.techfix.beta2.domain.produto.Produto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "vendas_corpo")
public class VendaCorpo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_venda_cabecalho")
    private VendaCabecalho cabecalho;
    private int numeroItem;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private int quantidade;
    private BigDecimal precoVenda;
    private BigDecimal valorDesconto;
    private Float percentualDesconto;
    private BigDecimal custoMedioVenda;
    private float margemVenda;

}
