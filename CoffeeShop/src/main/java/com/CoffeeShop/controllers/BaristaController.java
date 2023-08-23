package com.CoffeeShop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CoffeeShop.models.OrderModel;
import com.CoffeeShop.services.BaristaService;

@RestController  
public class BaristaController {

	@Autowired
	BaristaService baristaService;
	
	@PostMapping("/barista/processOrder")  
	private void postOrder(@RequestBody OrderModel order)   
	{  
		baristaService.processOrder(order);  
	}
}
