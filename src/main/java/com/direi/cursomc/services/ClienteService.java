package com.direi.cursomc.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.direi.cursomc.domain.Cidade;
import com.direi.cursomc.domain.Cliente;
import com.direi.cursomc.domain.Endereco;
import com.direi.cursomc.domain.enums.TipoCliente;
import com.direi.cursomc.dto.ClienteDTO;
import com.direi.cursomc.dto.ClienteNewDTO;
import com.direi.cursomc.repositories.CidadeRepository;
import com.direi.cursomc.repositories.ClienteRepository;
import com.direi.cursomc.services.exception.DataIntegrityException;
import com.direi.cursomc.services.exception.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto Não encontrado "+ id + ", Tipo "+ Cliente.class.getName() ));
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = buscar(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}
	public void detele(Integer id){
		buscar(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível exluir porque há entidades relacionadas");
		}
		
	}
	public List<Cliente> findAll() {
			return repo.findAll();
	}
	
	public Page<Cliente>findPage(Integer page, Integer linesPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.fromString(direction),orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDto(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDto(ClienteNewDTO objDto) {
		Cliente cliente =  new Cliente(null,objDto.getNome(), objDto.getEmail(),objDto.getCpfOuCnpj(),TipoCliente.toEnum(objDto.getTipo()));
		cliente.getTelefones().addAll(Arrays.asList(objDto.getTelefone1()));
		Cidade cidade = new Cidade(objDto.getCidade_id(), null,null);
		Endereco endereco = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cliente, cidade);
		cliente.getEnderecos().addAll(Arrays.asList(endereco));
				
		if(objDto.getTelefone2() != null) {
			cliente.getTelefones().addAll(Arrays.asList(objDto.getTelefone2()));
		}

		if(objDto.getTelefone3() != null) {
			cliente.getTelefones().addAll(Arrays.asList(objDto.getTelefone3()));
		}
		
		return cliente;
	}
	
	private void updateData(Cliente newObj, Cliente obj ) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
