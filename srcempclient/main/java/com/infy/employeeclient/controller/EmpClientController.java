package com.infy.employeeclient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.employeeclientModel.EmpList;
import com.infy.employeeclientModel.Employee;
import com.infy.employeeclientModel.Gender;

@RestController
@RequestMapping(value="/")
public class EmpClientController {
	
//	@Autowired
//	private RestTemplate restTemplate;
	
	@RequestMapping(value="GET/test-client/v1/female-employees", method=RequestMethod.GET)
	public List<Employee> getFemaleEmployees() throws Exception{
		RestTemplate restTemplate = new RestTemplate(); 
		EmpList empList = restTemplate.getForObject("http://localhost:8082/GET/test-server/v1/get-employees",EmpList.class);
		System.out.print("hulhul"+empList);
		List<Employee> femaleList = new ArrayList<Employee>();
		
		for(Employee emp:empList.getEmpList()) {
			if(emp.getGender()==Gender.FEMALE) femaleList.add(emp);
		}
		return femaleList;
	}
	@RequestMapping(value="POST/test-client/v1/employee", method=RequestMethod.POST)
	public String addEmployee(@RequestBody Employee employee) throws Exception{
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Object> requestEntity = new HttpEntity(employee,headers);
		
		RestTemplate restTemplate = new RestTemplate(); 
		ResponseEntity<String> res = restTemplate.exchange("http://localhost:8082/POST/test-server/v1/employee",
				HttpMethod.POST,requestEntity,String.class);
		
		System.out.println(res.getHeaders());
		System.out.print(res.getStatusCode());
		
		return res.getBody();
	}
}
