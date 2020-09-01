package com.direi.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.direi.cursomc.domain.Cliente;
import com.direi.cursomc.repositories.ClienteRepository;
import com.direi.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto Não encontrado "+ id + ", Tipo "+ Cliente.class.getName() ));
	}

}
