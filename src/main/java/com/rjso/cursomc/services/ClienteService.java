package com.rjso.cursomc.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.rjso.cursomc.domain.Cliente;
import com.rjso.cursomc.dto.ClienteDTO;
import com.rjso.cursomc.repositories.ClienteRepository;
import com.rjso.cursomc.services.exceptions.ClienteNaoEncontradoException;
import com.rjso.cursomc.services.exceptions.IntegridadeDeDadosException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj =  repo.findById(id);
		return obj.orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado. Id: " + id +
				" Tipo: " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new IntegridadeDeDadosException("Não é possível excluir um cliente que tenha realizado pedidos.");
		}
	}

	public Page<Cliente> findPage(Integer pagina, Integer linhasPorPagina, String ordenaPor, String direcao) {
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordenaPor);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}


}
