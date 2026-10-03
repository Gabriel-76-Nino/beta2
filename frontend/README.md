# TechFix · Frontend independente

Interface beta em JavaScript, HTML e CSS, inspirada no SAP Fiori. A execução não depende de Maven nem do código Java.

## Iniciar

Requer Node.js 22+. Não há dependências externas para instalar.

```bash
cp .env.example .env
npm run dev
```

Abra `http://127.0.0.1:5173`. Para demonstração sem backend: `http://127.0.0.1:5173/?demo=1`.

O servidor Spring Boot deve ser executado separadamente. Configure `BACKEND_URL` em `.env` (padrão: `http://localhost:8080`). Reinicie o frontend se alterar a configuração.

- `npm run dev`: servidor com reinício automático ao editar arquivos.
- `npm start`: servidor sem watch.
- `npm test`: testes de contratos, renderização programática e comunicação HTTP.
- `public/`: interface e contratos da API.
- `server.mjs`: entrega dos arquivos e proxy HTTP de `/api/...` para o backend.
- `test/`: testes independentes do Java.

Esta pasta pode ser movida para outro diretório ou repositório. O backend não serve nem empacota a interface. Sem o backend, o frontend continua abrindo, mas as operações reais retornam uma mensagem de conexão indisponível.

Não existe persistência ou lógica de estoque/venda neste servidor: regras de negócio continuam no backend. Ainda faltam validação em navegador real, teste com PostgreSQL, autenticação e definição de hospedagem.
