package com.accredilink.bgv.service;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.entity.User;
import com.accredilink.bgv.exception.AccredLinkAppException;
import com.accredilink.bgv.util.Constants;
@Service
public class NotificationServiceImpl implements NotificationService {

	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Constants constants;
	
	@Value("${spring.mail.username}")
	String userMailFrom;
		
	@Transactional
	public boolean sendEmail(String to,String subject,String mailBody) {
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(mimeMessage,true);
			msgHelper.setFrom(userMailFrom);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
					
			msgHelper.setText(mailBody.toString());
			mailSender.send(mimeMessage);
			
		} catch(Exception e) {
			logger.error("Exceptin raised in notification : " + e);
			throw new AccredLinkAppException("Unable to send the email");
		}
		return true;
	}
}