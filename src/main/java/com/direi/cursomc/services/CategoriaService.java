package com.direi.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.direi.cursomc.domain.Categoria;
import com.direi.cursomc.repositories.CategoriaRepository;
import com.direi.cursomc.services.exception.DataIntegrityException;
import com.direi.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	public Categoria buscar(Integer id) {
		//Optional<Categoria> categoria = repo.findById(id);
		
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repo.save(categoria);
	}
	public Categoria update(Categoria categoria) {
		buscar(categoria.getId());
		return repo.save(categoria);
	}
	public void detele(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível exluir uma categoria que possui produtos");
		}
		
	}
	public List<Categoria> findAll() {
			return repo.findAll();
	}
	
	public Page<Categoria>findPage(Integer page, Integer linesPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.fromString(direction),orderBy);
		return repo.findAll(pageRequest);
	}
}
