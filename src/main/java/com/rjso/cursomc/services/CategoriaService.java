package com.rjso.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rjso.cursomc.domain.Categoria;
import com.rjso.cursomc.repositories.CategoriaRepository;
import com.rjso.cursomc.services.exceptions.CategoriaNaoEncontradaException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new CategoriaNaoEncontradaException("Categoria não encontrada. Id: " + id +
				" Tipo: " + Categoria.class.getName()));
	}

}
