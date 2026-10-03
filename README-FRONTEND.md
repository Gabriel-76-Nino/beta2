# TechFix ERP · Frontend beta 0.1

Interface inspirada no SAP Fiori, com cabeçalho azul, área de trabalho com aplicações, tabelas compactas, filtros, paginação, detalhes e formulários. Não utiliza componentes oficiais SAP nem pretende reproduzir todas as funcionalidades do S/4HANA.

## Executar com o backend

1. Use o Java 21 ou superior, conforme `pom.xml`, e configure o PostgreSQL que seu backend já utiliza.
2. Execute `./mvnw spring-boot:run` ou inicie `Beta2Application` no IntelliJ.
3. Acesse **http://localhost:8080/beta/index.html**. Se alterou a porta do Spring, ajuste o endereço.

Os arquivos estão em `src/main/resources/static/beta`. O próprio Spring Boot serve a interface, que chama as APIs no mesmo servidor. Não é necessário npm, build de frontend nem configuração de CORS. Essa escolha reduz os passos para testar a primeira beta; uma futura migração para React/TypeScript pode ser discutida sem alterar os contratos da API.

## Experimentar sem banco

```bash
python3 -m http.server 8765 --directory src/main/resources/static
```

Abra **http://localhost:8765/beta/index.html?demo=1**. O aviso de demonstração permanece visível; os dados são fictícios e as alterações ficam apenas em memória até recarregar. Essa opção permite avaliar a interface e não comprova a execução das regras do backend. Não abra o HTML via `file://`, pois ele usa módulos JavaScript.

## Operações implementadas

| Área | API utilizada | Operações |
| --- | --- | --- |
| OS | `/os` | Criar, consultar abertas, filtrar, exportar CSV, ver dados de entrada |
| Técnico | `PUT /os/tecnico` | Atribuir usuário técnico/admin ativo |
| Diagnóstico | `PUT /os/diagnostico` | Registrar diagnóstico e confirmação do relato |
| Orçamento | `POST /os/orcamento` | Adicionar itens, preços de peça/mão de obra e situação inicial por item |
| Status da OS | `POST /os/statusos` | Selecionar valores do enum `StatusOS` |
| Pessoas | `/pessoa` | Consultar e cadastrar clientes, funcionários e fornecedores |
| Produtos | `/produto` | Consultar e cadastrar, selecionando referências existentes |
| Estoque | `GET /produto` | Consultar saldo, mínimo/máximo e destacar abaixo do mínimo |
| Venda | `POST /venda` | Cadastrar cliente, vendedor, OS, itens e uma condição de pagamento |
| Aparelhos | `/aparelho` | Consultar e cadastrar |
| Usuários | `/usuario` | Consultar e cadastrar; não implementa login |
| Auxiliares | `/marca`, `/departamento`, `/categoria`, `/subcategoria`, `/garantia`, `/unidade_medida`, `/codigo_barras`, `/referencia`, `/compatibildade`, `/forma_pagamento`, `/condicao`, `/movimentacao` | Consultar e cadastrar |

Preservadas as grafias atuais da API: `/compatibildade`, `quatidadeParcelas` e `nacimentoFundacao`. O cadastro de produto envia nomes de marca/departamento/categoria/subcategoria/fornecedor e o código da unidade, pois o serviço resolve por esses campos. Garantia é enviada por ID. Usuários vinculados à OS e venda são enviados pelo nome de usuário. Todos os valores monetários da interface usam apresentação em reais.

## Limitações e decisões pendentes

- A API de OS lista somente ordens abertas e dados de entrada. Não há GET de detalhes, consulta de encerradas ou histórico. As respostas de alterações mostram os campos retornados naquele momento; não são persistidas pelo frontend.
- Cadastro de orçamento adiciona itens. A API não oferece atualização posterior da aprovação nem remoção dos itens. A numeração enviada começa em 1 em cada inclusão; evitar inclusões repetidas enquanto o backend não resolver a numeração global por OS.
- Venda exige OS porque `VendaCabecalhoService` busca esse vínculo incondicionalmente. Ainda não há GET de vendas. Esta beta permite apenas uma condição de pagamento; múltiplas formas exigem definição dos valores rateados no backend.
- Descontos são enviados zerados: o backend calcula total como quantidade × preço e não aplica descontos. Valores de custo/margem são campos informados no item; precificação automática e estoque não são recalculados no navegador.
- Estoque é consulta. O cadastro de tipos de movimentação não registra entrada/saída de mercadorias. Ainda faltam endpoints próprios para ajustes, financeiro, compras e relatórios.
- Métodos de tabela de descontos não têm `@GetMapping`/`@PostMapping`. Não são expostos como recursos prontos.
- Fotos aceitam endereço textual; não existe upload de arquivos.
- Ainda precisamos definir autenticação, permissões por função, histórico/aprovação de OS, venda de balcão sem OS, impressão e publicação. O repositório não possui autenticação implementada; esta beta deve ser experimentada no ambiente de desenvolvimento.
- Seletores de relações dependem dos cadastros auxiliares. Comece por pessoas e usuários, marcas/departamentos/categorias/subcategorias, unidades/garantias e pagamentos, depois produtos e OS.

## Ajustes mínimos no backend

`OrdemServicoService` agora trata ID nulo de aparelho do cliente como cadastro novo, sem chamar `findById(null)`. A conversão de detalhes retorna técnico nulo quando ainda não foi atribuído, permitindo alterar status sem técnico. Dois testes de regressão acompanham essas mudanças.

## Verificação

```bash
node --test frontend-tests/*.test.mjs
./mvnw -Dtest=OrdemServicoServiceTest test
```

Nesta implementação passaram 10 testes Node: conversões, validação de OS/orçamento/venda/pagamento, erros HTTP/JSON, escape de HTML e renderização programática das telas/formulários com um DOM simulado. O DOM simulado não substitui verificação em navegador.

A compilação/testes Java e execução com banco não puderam ser concluídos neste ambiente: Maven Central ficou inacessível e o Java disponível é 17, abaixo do 21 declarado no projeto. A instalação do Chromium também falhou por download incompleto; layout, responsividade e interações reais precisam de conferência em navegador no ambiente local.

Sugestão de teste manual: cadastrar uma pessoa e usuário; cadastrar auxiliares e produto; abrir uma OS com aparelho novo (ID vazio); atribuir técnico; diagnosticar; adicionar orçamento; mudar status; cadastrar uma condição com tantos vencimentos quanto parcelas; cadastrar venda vinculada à OS. Conferir os registros no banco e o saldo após a venda.
