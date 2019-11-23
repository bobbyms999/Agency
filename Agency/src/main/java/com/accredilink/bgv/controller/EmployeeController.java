package com.accredilink.bgv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accredilink.bgv.entity.Employee;
import com.accredilink.bgv.service.EmployeeService;
import com.accredilink.bgv.util.ResponseObject;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/create")
	public ResponseObject createEmployee(@RequestBody Employee employee){
		return employeeService.createEmployee(employee);
	}
	
	@GetMapping("/delete/{employeeId}")
	public ResponseObject deleteEmployee(@PathVariable int employeeId){
		return employeeService.deleteEmployee(employeeId);
	}
}
