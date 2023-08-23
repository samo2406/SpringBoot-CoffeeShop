package com.CoffeeShop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.CoffeeShop.models.OrderModel;
import com.CoffeeShop.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired  
	OrderRepository orderRepository;  

	public OrderModel getOrder(int orderNumber) {
		return orderRepository.findById(orderNumber).get(); 
	}
	
	public OrderModel postOrder(OrderModel order) {
		return orderRepository.save(order);  
	}
	
	public OrderModel createOrder(OrderModel order) {
		// Creates a HTTP header with content type set to JSON, in order to include the created order with the request
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<OrderModel> request = new HttpEntity<>(order, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		// Calls a POST request to notify the barista service, including the new order
	    restTemplate.postForEntity("http://localhost:8080/barista/processOrder", request, null);
		return orderRepository.save(order);
	}
	
	public void cancelOrder(int orderNumber) {
		OrderModel order = this.getOrder(orderNumber);
		// An order, which is already in preparation, cannot be cancelled
		if (order.getPreparationState().toString() == "waiting") {
			orderRepository.deleteById(orderNumber);
		} 
	}
	
	public void deleteOrder(int orderNumber) {
		orderRepository.deleteById(orderNumber);
	}
	
	public List<OrderModel> listOrders() {
		List<OrderModel> orders = new ArrayList<OrderModel>();  
		orderRepository.findAll().forEach(order -> orders.add(order));  
		return orders;  	
	}
}
