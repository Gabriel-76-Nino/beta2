package com.techfix.beta2.domain.usuario;

import com.techfix.beta2.domain.pessoa.Pessoa;
import com.techfix.beta2.domain.venda.VendaCabecalho;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeUsuario;

    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    private Funcao funcao;

    @OneToMany(mappedBy = "nomeUsuario")
    private List<VendaCabecalho> vendas;

    private Boolean ativo;

}
