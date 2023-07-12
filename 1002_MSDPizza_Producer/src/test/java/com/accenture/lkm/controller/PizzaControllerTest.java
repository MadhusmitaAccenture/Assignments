package com.accenture.lkm.controller;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.accenture.lkm.EurekaProducer;
import com.accenture.lkm.dto.PizzaNameDto;
import com.accenture.lkm.dto.PizzaOrderDto;
import com.accenture.lkm.service.PizzaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//Following Annotation is used to tell that SpringJunit is used to run the tests
@RunWith(SpringJUnit4ClassRunner.class)

//Following Annotation is replacement of @Configuration annotation
//it is used to point to the files having the configuration and helps to load and start the context
//Context will be cached for all test cases and classes
@SpringBootTest(classes = EurekaProducer.class)
//No @Transactional Required as database is never hit
public class PizzaControllerTest {

	@Mock
	private PizzaService pizzaService;

	@InjectMocks
	PizzaController controller;

	protected MockMvc mockMVC;

	private HttpHeaders headersMock;

	private final ObjectMapper mapper = new ObjectMapper();

	private PizzaOrderDto dto;

	private PizzaNameDto nameDto;

	public static HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		return headers;
	}

	@Before
	public void mySetup() {
		// this is done to initialize mockito annotations for mocking
		// prepare the objects for testing

		// Step2: Using Use MockMvcBuilders to create a MockMvc which is replica just of
		// Controller
		MockitoAnnotations.initMocks(this);
		mockMVC = MockMvcBuilders.standaloneSetup(controller).build();

		headersMock = getHttpHeaders();

		// Sample object for testing
		dto = new PizzaOrderDto();
		dto.setBill(1.1);
		dto.setCustomerContactNumber("111-222-333");
		dto.setOrderId(1);
		dto.setPizzaName("test");
		dto.setQuantity(1);

		nameDto = new PizzaNameDto();
		nameDto.setPizzaName("test2");
	}

	@Test
	public void addPizzaTest() throws Exception {
		String postUri = "/pizzaorder";
		when(pizzaService.addPizza(dto)).thenReturn(anyInt());

		mockMVC.perform(post(postUri).headers(headersMock).content(mapper.writeValueAsString(dto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void getallDetailsByPizaaNameTest() throws JsonProcessingException, Exception {
		String postUri = "/pizzaorder/pizzaName";

		List<PizzaOrderDto> dtoList = Arrays.asList(dto);

		when(pizzaService.getallDetailsByPizaaName(nameDto.getPizzaName())).thenReturn(dtoList);

		mockMVC.perform(post(postUri).headers(headersMock).content(mapper.writeValueAsString(nameDto))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
