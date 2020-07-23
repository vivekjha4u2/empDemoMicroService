package com.infy.employeeserverController;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.employeeserverModel.Employee;
import com.infy.employeeserverModel.Gender;

@RunWith(SpringJUnit4ClassRunner.class) 
public class EmpSeverControllerTest {

	private MockMvc mockMvc;
	
	@InjectMocks
	private EmpSeverController esc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(esc).build();
	}
	
	//****mappers****//
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
     }

     //**************//
	
	@Test
	public void testGetEmployee() throws Exception{
		mockMvc.perform(get("/GET/test-server/v1/get-employees")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.empList").isArray())
			.andExpect(jsonPath("$.empList[0].id",is(1001)))
			.andExpect(jsonPath("$.empList[0].name",is("Derek")))
			.andExpect(jsonPath("$.empList[0].gender",is("MALE")));
	
	}
	
	@Test
	public void testPostEmployee() throws Exception{
		Employee emp;
		emp = new Employee(1008,"Gary",Gender.MALE);
		
		String inputJson = mapToJson(emp);		
		
		mockMvc.perform(post("/POST/test-server/v1/employee")
			.contentType(MediaType.APPLICATION_JSON)
			.content(inputJson))
			.andExpect(status().isCreated());
	}

}
