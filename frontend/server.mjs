import http from 'node:http';
import https from 'node:https';
import { readFile } from 'node:fs/promises';
import { resolve } from 'node:path';
import { fileURLToPath, pathToFileURL } from 'node:url';

const publicRoot = fileURLToPath(new URL('./public/', import.meta.url));
const assets = new Map([
  ['/', ['index.html', 'text/html; charset=utf-8']],
  ['/index.html', ['index.html', 'text/html; charset=utf-8']],
  ['/app.js', ['app.js', 'text/javascript; charset=utf-8']],
  ['/contracts.js', ['contracts.js', 'text/javascript; charset=utf-8']],
  ['/core.mjs', ['core.mjs', 'text/javascript; charset=utf-8']],
  ['/styles.css', ['styles.css', 'text/css; charset=utf-8']],
  ['/favicon.svg', ['favicon.svg', 'image/svg+xml']],
]);
const routes = /^\/(os|pessoa|produto|venda|aparelho|usuario|marca|departamento|categoria|subcategoria|unidade_medida|garantia|forma_pagamento|condicao|codigo_barras|referencia|compatibildade|movimentacao)(\/|$)/;

function jsonError(res, status, message) {
  if (res.headersSent || res.destroyed) return;
  res.writeHead(status, { 'Content-Type': 'application/json; charset=utf-8' });
  res.end(JSON.stringify({ message }));
}

export function createFrontendServer({ backendUrl = 'http://localhost:8080', staticRoot = publicRoot, timeout = 15000 } = {}) {
  const backend = new URL(backendUrl);
  if (!['http:', 'https:'].includes(backend.protocol) || backend.username || backend.password || backend.search || backend.hash) {
    throw new Error('BACKEND_URL deve ser um endereço HTTP ou HTTPS, sem credenciais, query ou fragmento.');
  }
  return http.createServer(async (req, res) => {
    const incoming = new URL(req.url, 'http://frontend.local');
    if (incoming.pathname.startsWith('/api/')) {
      const apiPath = incoming.pathname.slice(4);
      if (!routes.test(apiPath)) return jsonError(res, 404, 'Rota da API não encontrada.');
      const target = new URL(backend.origin + backend.pathname.replace(/\/$/, '') + apiPath + incoming.search);
      const headers = {};
      for (const name of ['accept', 'content-type', 'authorization', 'cookie']) {
        if (req.headers[name]) headers[name] = req.headers[name];
      }
      const transport = target.protocol === 'https:' ? https : http;
      const upstream = transport.request(target, { method: req.method, headers }, response => {
        const responseHeaders = {};
        for (const name of ['content-type', 'set-cookie', 'cache-control']) {
          if (response.headers[name]) responseHeaders[name] = response.headers[name];
        }
        res.writeHead(response.statusCode || 502, responseHeaders);
        response.on('error', () => res.destroy());
        response.pipe(res);
      });
      upstream.setTimeout(timeout, () => upstream.destroy(new Error('Tempo de resposta excedido.')));
      upstream.on('error', () => jsonError(res, 502, 'Não foi possível conectar ao backend. Inicie o Spring Boot e confira BACKEND_URL no .env do frontend.'));
      req.on('aborted', () => upstream.destroy());
      res.on('close', () => { if (!res.writableEnded) upstream.destroy(); });
      req.pipe(upstream);
      return;
    }
    if (!['GET', 'HEAD'].includes(req.method)) return jsonError(res, 405, 'Método não permitido para arquivos do frontend.');
    const asset = assets.get(incoming.pathname);
    if (!asset) return jsonError(res, 404, 'Página não encontrada.');
    try {
      const bytes = await readFile(resolve(staticRoot, asset[0]));
      res.writeHead(200, { 'Content-Type': asset[1], 'Cache-Control': 'no-store' });
      res.end(req.method === 'HEAD' ? undefined : bytes);
    } catch {
      jsonError(res, 500, 'Não foi possível carregar o arquivo do frontend.');
    }
  });
}

if (process.argv[1] && import.meta.url === pathToFileURL(resolve(process.argv[1])).href) {
  const port = Number(process.env.PORT || 5173);
  const host = process.env.HOST || '127.0.0.1';
  if (!Number.isInteger(port) || port < 1 || port > 65535) throw new Error('PORT deve estar entre 1 e 65535.');
  const server = createFrontendServer({ backendUrl: process.env.BACKEND_URL || 'http://localhost:8080' });
  server.on('error', error => { console.error(`Falha ao iniciar frontend: ${error.message}`); process.exitCode = 1; });
  server.listen(port, host, () => console.log(`TechFix frontend: http://${host}:${port} · API: ${process.env.BACKEND_URL || 'http://localhost:8080'}`));
}
