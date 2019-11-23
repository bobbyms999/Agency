package com.accredilink.bgv.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:constants.properties")
public class Constants {

	@Value("${invalid.email.id}")
	public String INVALID_EMAIL_ID;
	@Value("${email.id.exists}")
	public String ALREADY_EMAIL_ID_AND_USERNAME_REGISTERED;
	@Value("${registration.sucess}")
	public String ACCREDILINK_REGISTRATION_SUCCESSFUL;
	@Value("${login.sucess}")
	public String LOGIN_SUCCESS;
	@Value("${invalid.credentials}")
	public String INVALID_CREDENTIALS;
	@Value("${individual}")
	public String INDIVIDUAL;
	@Value("${company}")
	public String COMPANY;

}
