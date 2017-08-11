package com.cg.practice;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewTaskApplicationTests {

	 private MockMvc mockMvc;
	Controller controller=new Controller(); 
	HashMap<Integer,HelloWorldPojo> hm=new HashMap<Integer,HelloWorldPojo>();
	
	
	 @Autowired
	 private WebApplicationContext webApplicationContext;
	
	    int id=1;
		String msg="Hello World";
		HelloWorldPojo pojo= new HelloWorldPojo();
		
	
	 
	 @Before
	 public void setup() {
	  //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	  hm.put(pojo.getId(),pojo);
	 }
	
	 @Rule
	    public ExpectedException thrown = ExpectedException.none();
	 
	@Test(expected=EmptyException.class)
	public void testput() throws EmptyException {
		
		
		//mockMvc.perform(get("/hello-world-service/hello"));/*.andExpect(status().isInternalServerError())*/
	    // .andExpect(content().contentType("application/json;charset=UTF-8"))
	     //.andExpect((ResultMatcher) controller.hm.values());
	    // .andExpect(jsonPath("$.msg").value("Hello World"));
		//assertEquals(pojo,controller.update(pojo.getId(),pojo));
		assertTrue(EqualsBuilder.reflectionEquals(pojo,controller.update(pojo.getId(),pojo)));
		
	}

	@Test(expected=EmptyException.class)
	public void testget() throws EmptyException {
		assertTrue(EqualsBuilder.reflectionEquals(hm,controller.retrival()));
	}
	
	@Test(expected=EmptyException.class)
	public void testPost() throws EmptyException {
		String Stored="Stored Succesfully";
		assertTrue(EqualsBuilder.reflectionEquals(pojo,controller.greeting(pojo)));
		//assertEquals(Stored,controller.greeting(pojo));
	}
	
	@Test(expected=EmptyException.class)
	public void testDelete() throws EmptyException {
		assertTrue(EqualsBuilder.reflectionEquals(pojo,controller.delete(id, pojo)));
	}
	
	

}
