package com.example.demo.activiti;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ActivitiController.class})
@ExtendWith(SpringExtension.class)
public class ActivitiControllerTest {
    @Autowired
    private ActivitiController activitiController;

    @MockBean
    private RuntimeService runtimeService;

    @MockBean
    private TaskService taskService;

    @Test
    public void testGetVariables() throws Exception {
        // Arrange
        when(this.taskService.getVariables(anyString())).thenReturn(new HashMap<String, Object>(1));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/var/{processInstanceId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.activitiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("{}")));
    }

    @Test
    public void testTest() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getIds");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.activitiController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("{}")));
    }

    @Test
    public void testTest2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/getIds");
        getResult.contentType("Not all who wander are lost");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(this.activitiController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("{}")));
    }
}

