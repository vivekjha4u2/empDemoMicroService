package com.infy.employeeserverController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.employeeserverModel.EmpList;
import com.infy.employeeserverModel.Employee;
import com.infy.employeeserverModel.Gender;

@Controller
@RestController
@RequestMapping(value="/")
public class EmpSeverController {

	List<Employee> el = new ArrayList<Employee>();
	
	@RequestMapping(value="GET/test-server/v1/get-employees", method=RequestMethod.GET)
	public EmpList getEmployees() throws Exception{
		el.add(new Employee(1001,"Derek",Gender.MALE));
		el.add(new Employee(1002,"Sally",Gender.FEMALE));
		el.add(new Employee(1003,"Mark",Gender.MALE));
		el.add(new Employee(1004,"Selena",Gender.FEMALE));
		el.add(new Employee(1005,"Dustin",Gender.MALE));
		el.add(new Employee(1006,"Sheryl",Gender.FEMALE));
		EmpList empList = new EmpList(el);
		
		return empList;
		
	}
	@RequestMapping(value="POST/test-server/v1/employee",method=RequestMethod.POST)
	public ResponseEntity<String> addEmployee(@RequestBody Employee employee) throws Exception{
		el.add(employee);
		String s = "Employee add successfully with empid "+Integer.toString(employee.getId());
		ResponseEntity<String> response = new ResponseEntity<String>(s , HttpStatus.CREATED);
		return response;
	}
}
