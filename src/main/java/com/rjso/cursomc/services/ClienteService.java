package com.rjso.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rjso.cursomc.domain.Cliente;
import com.rjso.cursomc.repositories.ClienteRepository;
import com.rjso.cursomc.services.exceptions.ClienteNaoEncontradoException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj =  repo.findById(id);
		return obj.orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado. Id: " + id +
				" Tipo: " + Cliente.class.getName()));
	}

}
