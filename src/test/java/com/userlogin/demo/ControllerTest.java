package com.userlogin.demo;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.userlogin.demo.dao.UserDAO;
import com.userlogin.demo.model.User;
import com.userlogin.demo.service.UserServiceImpl;






@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)

public class ControllerTest {


    private static final ObjectMapper om = new ObjectMapper();

    @Autowired(required=true)
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl service;
    
    
    /*@Test
    public void save_user_OK() throws Exception {
    	User u = new User("jkl@gmail", "890");
    	when(mockRepository.save(u)).thenReturn(u);
    	this.mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                
                .andExpect(jsonPath("$.uemailid", is(u.getUemailid())))
                .andExpect(jsonPath("$.upass", is(u.getUpass())));

        verify(mockRepository, times(1)).save(u);
    }*/
    
	@Test
	public void testSaveEmployee() throws Exception
	{
		String URI="/user/register";
		User u1=new User("qwe@gmail.com","qwe");
		String inputInJson=this.mapToJson(u1);
		Mockito.when(service.saveUser(Mockito.any(User.class))).thenReturn(u1);
		RequestBuilder requestBuilder=MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result=mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response=result.getResponse();
		
		String outputInJson=response.getContentAsString();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		
	}


	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
