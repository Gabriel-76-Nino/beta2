package com.techfix.beta2.domain.venda;


import com.techfix.beta2.domain.produto.Categoria;
import com.techfix.beta2.domain.produto.Departamento;
import com.techfix.beta2.domain.produto.Produto;
import com.techfix.beta2.domain.produto.Subcategoria;
import com.techfix.beta2.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tabelas_descontos")
@NoArgsConstructor
@AllArgsConstructor
public class TabelaDesconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nome_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_subcategoria")
    private Subcategoria subcategoria;

    @ManyToOne
    @JoinColumn(name = "id_fpg")
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private Float desconto;

}
