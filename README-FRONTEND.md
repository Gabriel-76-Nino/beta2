# TechFix ERP · Frontend beta 0.1

Interface inspirada no SAP Fiori, com cabeçalho azul, área de trabalho com aplicações, tabelas compactas, filtros, paginação, detalhes e formulários. Não utiliza componentes oficiais SAP nem pretende reproduzir todas as funcionalidades do S/4HANA.

## Dois projetos independentes

| Projeto | Local | Execução | Endereço padrão |
| --- | --- | --- | --- |
| Backend Java/Spring Boot | `src/` + `pom.xml` na raiz | IntelliJ ou `./mvnw spring-boot:run` | `http://localhost:8080` |
| Frontend JavaScript | `frontend/` | `npm run dev` dentro de `frontend/` | `http://127.0.0.1:5173` |

O frontend não está mais em `src/main/resources/static` nem é empacotado no JAR. Tem seus próprios arquivos, configuração, scripts e testes. Pode ser copiado integralmente para outra pasta ou repositório e executado sem o código Java. Por enquanto os dois projetos são versionados no mesmo repositório, mas são independentes em execução e entrega.

O frontend não acessa o banco. Faz requisições HTTP para `/api/...` em seu próprio servidor; um proxy encaminha essas chamadas ao endereço `BACKEND_URL` do Spring Boot. Isso preserva os endpoints e evita precisar de CORS para a configuração inicial. Não há regras de negócio nem persistência no servidor do frontend.

## Executar com o backend

1. Inicie o backend no IntelliJ ou execute `./mvnw spring-boot:run` na raiz, usando Java 21 ou superior e o PostgreSQL já configurado no projeto.
2. Em outro terminal:

```bash
cd frontend
cp .env.example .env
npm run dev
```

3. Abra **http://127.0.0.1:5173**.

Requer Node.js 22 ou superior. Não há dependências npm externas: não é necessário `npm install`. O arquivo `.env` pode definir `BACKEND_URL`, `PORT` e `HOST`; o `.env.example` documenta os valores padrão. Reinicie o frontend depois de mudar o `.env`.

Exemplo com o backend em outro computador:

```dotenv
BACKEND_URL=http://192.168.0.6:8080
PORT=5173
HOST=127.0.0.1
```

`BACKEND_URL` é resolvido pelo servidor do frontend, e não pelo navegador. Para disponibilizar o frontend na rede local, use `HOST=0.0.0.0` e acesse o IP do computador que executa o frontend. A autenticação ainda é uma decisão pendente do ERP.

## Experimentar sem backend ou banco

```bash
cd frontend
npm run dev
```

Abra **http://127.0.0.1:5173/?demo=1**. O aviso de demonstração permanece visível; os dados são fictícios e as alterações ficam apenas em memória até recarregar. A interface pode abrir mesmo com o backend desligado. Esse modo não comprova as regras do backend.

## Entrega separada

`npm start` executa o servidor independente sem modo watch. A pasta `frontend/` contém tudo que ele precisa. Configuração e ciclo de execução do Java continuam na raiz. O frontend não lê entidades, migrations ou propriedades Spring em tempo de execução: `public/contracts.js` contém uma cópia explícita dos contratos da API, que deve acompanhar alterações futuras dos DTOs.

Os arquivos de `public/` também podem ser servidos por outro servidor web, desde que ele encaminhe `/api/...` para o backend, retirando o prefixo `/api`. Publicação e autenticação serão definidas em uma etapa própria.

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
npm test --prefix frontend
./mvnw -Dtest=OrdemServicoServiceTest test
```

Nesta implementação passaram 15 testes Node, incluindo testes HTTP reais do servidor independente (arquivos, proxy, método/query/body/status e backend indisponível), além de: conversões, validação de OS/orçamento/venda/pagamento, erros HTTP/JSON, escape de HTML e renderização programática das telas/formulários com um DOM simulado. O DOM simulado não substitui verificação em navegador.

A compilação/testes Java e execução com banco não puderam ser concluídos neste ambiente: Maven Central ficou inacessível e o Java disponível é 17, abaixo do 21 declarado no projeto. A instalação do Chromium também falhou por download incompleto; layout, responsividade e interações reais precisam de conferência em navegador no ambiente local.

Sugestão de teste manual: cadastrar uma pessoa e usuário; cadastrar auxiliares e produto; abrir uma OS com aparelho novo (ID vazio); atribuir técnico; diagnosticar; adicionar orçamento; mudar status; cadastrar uma condição com tantos vencimentos quanto parcelas; cadastrar venda vinculada à OS. Conferir os registros no banco e o saldo após a venda.
