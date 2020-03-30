package com.rjso.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rjso.cursomc.domain.Cidade;
import com.rjso.cursomc.domain.Cliente;
import com.rjso.cursomc.domain.Endereco;
import com.rjso.cursomc.domain.enums.TipoCliente;
import com.rjso.cursomc.dto.ClienteDTO;
import com.rjso.cursomc.dto.ClienteNewDTO;
import com.rjso.cursomc.repositories.ClienteRepository;
import com.rjso.cursomc.repositories.EnderecoRepository;
import com.rjso.cursomc.services.exceptions.ClienteNaoEncontradoException;
import com.rjso.cursomc.services.exceptions.IntegridadeDeDadosException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepo;

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ClienteNaoEncontradoException(
				"Cliente não encontrado. Id: " + id + " Tipo: " + Cliente.class.getName()));
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

	public Cliente fromDTO(ClienteNewDTO objNewDTO) {
		Cliente cliente = new Cliente(null, objNewDTO.getNome(), objNewDTO.getEmail(), objNewDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objNewDTO.getTipoCliente()));

		Cidade cidade = new Cidade(objNewDTO.getCidadeId(), null, null);

		Endereco endereco = new Endereco(null, objNewDTO.getLogradouro(), objNewDTO.getNumero(),
				objNewDTO.getComplemento(), objNewDTO.getBairro(), objNewDTO.getCep(), cidade, cliente);

		cliente.getTelefones().add(objNewDTO.getTelefone1());

		if (objNewDTO.getTelefone2() != null) {
			cliente.getTelefones().add(objNewDTO.getTelefone2());
		}

		if (objNewDTO.getTelefone3() != null) {
			cliente.getTelefones().add(objNewDTO.getTelefone3());
		}

		cliente.getEnderecos().add(endereco);

		return cliente;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		// Se o id do objeto for diferente de null, o repositório irá atualizar os
		// dados, e não criar uma nova categoria.
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepo.saveAll(obj.getEnderecos());
		return obj;
	}

}
