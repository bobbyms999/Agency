package com.accredilink.bgv.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.entity.User;
import com.accredilink.bgv.exception.AccredLinkAppException;
import com.accredilink.bgv.pojo.Login;
import com.accredilink.bgv.repository.UserRepository;
import com.accredilink.bgv.util.Constants;
import com.accredilink.bgv.util.EmailValidator;
import com.accredilink.bgv.util.PasswordEncrAndDecrUtil;
import com.accredilink.bgv.util.TokenGeneratorUtil;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
	private static StringBuffer emailBody = new StringBuffer(
			"You have requested to reset your password please click in the below link \n http://localhost:4200/resetPassword?token=");
	@Autowired
	NotificationService notificationService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	Constants constants;

	/**
	 * @param registrationDTO
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public boolean registration(User user) throws Exception {
		boolean flag = false;
		/*
		 * Checking email id is valid or not, if it is invalid then throwing exception.
		 */
		boolean isValid = isEmailValid(user.getEmailId());

		if (!isValid) {
			throw new AccredLinkAppException(constants.INVALID_EMAIL_ID);
		}
		try {

			checkUserIdAndEmail(user);
			/*
			 * Checking the User, If the User is Employee then inserting records into only
			 * User table
			 */
			if (user.getType().equalsIgnoreCase(constants.INDIVIDUAL)) {
				user.setCompany(null);
				userRepository.save(user);
				/*
				 * Checking the User, If the User is Company then inserting records into User,
				 * Company, Address and UserType tables.
				 */
			} else if (user.getType().equalsIgnoreCase(constants.COMPANY)) {
				userRepository.save(user);
			}
			flag = true;
		} catch (AccredLinkAppException e) {
			logger.error(e.getMessage());
			throw new AccredLinkAppException(e.getExceptionMessge());
		} catch (Exception e) {
			logger.error("Exception raised in registration ", e);
			throw new Exception("Exception raised in registration");
		}

		boolean status = notificationService.sendEmail(user.getEmailId(), "for reg", user.getUserName());
		if (status) {
			logger.info("Successfully sent email notification after registration.");
		} else {
			logger.error("ERROR : while sending notification at registration");
		}

		return flag;
	}

	@Override
	public boolean login(Login login) {
		boolean flag = false;
		Optional<User> optionalRegistration = userRepository.findByEmailIdOrUserName(login.getEmailId(),
				login.getEmailId());
		if (optionalRegistration.isPresent()) {
			if (PasswordEncrAndDecrUtil.check(login.getPassword(), optionalRegistration.get().getPassword())) {
				flag = true;
			} else {
				throw new AccredLinkAppException("password is not correct");
			}
		} else {
			throw new AccredLinkAppException("your user name and email id not matched");
		}
		return flag;
	}

	@Override
	public boolean reset(Login login) {
		User user = null;
		String token = null;
		boolean flag = false;
		Optional<User> optionalRegistration = userRepository.findByEmailId(login.getEmailId());
		if (optionalRegistration.isPresent()) {
			user = optionalRegistration.get();
			token = TokenGeneratorUtil.generateNewToken();
			user.setTokenNumer(token);
			userRepository.save(user);
			emailBody = emailBody.append(token).append("&").append("email=" + login.getEmailId());
			flag = notificationService.sendEmail(login.getEmailId(), "Accredilink Reset Password",
					emailBody.toString());
			if (flag) {
				return flag;
			}
		} else {
			throw new AccredLinkAppException("Email Id Is not correct");
		}
		return flag;
	}

	@Override
	public boolean conform(Login login) {
		User user = null;
		boolean flag = false;
		Optional<User> optionalRegistration = userRepository.findByEmailIdAndTokenNumer(login.getEmailId(),
				login.getToken());
		if (optionalRegistration.isPresent()) {
			user = optionalRegistration.get();
			user.setPassword(PasswordEncrAndDecrUtil.encrypt(login.getPassword()));
			userRepository.save(user);
			flag = true;
		} else {
			logger.error("Email id or token not found");
		}
		return flag;
	}

	private boolean isEmailValid(String emailId) {
		EmailValidator emailValidator = new EmailValidator();
		boolean status = false;
		if (emailId != null) {
			status = emailValidator.validate(emailId);
		}
		return status;
	}

	private void auditUser(User user) {
		user.getAddress().setCreatedBy(user.getUserName());
		user.setPassword(PasswordEncrAndDecrUtil.encrypt(user.getPassword()));
		if (user.getType().equalsIgnoreCase(constants.COMPANY)) {
			user.getCompany().setCreatedBy(user.getUserName());
		}
	}

	/*
	 * Checking email id already exist or not, if it is exists then throwing
	 * exception.
	 */
	private void checkUserIdAndEmail(User user) {
		Optional<User> optionalRegistration = userRepository.findByEmailIdOrUserName(user.getEmailId(),
				user.getUserName());
		if (optionalRegistration.isPresent()) {
			throw new AccredLinkAppException(constants.ALREADY_EMAIL_ID_AND_USERNAME_REGISTERED);
		} else {
			auditUser(user);
		}
	}

}