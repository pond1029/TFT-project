/**
  * @Package : com.jylee.tft.user
  * @FileName : ConsoleMailSender.java
  * @Date : 2021. 1. 12. 
  * @Author : "LeeJaeYeon"
  * @Version :
  * @Information :
  */

package com.jylee.tft.modules.siteuser.service;

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
	}

	@Override
	public MimeMessage createMimeMessage() {
		return null;
	}

	@Override
	public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
		return null;
	}

	@Override
	public void send(MimeMessage mimeMessage) throws MailException {
	}

	@Override
	public void send(MimeMessage... mimeMessages) throws MailException {
	}

	@Override
	public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
	}

	@Override
	public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
	}
	

}
