package com.direi.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.direi.cursomc.domain.Pedido;

public class SmtpEmailService extends AbstractEmailService{

	@Autowired
	private MailSender mailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando o envio de email..");
		mailSender.send(msg);
		LOG.info("email enviado...");
		
	}
	@Override
	public void sendOrderConfirmationHtmlEmail(Pedido obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		// TODO Auto-generated method stub
		
	}

}
