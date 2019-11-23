package com.accredilink.bgv.service;

public interface NotificationService {

	public boolean sendEmail(String to, String subject, String mailBody);

}
