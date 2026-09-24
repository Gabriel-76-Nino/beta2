package com.techfix.beta2.domain.entrada;

import com.techfix.beta2.domain.contas_pagar.ContasPagar;
import com.techfix.beta2.domain.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "entradas_notas_fiscais_cabecalho")
@NoArgsConstructor
@AllArgsConstructor
public class EntradaNotaFiscalCabecalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numeroNota;
    private String destinatario;
    private String chaveNota;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Pessoa fornecedor;

    private BigDecimal frete;

    @OneToMany(mappedBy = "id")
    private List<ContasPagar> contasPagar;

    @OneToMany(mappedBy = "notaCabecalho")
    private List<EntradaNotaFiscalCorpo> entradaNotaFiscalCorpos;

}
