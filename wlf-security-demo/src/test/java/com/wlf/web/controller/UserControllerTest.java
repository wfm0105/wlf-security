package com.wlf.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(get("/user")
			   .param("username", "admin")
			   .param("status", "1")
			   .param("size", "6")
			   .param("page", "1")
			   .param("sort", "username")
			   .contentType(MediaType.APPLICATION_JSON_UTF8))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.length()").value(3))
			   .andReturn().getResponse().getContentAsString();
	
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/user/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
				    .andExpect(status().isOk())
				    .andExpect(jsonPath("$.username").value("admin"))
				    .andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(get("/user/a")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		String content = "{\"username\":\"admin\",\"password\":null}";
		
		mockMvc.perform(post("/user")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.userid").value("1"));
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		String content = "{\"id\":\"1\",\"username\":\"admin\",\"password\":null}";
		
		mockMvc.perform(put("/user/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(content))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.userid").value("1"));
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/user/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8))
					.andExpect(status().isOk());
	}
	
}
