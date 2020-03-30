package com.rjso.cursomc.services;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rjso.cursomc.domain.Categoria;
import com.rjso.cursomc.domain.ItemPedido;
import com.rjso.cursomc.domain.PagamentoComBoleto;
import com.rjso.cursomc.domain.Pedido;
import com.rjso.cursomc.domain.Produto;
import com.rjso.cursomc.domain.enums.EstadoPagamento;
import com.rjso.cursomc.repositories.ItemPedidoRepository;
import com.rjso.cursomc.repositories.PagamentoRepository;
import com.rjso.cursomc.repositories.PedidoRepository;
import com.rjso.cursomc.services.exceptions.PedidoNaoEncontradoException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PagamentoRepository repoPagamento;

	@Autowired
	private ItemPedidoRepository repoItemPedido;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new PedidoNaoEncontradoException(
				"Pedido não encontrado. Id: " + id + " Tipo: " + Categoria.class.getName()));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(Calendar.getInstance());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		repoPagamento.save(obj.getPagamento());
		for (ItemPedido i : obj.getItensPedido()) {
			i.setDesconto(0.0);
			i.setProduto(produtoService.find(i.getProduto().getId()));
			i.setPreco(i.getProduto().getPreco());
			i.setPedido(obj);
		}
		repoItemPedido.saveAll(obj.getItensPedido());
		System.out.println(obj);
		return obj;
	}
}
