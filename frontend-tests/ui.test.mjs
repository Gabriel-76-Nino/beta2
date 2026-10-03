import test from 'node:test';
import assert from 'node:assert/strict';
import fs from 'node:fs';
import vm from 'node:vm';
import * as core from '../src/main/resources/static/beta/core.mjs';
const dir=new URL('../src/main/resources/static/beta/',import.meta.url);
function setup(){
 const elements=new Map(), listeners={};
 function element(sel){if(!elements.has(sel))elements.set(sel,{innerHTML:'',textContent:'',value:'',dataset:{},classList:{add(){},remove(){},toggle(){}},setAttribute(){},removeAttribute(){},addEventListener(type,fn){listeners[sel+':'+type]=fn;},showModal(){this.open=true;},close(){this.open=false;}});return elements.get(sel);}
 const document={querySelector:element,querySelectorAll:()=>[],addEventListener(type,fn){listeners[type]=fn;}};
 const location={search:'?demo=1',hash:'#inicio'};
 const context=vm.createContext({document,location,URLSearchParams,URL,Blob,structuredClone,console,setTimeout:()=>0,clearTimeout(){},window:{addEventListener(type,fn){listeners['window:'+type]=fn;}},...core});
 const schema=fs.readFileSync(new URL('contracts.js',dir),'utf8').replaceAll('export const ','const ');
 const source=fs.readFileSync(new URL('app.js',dir),'utf8').replace(/^import .*;\n/gm,'').replace('escapeHtml as e','');
 vm.runInContext(`const e=escapeHtml;${schema}\n${source}`,context);
 const tick=()=>new Promise(resolve=>setImmediate(resolve));
 return {context,location,element,listeners,tick,run:code=>vm.runInContext(code,context)};
}
test('visão geral e todas as consultas renderizam em modo demonstrativo',async()=>{
 const s=setup();await s.tick();assert.match(s.element('#content').innerHTML,/OS em aberto/);
 for(const route of ['os','produto','pessoa','estoque','aparelho','usuario','marca','departamento','categoria','subcategoria','unidade_medida','garantia','forma_pagamento','condicao','codigo_barras','referencia','compatibilidade','movimentacao','venda','cadastros','decisoes']){
  s.location.hash='#'+route;await s.run('route()');assert.doesNotMatch(s.element('#content').innerHTML,/Não foi possível carregar/);assert.match(s.element('#content').innerHTML,/<h1>/);
 }
});
test('formulários usam relações e contratos reais, sem pedir IDs gerados',async()=>{
 const s=setup();await s.tick();await s.run("openEditor('CadastroProdutoDto','/produto')");
 assert.match(s.element('#form-content').innerHTML,/value="Samsung"/);assert.match(s.element('#form-content').innerHTML,/value="UN"/);assert.match(s.element('#form-content').innerHTML,/name="garantia"/);
 await s.run("openEditor('FormaPagamentoDto','/forma_pagamento')");assert.doesNotMatch(s.element('#form-content').innerHTML,/name="idFpg"/);
 await s.run("openEditor('DadosOSCadastro','/os')");assert.match(s.element('#form-content').innerHTML,/data-object="aparelhoClienteDto"/);assert.match(s.element('#form-content').innerHTML,/Deixe vazio para cadastrar/);
 await s.run("openEditor('VendaCabecalhoDto','/venda')");assert.match(s.element('#form-content').innerHTML,/data-array="itens"/);assert.match(s.element('#form-content').innerHTML,/name="idCondicao"/);assert.doesNotMatch(s.element('#form-content').innerHTML,/name="valorDesconto"/);
});
test('OS permite filtrar, consultar detalhes e preparar ações específicas',async()=>{
 const s=setup();await s.tick();s.location.hash='#os';await s.run('route()');s.run("status='DIAGNOSTICO';renderTable()");assert.match(s.element('#table-content').innerHTML,/Não carrega/);assert.doesNotMatch(s.element('#table-content').innerHTML,/Tela quebrada/);
 s.run('showDetail(rows[0])');assert.match(s.element('#content').innerHTML,/Atribuir técnico/);assert.match(s.element('#content').innerHTML,/Adicionar orçamento/);
 await s.run("openEditor('DadosAtribuirTecnico','/os/tecnico','PUT',{id:100001})");assert.doesNotMatch(s.element('#form-content').innerHTML,/name="id"/);assert.match(s.element('#form-content').innerHTML,/tecnico.demo/);
});
