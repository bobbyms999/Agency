package com.accredilink.bgv.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.entity.Employee;
import com.accredilink.bgv.exception.AccredLinkAppException;
import com.accredilink.bgv.repository.EmployeeRepository;
import com.accredilink.bgv.util.Constants;
import com.accredilink.bgv.util.EmailValidator;
import com.accredilink.bgv.util.ResponseObject;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private Constants constants;

	@Transactional
	public ResponseObject createEmployee(Employee employee) {

		/*
		 * Checking email id is valid or not, if it is invalid then throwing exception.
		 */
		boolean isValid = isEmailValid(employee.getEmailId());
		if (!isValid) {
			throw new AccredLinkAppException(constants.INVALID_EMAIL_ID);
		}

		ResponseObject ResponseObject = new ResponseObject();
		try {
			employeeRepository.save(employee);
		} catch (Exception e) {
			logger.error("Exception raised in creating employee ", e);
			throw new AccredLinkAppException("Exception raised in creating employee ");
		}
		ResponseObject.setMessage("Success");
		ResponseObject.setCode(1);

		return ResponseObject;
	}

	@Transactional
	
	private boolean isEmailValid(String emailId) {
		EmailValidator emailValidator = new EmailValidator();
		return emailValidator.validate(emailId);
	}
	
	@Transactional
	public ResponseObject deleteEmployee(int employeeId) {
		ResponseObject ResponseObject = new ResponseObject();
		try {
			employeeRepository.deleteById(employeeId);
		} catch (Exception e) {
			logger.error("Exception raised in deleting employee ", e);
			throw new AccredLinkAppException("Exception raised in deleting employee ");
		}
		ResponseObject.setMessage("Deleted employee successfully");
		ResponseObject.setCode(1);
		return ResponseObject;
	}

}
