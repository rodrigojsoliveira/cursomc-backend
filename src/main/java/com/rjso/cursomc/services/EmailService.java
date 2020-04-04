package com.rjso.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.rjso.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
	
}
