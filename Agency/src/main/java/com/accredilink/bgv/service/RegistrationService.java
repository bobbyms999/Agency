package com.accredilink.bgv.service;

import com.accredilink.bgv.entity.User;
import com.accredilink.bgv.pojo.Login;

public interface RegistrationService {

	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	public boolean registration(User user) throws Exception;
	
	public boolean login(Login login);
	
	public boolean reset(Login login);
	
	public boolean conform(Login login);



}
