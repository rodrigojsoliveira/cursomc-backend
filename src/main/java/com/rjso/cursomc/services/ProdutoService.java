package com.rjso.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rjso.cursomc.domain.Categoria;
import com.rjso.cursomc.domain.Produto;
import com.rjso.cursomc.repositories.CategoriaRepository;
import com.rjso.cursomc.repositories.ProdutoRepository;
import com.rjso.cursomc.services.exceptions.ProdutoNaoEncontradoException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository catRepo;

	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ProdutoNaoEncontradoException(
				"Produto não encontrado. Id: " + id + " Tipo: " + Categoria.class.getName()));
	}

	public Page<Produto> search(String nome, List<Integer> ids, Integer pagina, Integer linhasPorPagina,
			String ordenaPor, String direcao) {
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Direction.valueOf(direcao), ordenaPor);
		List<Categoria> categorias = catRepo.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
	}
}
