package com.accenture.lkm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.lkm.dao.PizzaDao;
import com.accenture.lkm.dto.PizzaOrderDto;
import com.accenture.lkm.model.PizzaOrderEntity;

@Service
public class PizzaService {

	@Autowired
	private PizzaDao pizzaDao;

	/**
	 * Add Pizza Details
	 * 
	 * @param pizzaDto
	 * @return int
	 */
	public int addPizza(PizzaOrderDto pizzaDto) {
		PizzaOrderEntity pizzaOrdEntity = new PizzaOrderEntity();
		BeanUtils.copyProperties(pizzaDto, pizzaOrdEntity);
		PizzaOrderEntity pizzaOrdEnt = (PizzaOrderEntity) pizzaDao.save(pizzaOrdEntity);
		return pizzaOrdEnt.getOrderId();
	}

	/**
	 * Find list of PizzaOrders by PizzaName
	 * 
	 * @param pizzaName
	 * @return List<PizzaOrderDto>
	 */
	public List<PizzaOrderDto> getallDetailsByPizaaName(String pizzaName) {
		List<PizzaOrderEntity> pizzaEntList = pizzaDao.findByPizzaName(pizzaName);
		List<PizzaOrderDto> listPizzaDto = new ArrayList<> ();
		for (PizzaOrderEntity entity : pizzaEntList) {
			PizzaOrderDto empDto = new PizzaOrderDto();
			BeanUtils.copyProperties(entity, empDto);
			listPizzaDto.add(empDto);
		}
		return listPizzaDto;
	}

	/**
	 * Get all Pizza Details
	 * 
	 * @return List<PizzaOrderDto>
	 */
	public List<PizzaOrderDto> getallDetails() {
		List<PizzaOrderEntity> pizzaEntList = pizzaDao.findAll();
		List<PizzaOrderDto> listPizzaDto = new ArrayList<> ();
		for (PizzaOrderEntity entity : pizzaEntList) {
			PizzaOrderDto empDto = new PizzaOrderDto();
			BeanUtils.copyProperties(entity, empDto);
			listPizzaDto.add(empDto);
		}
		return listPizzaDto;
	}
	
	/**
	 * GetOrderDetailsByContactNumber
	 * 
	 * @param customerContactNumber
	 * @return List<PizzaOrderDto>
	 */
	public List<PizzaOrderDto> getOrderDetailsByContactNumber(String customerContactNumber) {
		List<PizzaOrderEntity> pizzaEntList = pizzaDao.getListByCustomerContactNumber(customerContactNumber);
		List<PizzaOrderDto> listPizzaDto = new ArrayList<> ();
		for (PizzaOrderEntity entity : pizzaEntList) {
			PizzaOrderDto empDto = new PizzaOrderDto();
			BeanUtils.copyProperties(entity, empDto);
			listPizzaDto.add(empDto);
		}
		return listPizzaDto;
	}
}
