package com.direi.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.direi.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
