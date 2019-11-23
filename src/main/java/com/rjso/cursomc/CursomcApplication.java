package com.rjso.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.rjso.cursomc.domain.Categoria;
import com.rjso.cursomc.domain.Cidade;
import com.rjso.cursomc.domain.Cliente;
import com.rjso.cursomc.domain.Endereco;
import com.rjso.cursomc.domain.Estado;
import com.rjso.cursomc.domain.Pagamento;
import com.rjso.cursomc.domain.PagamentoComBoleto;
import com.rjso.cursomc.domain.PagamentoComCartao;
import com.rjso.cursomc.domain.Pedido;
import com.rjso.cursomc.domain.Produto;
import com.rjso.cursomc.domain.enums.EstadoPagamento;
import com.rjso.cursomc.domain.enums.TipoCliente;
import com.rjso.cursomc.repositories.CategoriaRepository;
import com.rjso.cursomc.repositories.CidadeRepository;
import com.rjso.cursomc.repositories.ClienteRepository;
import com.rjso.cursomc.repositories.EnderecoRepository;
import com.rjso.cursomc.repositories.EstadoRepository;
import com.rjso.cursomc.repositories.PagamentoRepository;
import com.rjso.cursomc.repositories.PedidoRepository;
import com.rjso.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00d);
		Produto p2 = new Produto(null, "Impressora", 800.00d);
		Produto p3 = new Produto(null, "Mouse", 80.00d);
		 
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "76382976522", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("33748947", "44286622"));
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat dataVencimento = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		dataVencimento.parse("20/10/2017 00:00");
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, null, 6);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, null, dataVencimento.getCalendar(), null);
		
		SimpleDateFormat instante = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		instante.parse("30/09/2017 10:32");
		
		Pedido ped1 = new Pedido(null, instante.getCalendar(), cli1, pagto1);
		ped1.setEnderecoDeEntrega(e1);
		pagto1.setPedido(ped1);
		
		SimpleDateFormat instante2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		instante2.parse("10/10/2017 19:35");

		Pedido ped2 = new Pedido(null, instante2.getCalendar(), cli1, pagto2);
		ped2.setEnderecoDeEntrega(e2);
		pagto2.setPedido(ped2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));		

	}

}
