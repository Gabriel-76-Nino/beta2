export const records = {
  "FormaPagamentoDto": [
    {
      "type": "Long",
      "name": "idFpg"
    },
    {
      "type": "String",
      "name": "descricaoFpg"
    }
  ],
  "FormaPagamentoECondicaoDto": [
    {
      "type": "Long",
      "name": "idFpg"
    },
    {
      "type": "Long",
      "name": "idCondicao"
    }
  ],
  "DiasParaCondicaoPagamentoDto": [
    {
      "type": "Integer",
      "name": "dias"
    }
  ],
  "CondicaoPagamentoDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "idFpg"
    },
    {
      "type": "String",
      "name": "condicao"
    },
    {
      "type": "Integer",
      "name": "quatidadeParcelas"
    },
    {
      "type": "List<DiasParaCondicaoPagamentoDto>",
      "name": "dias"
    }
  ],
  "DiasCondicaoPagamentoDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "idCondicao"
    },
    {
      "type": "Integer",
      "name": "dias"
    }
  ],
  "UsuarioDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "nomeUsuario"
    },
    {
      "type": "Long",
      "name": "pessoa"
    },
    {
      "type": "Funcao",
      "name": "funcao"
    },
    {
      "type": "Boolean",
      "name": "ativo"
    }
  ],
  "CalcularParcelas": [
    {
      "type": "Long",
      "name": "idFpg"
    },
    {
      "type": "Long",
      "name": "idCondicao"
    },
    {
      "type": "Integer",
      "name": "numeroParcela"
    },
    {
      "type": "Double",
      "name": "valorParcela"
    },
    {
      "type": "LocalDateTime",
      "name": "vencimento"
    },
    {
      "type": "Integer",
      "name": "quantidadeTotalParcelas"
    }
  ],
  "TabelaDescontoDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "usuario"
    },
    {
      "type": "Long",
      "name": "departamento"
    },
    {
      "type": "Long",
      "name": "categoria"
    },
    {
      "type": "Long",
      "name": "subcategoria"
    },
    {
      "type": "Long",
      "name": "formaPagamento"
    },
    {
      "type": "Long",
      "name": "produto"
    },
    {
      "type": "Double",
      "name": "desconto"
    }
  ],
  "VendaCorpoDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "idVendaCabecalho"
    },
    {
      "type": "Integer",
      "name": "numeroItem"
    },
    {
      "type": "Long",
      "name": "produto"
    },
    {
      "type": "Integer",
      "name": "quantidade"
    },
    {
      "type": "Double",
      "name": "precoVenda"
    },
    {
      "type": "BigDecimal",
      "name": "valorDesconto"
    },
    {
      "type": "Double",
      "name": "percentualDesconto"
    },
    {
      "type": "BigDecimal",
      "name": "custoMedioVenda"
    },
    {
      "type": "Double",
      "name": "margemVenda"
    },
    {
      "type": "Double",
      "name": "valorTotalProduto"
    }
  ],
  "VendaCabecalhoDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "numeroNota"
    },
    {
      "type": "Long",
      "name": "cliente"
    },
    {
      "type": "LocalDateTime",
      "name": "dataVenda"
    },
    {
      "type": "String",
      "name": "nomeUsuario"
    },
    {
      "type": "Long",
      "name": "os"
    },
    {
      "type": "List<FormaPagamentoECondicaoDto>",
      "name": "fpgs"
    },
    {
      "type": "List<VendaCorpoDto>",
      "name": "itens"
    }
  ],
  "AparelhoClienteDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "produto"
    },
    {
      "type": "TipoAparelho",
      "name": "tipoAparelho"
    },
    {
      "type": "Map<String, Object>",
      "name": "especificacoes"
    },
    {
      "type": "String",
      "name": "imei"
    },
    {
      "type": "Long",
      "name": "cliente"
    }
  ],
  "AparelhoDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "produto"
    },
    {
      "type": "TipoAparelho",
      "name": "tipoAparelho"
    },
    {
      "type": "CondicaoAparelho",
      "name": "condicaoAparelho"
    },
    {
      "type": "Map<String, Object>",
      "name": "especificacoes"
    },
    {
      "type": "String",
      "name": "imei"
    },
    {
      "type": "String",
      "name": "observacoes"
    },
    {
      "type": "String",
      "name": "pecasTrocadas"
    },
    {
      "type": "String",
      "name": "statusConservacao"
    }
  ],
  "TipoMovimentacaoDto": [
    {
      "type": "Long",
      "name": "codigoMovimentacao"
    },
    {
      "type": "String",
      "name": "descricaoMovimentacao"
    },
    {
      "type": "Operacao",
      "name": "operacao"
    }
  ],
  "CadastroPessoaDto": [
    {
      "type": "String",
      "name": "nome"
    },
    {
      "type": "String",
      "name": "razaoSocial"
    },
    {
      "type": "String",
      "name": "cpfCnpj"
    },
    {
      "type": "String",
      "name": "email"
    },
    {
      "type": "LocalDate",
      "name": "nacimentoFundacao"
    },
    {
      "type": "String",
      "name": "telefoneTitular"
    },
    {
      "type": "String",
      "name": "logradouro"
    },
    {
      "type": "String",
      "name": "numero"
    },
    {
      "type": "String",
      "name": "bairro"
    },
    {
      "type": "String",
      "name": "complemento"
    },
    {
      "type": "String",
      "name": "cidade"
    },
    {
      "type": "String",
      "name": "uf"
    },
    {
      "type": "String",
      "name": "cep"
    },
    {
      "type": "String",
      "name": "nomeSegundaPessoa"
    },
    {
      "type": "String",
      "name": "telefoneSegundaPessoa"
    },
    {
      "type": "Boolean",
      "name": "cliente"
    },
    {
      "type": "Boolean",
      "name": "funcionario"
    },
    {
      "type": "Boolean",
      "name": "fornecedor"
    },
    {
      "type": "String",
      "name": "siteFornecedor"
    }
  ],
  "PessoaDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "nome"
    },
    {
      "type": "String",
      "name": "cpfCnpj"
    },
    {
      "type": "String",
      "name": "email"
    },
    {
      "type": "Boolean",
      "name": "cliente"
    },
    {
      "type": "Boolean",
      "name": "funcionario"
    },
    {
      "type": "Boolean",
      "name": "fornecedor"
    }
  ],
  "ProdutoDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "descricao"
    },
    {
      "type": "String",
      "name": "descricaoComercial"
    },
    {
      "type": "Tipo",
      "name": "tipo"
    },
    {
      "type": "Long",
      "name": "marca"
    },
    {
      "type": "Long",
      "name": "departamento"
    },
    {
      "type": "Long",
      "name": "categoria"
    },
    {
      "type": "Long",
      "name": "subcategoria"
    },
    {
      "type": "Long",
      "name": "fornecedor"
    },
    {
      "type": "Double",
      "name": "margem"
    },
    {
      "type": "BigDecimal",
      "name": "custoMedio"
    },
    {
      "type": "Double",
      "name": "precoVenda"
    },
    {
      "type": "BigDecimal",
      "name": "precoSugestao"
    },
    {
      "type": "Integer",
      "name": "minimo"
    },
    {
      "type": "Integer",
      "name": "maximo"
    },
    {
      "type": "Integer",
      "name": "estoque"
    },
    {
      "type": "Long",
      "name": "garantia"
    },
    {
      "type": "Long",
      "name": "unidadeMedida"
    },
    {
      "type": "String",
      "name": "foto"
    },
    {
      "type": "Boolean",
      "name": "ativo"
    }
  ],
  "CadastroProdutoDto": [
    {
      "type": "String",
      "name": "descricao"
    },
    {
      "type": "String",
      "name": "descricaoComercial"
    },
    {
      "type": "Tipo",
      "name": "tipo"
    },
    {
      "type": "String",
      "name": "marca"
    },
    {
      "type": "String",
      "name": "departamento"
    },
    {
      "type": "String",
      "name": "categoria"
    },
    {
      "type": "String",
      "name": "subcategoria"
    },
    {
      "type": "String",
      "name": "fornecedor"
    },
    {
      "type": "String",
      "name": "codigoBarras"
    },
    {
      "type": "String",
      "name": "referencia"
    },
    {
      "type": "Double",
      "name": "margem"
    },
    {
      "type": "Integer",
      "name": "minimo"
    },
    {
      "type": "Integer",
      "name": "maximo"
    },
    {
      "type": "Long",
      "name": "garantia"
    },
    {
      "type": "String",
      "name": "unidadeMedida"
    },
    {
      "type": "String",
      "name": "foto"
    }
  ],
  "AtribuirDiagnostico": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "diagnosticoTecnico"
    },
    {
      "type": "Boolean",
      "name": "diagnosticoIgualRelato"
    }
  ],
  "ListagemOSAberto": [],
  "DadosOSCadastro": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "pessoa"
    },
    {
      "type": "Long",
      "name": "idAparelhoCliente"
    },
    {
      "type": "AparelhoClienteDto",
      "name": "aparelhoClienteDto"
    },
    {
      "type": "String",
      "name": "problemaRelatado"
    },
    {
      "type": "String",
      "name": "acessoriosCliente"
    },
    {
      "type": "String",
      "name": "atendenteRecebeu"
    },
    {
      "type": "StatusOS",
      "name": "statusOS"
    }
  ],
  "DadosAtribuirTecnico": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "tecnicoResponsavel"
    }
  ],
  "TodosDadosOS": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "idCliente"
    },
    {
      "type": "LocalDateTime",
      "name": "dataEntrada"
    },
    {
      "type": "AparelhoClienteDto",
      "name": "aparelhoClienteDto"
    },
    {
      "type": "String",
      "name": "problemaRelatado"
    },
    {
      "type": "String",
      "name": "fotoEntrada"
    },
    {
      "type": "String",
      "name": "acessoriosCliente"
    },
    {
      "type": "String",
      "name": "atendenteRecebeu"
    },
    {
      "type": "String",
      "name": "tecnicoResponsavel"
    },
    {
      "type": "String",
      "name": "diagnosticoTecnico"
    },
    {
      "type": "Boolean",
      "name": "diagnosticoIgualRelato"
    },
    {
      "type": "DadosCadastroOrcamento",
      "name": "orcamento"
    },
    {
      "type": "StatusOS",
      "name": "statusOS"
    },
    {
      "type": "String",
      "name": "testesRealizados"
    },
    {
      "type": "String",
      "name": "cpfRetirante"
    },
    {
      "type": "String",
      "name": "nomeRetirante"
    },
    {
      "type": "LocalDateTime",
      "name": "dataRetirada"
    }
  ],
  "AlterarStatusOS": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "StatusOS",
      "name": "statusOS"
    }
  ],
  "DadosCadastroOrcamento": [
    {
      "type": "Long",
      "name": "ordemServico"
    },
    {
      "type": "List<ListaProdutosCadastroDto>",
      "name": "listaItens"
    }
  ],
  "ListaProdutosCadastroDto": [
    {
      "type": "Integer",
      "name": "numeroItem"
    },
    {
      "type": "Long",
      "name": "idProduto"
    },
    {
      "type": "String",
      "name": "descricao"
    },
    {
      "type": "Integer",
      "name": "quantidade"
    },
    {
      "type": "BigDecimal",
      "name": "precoPeca"
    },
    {
      "type": "BigDecimal",
      "name": "precoMaoObra"
    },
    {
      "type": "StatusItemOrcamento",
      "name": "statusItemOrcamento"
    },
    {
      "type": "String",
      "name": "observacoes"
    }
  ],
  "CodigoBarrasDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "produto"
    },
    {
      "type": "String",
      "name": "gtin"
    },
    {
      "type": "String",
      "name": "unidadeMedida"
    }
  ],
  "MarcaDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "nomeMarca"
    }
  ],
  "ReferenciaDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "fornecedor"
    },
    {
      "type": "Long",
      "name": "produto"
    },
    {
      "type": "String",
      "name": "referencia"
    }
  ],
  "DepartamentoDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "nomeDepartamento"
    }
  ],
  "UnidadeMedidaDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "codigo"
    },
    {
      "type": "String",
      "name": "descricao"
    },
    {
      "type": "Integer",
      "name": "multiplicador"
    }
  ],
  "SubcategoriaDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "nomeSubcategoria"
    },
    {
      "type": "Long",
      "name": "departamento"
    },
    {
      "type": "Long",
      "name": "categoria"
    }
  ],
  "GarantiaDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "fornecedor"
    },
    {
      "type": "String",
      "name": "detalhesGarantia"
    },
    {
      "type": "Integer",
      "name": "tempoGarantiaDias"
    }
  ],
  "CategoriaDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "String",
      "name": "nomeCategoria"
    },
    {
      "type": "Long",
      "name": "departamento"
    }
  ],
  "CompatibilidadeDto": [
    {
      "type": "Long",
      "name": "id"
    },
    {
      "type": "Long",
      "name": "produto"
    },
    {
      "type": "String",
      "name": "compativel"
    }
  ]
};
export const enums = {
  "Funcao": [
    "ADMIN",
    "CAIXA",
    "TECNICO"
  ],
  "StatusReq": [
    "REQUISICAO_CRIADA",
    "PEDIDO_CRIADO",
    "RECEBIDO"
  ],
  "StatusParcela": [
    "PAGO",
    "EM_ABERTO",
    "VENCIDO"
  ],
  "Tipo": [
    "APARELHO",
    "ACESSORIO",
    "PECA",
    "INSUMO",
    "IMOBILIZADO"
  ],
  "CondicaoAparelho": [
    "NOVO",
    "USADO",
    "RECONDICIONADO"
  ],
  "TipoAparelho": [
    "CELULAR",
    "NOTEBOOK",
    "COMPUTADOR",
    "VIDEO_GAME",
    "EQUIPAMENTO"
  ],
  "StatusOS": [
    "AGUARDANDO_ATENDIMENTO",
    "DIAGNOSTICO",
    "AGUARDANDO_APROVACAO",
    "REPROVADO",
    "APROVADO",
    "APROVADO_BALCAO",
    "AGUARDO_CHEGADA_PECAS",
    "AGUARDANDO_REPARO",
    "REPARO_CONCLUIDO",
    "RETIRADO"
  ],
  "StatusItemOrcamento": [
    "PENDENTE",
    "APROVADO",
    "REPROVADO"
  ],
  "Operacao": [
    "ENTRADA",
    "SAIDA"
  ]
};
