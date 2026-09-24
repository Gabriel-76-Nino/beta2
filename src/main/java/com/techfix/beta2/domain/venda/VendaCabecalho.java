package com.techfix.beta2.domain.venda;

import com.techfix.beta2.domain.contas_receber.ContasReceber;
import com.techfix.beta2.domain.ordem_servico.OrdemServico;
import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "vendas_cabecalho")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VendaCabecalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numeroNota;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa cliente;

    private LocalDateTime dataVenda;
    private Double valorTotalVenda;

    @ManyToOne
    @JoinColumn(name = "nome_usuario")
    private Usuario nomeUsuario;

    @OneToOne(mappedBy = "notaVenda")
    @JoinColumn(name = "id_os")
    private OrdemServico os;

    @OneToMany(mappedBy = "vendaCabecalho")
    private List<ContasReceber> contasReceber;

    @OneToMany(mappedBy = "cabecalho")
    private List<VendaCorpo> itens;

    public VendaCabecalho(Pessoa cliente, Usuario usuario, OrdemServico os) {
        this.cliente = cliente;
        this.dataVenda = LocalDateTime.now();
        this.nomeUsuario = usuario;
        this.os = os;

    }




}
