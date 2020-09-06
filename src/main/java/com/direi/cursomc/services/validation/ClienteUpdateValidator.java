package com.direi.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.direi.cursomc.domain.Cliente;
import com.direi.cursomc.domain.enums.TipoCliente;
import com.direi.cursomc.dto.ClienteNewDTO;
import com.direi.cursomc.repositories.ClienteRepository;
import com.direi.cursomc.resources.exception.FieldMessage;
import com.direi.cursomc.services.validation.utils.BR;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		
		Integer uriId = Integer.parseInt(map.get("id"));
		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
		if(cliente != null && !uriId.equals(cliente.getId())) {
			list.add(new FieldMessage("email", "Email já cadastrado no banco de dados."));
		}
		// inclua os testes aqui, inserindo erros na lista
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMsg()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}