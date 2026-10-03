import test from 'node:test';
import assert from 'node:assert/strict';
import {typedValue,validatePayload,request,escapeHtml} from '../src/main/resources/static/beta/core.mjs';
test('conversão preserva zeros, nulos, booleanos e rejeita números inválidos',()=>{
 assert.equal(typedValue('Long',''),null);assert.equal(typedValue('Integer','0'),0);assert.equal(typedValue('Boolean',false),false);
 assert.throws(()=>typedValue('Long','1.2'));assert.throws(()=>typedValue('Double','NaN'));assert.throws(()=>typedValue('Map<String, Object>','[]'));
 assert.deepEqual(typedValue('Map<String, Object>','{"cor":"preto"}'),{cor:'preto'});
});
test('nova OS vincula aparelho ao cliente sem inventar código de aparelho',()=>{
 const p=validatePayload('DadosOSCadastro',{pessoa:7,atendenteRecebeu:'tecnico',problemaRelatado:'Tela',aparelhoClienteDto:{id:null,produto:9}});
 assert.equal(p.aparelhoClienteDto.cliente,7);assert.equal(p.aparelhoClienteDto.id,null);
});
test('orçamento exige itens e numera em sequência',()=>{
 assert.throws(()=>validatePayload('DadosCadastroOrcamento',{listaItens:[]}));
 assert.deepEqual(validatePayload('DadosCadastroOrcamento',{listaItens:[{},{}]}).listaItens.map(i=>i.numeroItem),[1,2]);
});
test('venda exige OS e uma condição, envia totais e descontos zerados',()=>{
 const p={os:1,cliente:2,nomeUsuario:'caixa',fpgs:[{idCondicao:3}],itens:[{produto:4,quantidade:2,precoVenda:15}]};
 const result=validatePayload('VendaCabecalhoDto',p);assert.equal(result.itens[0].valorTotalProduto,30);assert.equal(result.itens[0].valorDesconto,0);
 assert.throws(()=>validatePayload('VendaCabecalhoDto',{...p,os:null}));assert.throws(()=>validatePayload('VendaCabecalhoDto',{...p,fpgs:[{},{}]}));
});
test('condição mantém grafia do contrato e confere vencimentos',()=>{
 assert.throws(()=>validatePayload('CondicaoPagamentoDto',{quatidadeParcelas:2,dias:[{dias:0}]}));
 assert.equal(validatePayload('CondicaoPagamentoDto',{quatidadeParcelas:1,dias:[{dias:0}]}).dias[0].dias,0);
});
test('API trata falha HTTP e resposta HTML sem apresentar sucesso',async()=>{
 await assert.rejects(request('/pessoa',{},async()=>({ok:false,status:500,text:async()=>'{"message":"Falha de banco"}'})),/Falha de banco/);
 await assert.rejects(request('/pessoa',{},async()=>({ok:true,text:async()=>'<html>'})),/JSON/);
 let options;await request('/os',{method:'POST',body:'{}'},async(p,o)=>{options=o;return{ok:true,text:async()=>'{}'}});assert.equal(options.headers['Content-Type'],'application/json');
});
test('texto vindo do backend não vira HTML executável',()=>assert.equal(escapeHtml('<img onerror="x">'),'&lt;img onerror=&quot;x&quot;&gt;'));
