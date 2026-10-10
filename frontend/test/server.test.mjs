import test from 'node:test';
import assert from 'node:assert/strict';
import http from 'node:http';
import { once } from 'node:events';
import { createFrontendServer } from '../server.mjs';

async function listen(server, t) {
  server.listen(0, '127.0.0.1');
  await once(server, 'listening');
  t.after(() => new Promise(resolve => { server.closeAllConnections(); server.close(resolve); }));
  return `http://127.0.0.1:${server.address().port}`;
}

test('frontend serve HTML e módulos sozinho e não expõe seus arquivos de configuração', async t => {
  const url = await listen(createFrontendServer(), t);
  const html = await fetch(url + '/');
  assert.equal(html.status, 200);
  assert.match(await html.text(), /Frontend independente/);
  const module = await fetch(url + '/app.js');
  assert.match(module.headers.get('content-type'), /javascript/);
  assert.match(await module.text(), /request\('\/api'\+path/);
  for (const path of ['/.env', '/server.mjs', '/package.json', '/..%2f.env']) {
    assert.equal((await fetch(url + path)).status, 404);
  }
  const head = await fetch(url + '/styles.css', { method: 'HEAD' });
  assert.equal(head.status, 200);
  assert.equal(await head.text(), '');
});

test('proxy usa backend configurável preservando método, query, JSON e status', async t => {
  let received;
  const backend = http.createServer(async (req, res) => {
    let body = '';
    for await (const chunk of req) body += chunk;
    received = { method: req.method, url: req.url, type: req.headers['content-type'], body };
    res.writeHead(201, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify({ id: 42 }));
  });
  const backendUrl = await listen(backend, t);
  const frontendUrl = await listen(createFrontendServer({ backendUrl: backendUrl + '/erp' }), t);
  const result = await fetch(frontendUrl + '/api/os?origem=teste', {
    method: 'POST', headers: { 'Content-Type': 'application/json' }, body: '{"pessoa":7}',
  });
  assert.equal(result.status, 201);
  assert.deepEqual(await result.json(), { id: 42 });
  assert.deepEqual(received, { method: 'POST', url: '/erp/os?origem=teste', type: 'application/json', body: '{"pessoa":7}' });
});

test('erro do backend chega ao navegador sem virar sucesso', async t => {
  const backendUrl = await listen(http.createServer((req, res) => {
    res.writeHead(422, { 'Content-Type': 'application/json' });
    res.end('{"message":"Produto inválido"}');
  }), t);
  const frontendUrl = await listen(createFrontendServer({ backendUrl }), t);
  const result = await fetch(frontendUrl + '/api/produto');
  assert.equal(result.status, 422);
  assert.equal((await result.json()).message, 'Produto inválido');
  assert.equal((await fetch(frontendUrl + '/api/rota-inexistente')).status, 404);
});

test('frontend funciona mesmo com backend indisponível e devolve erro legível para API', async t => {
  const temporary = http.createServer();
  temporary.listen(0, '127.0.0.1');
  await once(temporary, 'listening');
  const backendUrl = `http://127.0.0.1:${temporary.address().port}`;
  await new Promise(resolve => temporary.close(resolve));
  const frontendUrl = await listen(createFrontendServer({ backendUrl }), t);
  assert.equal((await fetch(frontendUrl)).status, 200);
  const result = await fetch(frontendUrl + '/api/pessoa');
  assert.equal(result.status, 502);
  assert.match((await result.json()).message, /BACKEND_URL/);
});

test('configuração rejeita protocolo que não seja HTTP ou HTTPS', () => {
  assert.throws(() => createFrontendServer({ backendUrl: 'file:///tmp/backend' }), /HTTP/);
});
