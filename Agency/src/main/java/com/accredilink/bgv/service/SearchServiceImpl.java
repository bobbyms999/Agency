package com.accredilink.bgv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accredilink.bgv.entity.Employee;
import com.accredilink.bgv.repository.EmployeeRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> searchEmployee() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}
}
