package com.direi.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.direi.cursomc.domain.Pagamento;
import com.direi.cursomc.repositories.PagamentoRepository;
import com.direi.cursomc.services.exception.DataIntegrityException;
import com.direi.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository repo;
	public Pagamento buscar(Integer id) {
		//Optional<Pagamento> categoria = repo.findById(id);
		
		Optional<Pagamento> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Pagamento.class.getName()));

	}
	public Pagamento insert(Pagamento obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public void detele(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível exluir uma categoria que possui produtos");
		}
		
	}
	public List<Pagamento> findAll() {
			return repo.findAll();
	}
	
	
}
