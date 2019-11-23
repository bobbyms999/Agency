package com.accredilink.bgv.controller;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accredilink.bgv.entity.User;
import com.accredilink.bgv.pojo.Login;
import com.accredilink.bgv.service.RegistrationService;
import com.accredilink.bgv.util.ResponseObject;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RegistrationController {

	@Autowired
	RegistrationService registrationService;

	@Autowired
	ResponseObject responseObject;

	@PostMapping("/register")
	public ResponseEntity<ResponseObject> registrationForm(@RequestBody User user) throws Exception {
		if (registrationService.registration(user)) {
			return new ResponseEntity<ResponseObject>(ResponseObject.constructResponse("Registration Sucess", 1),
					HttpStatus.OK);
		}
		return new ResponseEntity<ResponseObject>(ResponseObject.constructResponse("Unable to register the user", 0),
				HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseObject> loginForm(@RequestBody Login login) {
		if (registrationService.login(login)) {
			return new ResponseEntity<ResponseObject>(ResponseObject.constructResponse("Login Sucess", 1),
					HttpStatus.OK);
		}
		return new ResponseEntity<ResponseObject>(ResponseObject.constructResponse("Login Faild", 0),
				HttpStatus.EXPECTATION_FAILED);
	}
	@PostMapping("/forgotPassword")
	public ResponseEntity<ResponseObject> resetPassword(@RequestBody Login login) {
		if (registrationService.reset(login)) {
			return new ResponseEntity<ResponseObject>(
					ResponseObject.constructResponse("Enter Valid Email Id", 1, "67576567576"), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseObject>(ResponseObject.constructResponse("Unable To Send the Email", 0),
				HttpStatus.EXPECTATION_FAILED);
	}

	@PostMapping("/resetPassword")
	public ResponseEntity<ResponseObject> conformPassword(@RequestBody Login login) {
		if (registrationService.conform(login)) {
			return new ResponseEntity<ResponseObject>(
					ResponseObject.constructResponse("Password Sucessfully changed", 1), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseObject>(
				ResponseObject.constructResponse("Unable to change the password try again", 0),
				HttpStatus.EXPECTATION_FAILED);
	}

}