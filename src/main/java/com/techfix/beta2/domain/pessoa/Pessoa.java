package com.techfix.beta2.domain.pessoa;

import com.techfix.beta2.domain.endereco.Endereco;
import com.techfix.beta2.domain.ordemservico.OrdemServico;
import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.produto.Referencia;
import com.techfix.beta2.domain.venda.VendaCabecalho;
import jakarta.persistence.*;
import lombok.*;

import java.lang.ref.Reference;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pessoas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String razaoSocial;
    private String cpfCnpj;
    private String email;
    private LocalDate nacimentoFundacao;
    private String telefoneTitular;

    @Embedded
    private Endereco endereco;

    private String nomeSegundaPessoa;
    private String telefoneSegundaPessoa;
    private Boolean cliente;
    private Boolean funcionario;
    private Boolean fornecedor;
    private String siteFornecedor;

    @OneToMany(mappedBy = "pessoaId")
    private List<OrdemServico> ordemServico;

    @OneToMany(mappedBy = "fornecedor")
    private List<Produto> produtos;

    @OneToMany(mappedBy = "cliente")
    private List<VendaCabecalho> vendasCliente;



}
