package com.CoffeeShop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CoffeeShop.services.OrderService;
import com.CoffeeShop.models.OrderModel;

@RestController  
public class OrderController {
	
	@Autowired
	OrderService orderService;

	
	@GetMapping("/order")  
	private List<OrderModel> listOrders()   
	{  
		return orderService.listOrders();  
	}  
	
	@DeleteMapping("/order/{orderNumber}")  
	private void cancelOrder(@PathVariable("orderNumber") int orderNumber)   
	{  
		orderService.cancelOrder(orderNumber);  
	}  

	@PostMapping("/order")  
	private OrderModel createOrder(@RequestBody OrderModel order)   
	{  
		return orderService.createOrder(order);  
	}
	
	@GetMapping("/order/processOrder/{orderNumber}")  
	private OrderModel processOrder(@PathVariable("orderNumber") int orderNumber)   
	{  
		OrderModel order = orderService.getOrder(orderNumber);
		order.advancePreparationState();
		return orderService.postOrder(order);
	} 
	
	@GetMapping("order/finishedOrder/{orderNumber}")  
	private void finishedOrder(@PathVariable("orderNumber") int orderNumber) 
	{
		System.out.println("Order number "+orderNumber+" has been picked up by the customer.");
		orderService.deleteOrder(orderNumber);
	}
	
}
