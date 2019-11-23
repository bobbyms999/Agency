package com.accredilink.bgv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accredilink.bgv.entity.Employee;
import com.accredilink.bgv.service.SearchService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SearchController {
	
	@Autowired
	SearchService searchService;

	@GetMapping("/fetchall")
	public List<Employee> search(){
		return searchService.searchEmployee();
	}
	

}
