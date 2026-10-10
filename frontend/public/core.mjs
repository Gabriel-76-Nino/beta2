export const escapeHtml = value => String(value ?? '').replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
export function typedValue(type, value) {
 if (type === 'Boolean') return Boolean(value);
 if (value === '') return null;
 if (['Long','Integer','Double','BigDecimal'].includes(type)) {
  const n = Number(value); if (!Number.isFinite(n) || (['Long','Integer'].includes(type) && !Number.isSafeInteger(n))) throw new Error('Informe um número válido.'); return n;
 }
 if (type.startsWith('Map')) { const v = JSON.parse(value); if (!v || Array.isArray(v) || typeof v !== 'object') throw new Error('Especificações devem ser um objeto JSON.'); return v; }
 return value;
}
export function readFields(container) {
 const result = {};
 container.querySelectorAll(':scope > .field [data-type]').forEach(input => {result[input.name] = typedValue(input.dataset.type, input.type === 'checkbox' ? input.checked : input.value);});
 container.querySelectorAll(':scope > fieldset[data-object]').forEach(group => {result[group.dataset.object] = readFields(group.querySelector(':scope > .form-grid'));});
 container.querySelectorAll(':scope > fieldset[data-array]').forEach(group => {result[group.dataset.array] = [...group.querySelectorAll(':scope > .repeat-list > .repeat-item')].map(row => readFields(row.querySelector(':scope > .form-grid')));});
 return result;
}
export function validatePayload(kind, p) {
 if (kind === 'DadosOSCadastro') { if (!p.pessoa || !p.aparelhoClienteDto?.produto || !p.atendenteRecebeu || !p.problemaRelatado) throw new Error('Informe cliente, produto do aparelho, atendente e problema relatado.'); p.aparelhoClienteDto.cliente = p.pessoa; }
 if (kind === 'DadosCadastroOrcamento') { if (!p.listaItens?.length) throw new Error('Adicione pelo menos um item ao orçamento.'); p.listaItens.forEach((item,i)=>item.numeroItem=i+1); }
 if (kind === 'VendaCabecalhoDto') { if (!p.os || !p.cliente || !p.nomeUsuario || !p.itens?.length || p.fpgs?.length !== 1) throw new Error('Informe cliente, vendedor, OS, itens e uma condição de pagamento.'); p.itens.forEach((item,i)=> { if (!item.produto || !(item.quantidade>0) || !(item.precoVenda>=0)) throw new Error('Confira produto, quantidade e preço dos itens.'); item.numeroItem=i+1; item.valorTotalProduto=item.quantidade*item.precoVenda; item.valorDesconto=0; item.percentualDesconto=0; }); }
 if (kind === 'CondicaoPagamentoDto') { if (!p.dias?.length || p.quatidadeParcelas!==p.dias.length) throw new Error('A quantidade de parcelas precisa corresponder ao número de vencimentos.'); }
 return p;
}
export async function request(path, options = {}, fetcher = fetch) {
 const response = await fetcher(path, { ...options, headers: {'Accept':'application/json', ...(options.body ? {'Content-Type':'application/json'} : {}), ...options.headers} });
 const text = await response.text(); let data; try { data = text ? JSON.parse(text) : null; } catch { throw new Error('O servidor não retornou JSON. Verifique se o backend está em execução e se a sessão está autorizada.'); }
 if (!response.ok) throw new Error(data?.message || data?.detail || `A API retornou HTTP ${response.status}. Verifique os dados e o log do backend.`);
 return data;
}
