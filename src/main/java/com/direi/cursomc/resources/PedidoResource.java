package com.direi.cursomc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.direi.cursomc.domain.Categoria;
import com.direi.cursomc.domain.Pedido;
import com.direi.cursomc.dto.CategoriaDTO;
import com.direi.cursomc.dto.PedidoDTO;
import com.direi.cursomc.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {			
		Pedido pedido = service.buscar(id);		
		return ResponseEntity.ok(pedido);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@Valid @RequestBody Pedido obj){
		obj = service.insert(obj);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
			return ResponseEntity.created(uri).build();
	}
	
}
