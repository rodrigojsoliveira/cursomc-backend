package com.rjso.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rjso.cursomc.domain.Categoria;
import com.rjso.cursomc.domain.Pedido;
import com.rjso.cursomc.repositories.PedidoRepository;
import com.rjso.cursomc.services.exceptions.PedidoNaoEncontradoException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado. Id: " + id +
				" Tipo: " + Categoria.class.getName()));
	}

}
