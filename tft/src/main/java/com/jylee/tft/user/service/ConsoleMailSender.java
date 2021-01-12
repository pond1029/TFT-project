/**
  * @Package : com.jylee.tft.user
  * @FileName : ConsoleMailSender.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.user.service;

import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
  * @Package : com.jylee.tft.user
  * @FileName : ConsoleMailSender.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */
@Profile("dev")
@Component
@Slf4j
public class ConsoleMailSender implements JavaMailSender{

	@Override
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		log.info(simpleMessage.getText());		
	}

	@Override
	public void send(SimpleMailMessage... simpleMessages) throws MailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MimeMessage createMimeMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void send(MimeMessage mimeMessage) throws MailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(MimeMessage... mimeMessages) throws MailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
		// TODO Auto-generated method stub
		
	}
	

}
