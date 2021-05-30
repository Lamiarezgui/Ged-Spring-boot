package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration("/processes/TaskManager.bpmn20.xml")
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void givenProcess_whenStartProcess_thenIncreaseInProcessInstanceCount()
			throws Exception {

		String responseBody = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/start-process"))
				.andReturn().getResponse().getContentAsString();

		assertEquals("Process started. Number of currently running"
				+ " process instances = 1", responseBody);

		responseBody = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/start-process"))
				.andReturn().getResponse().getContentAsString();

		assertEquals("Process started. Number of currently running"
				+ " process instances = 2", responseBody);

		responseBody = this.mockMvc
				.perform(MockMvcRequestBuilders.get("/start-process"))
				.andReturn().getResponse().getContentAsString();

		assertEquals("Process started. Number of currently running"
				+ " process instances = 3", responseBody);
	}


}
