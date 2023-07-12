package com.accenture.lkm.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.accenture.lkm.PizzaOrderProducer;
import com.accenture.lkm.dao.PizzaDao;
import com.accenture.lkm.dto.PizzaCustomerContactNumberDto;
import com.accenture.lkm.dto.PizzaNameDto;
import com.accenture.lkm.dto.PizzaOrderDto;
import com.accenture.lkm.model.PizzaOrderEntity;

//Following Annotation is used to tell that SpringJunit is used to run the tests 
@RunWith(SpringJUnit4ClassRunner.class)

//Following Annotation is replacement of @Configuration annotation
//it is used to point to the files having the configuration and helps to load and start the context
//Context will be cached for all test cases and classes
@SpringBootTest(classes = PizzaOrderProducer.class)

//Following Annotation is used to run each test case in a individual Transaction
//with default strategy as rollback, as service layer is hitting DB layer
//so changes done to database must be undone
//@Transactional
public class PizzaServiceTest {

	@Mock
	private PizzaDao pizzaDao;

	@InjectMocks
	private PizzaService pizzaService;

	private PizzaOrderDto dto;

	private PizzaNameDto nameDto;

	private PizzaCustomerContactNumberDto contactNumberDto;

	private PizzaOrderEntity orderEnt;

	public static HttpHeaders getHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		return headers;
	}

	@Before
	public void setup() {
		// this is done to initialize mockito annotations for mocking
		// prepare the objects for testing

		// Step2: Using Use MockMvcBuilders to create a MockMvc which is replica just of
		// Controller
		MockitoAnnotations.initMocks(this);

		// Sample object for testing
		dto = new PizzaOrderDto();
		dto.setBill(1.1);
		dto.setCustomerContactNumber("111-222-333");
		dto.setOrderId(1);
		dto.setPizzaName("test");
		dto.setQuantity(1);

		orderEnt = new PizzaOrderEntity();
		BeanUtils.copyProperties(dto, orderEnt);

		nameDto = new PizzaNameDto();
		nameDto.setPizzaName("test2");

		contactNumberDto = new PizzaCustomerContactNumberDto();
		contactNumberDto.setCustomerContactNumber("111-222-333");
	}

	@Test
	public void addPizzaTest() {
		PizzaOrderEntity orderEnt = new PizzaOrderEntity();
		BeanUtils.copyProperties(dto, orderEnt);
		orderEnt.setOrderId(1);
		PizzaOrderEntity orderEntResult = orderEnt;
		when(pizzaDao.save(any(PizzaOrderEntity.class))).thenReturn(orderEntResult);
		int result = pizzaService.addPizza(dto);
		Assertions.assertThat(result);
	}

	@Test
	public void getallDetailsByPizaaNameTest() {
		when(pizzaDao.findByPizzaName(anyString())).thenReturn(Arrays.asList(orderEnt));
		List<PizzaOrderDto> pizzaEntList = pizzaService.getallDetailsByPizaaName(dto.getPizzaName());
		assertThat(pizzaEntList.size()).isEqualTo(1);
		assertThat(pizzaEntList.get(0).getOrderId()).isEqualTo(dto.getOrderId());
	}

	@Test
	public void getOrderDetailsByContactNumberTest() {
		when(pizzaDao.getListByCustomerContactNumber(anyString())).thenReturn(Arrays.asList(orderEnt));
		List<PizzaOrderDto> pizzaEntList = pizzaService.getOrderDetailsByContactNumber(dto.getCustomerContactNumber());
		assertThat(pizzaEntList.size()).isEqualTo(1);
		assertThat(pizzaEntList.get(0).getPizzaName()).isEqualTo(dto.getPizzaName());
	}

	@Test
	public void getallDetailsTest() {
		List<PizzaOrderEntity> beanList = Arrays.asList(orderEnt);
		// List<PizzaOrderDto> dtoList = Arrays.asList(dto);

		when(pizzaDao.findAll()).thenReturn(beanList);

		List<PizzaOrderDto> dtoListRes = pizzaService.getallDetails();
		assertThat(dtoListRes.size()).isEqualTo(beanList.size());
		assertThat(dtoListRes.get(0).getPizzaName()).isEqualTo(dto.getPizzaName());
	}
}
