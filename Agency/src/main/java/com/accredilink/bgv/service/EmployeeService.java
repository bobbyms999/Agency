package com.accredilink.bgv.service;

import com.accredilink.bgv.entity.Employee;
import com.accredilink.bgv.util.ResponseObject;

public interface EmployeeService {
	
	public ResponseObject createEmployee(Employee employee);
		
	public ResponseObject deleteEmployee(int employeeId);
	
	

}
