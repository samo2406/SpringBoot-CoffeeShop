package com.CoffeeShop.services;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.CoffeeShop.models.OrderModel;

@Service
public class BaristaService {
	ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	RestTemplate restTemplate = new RestTemplate();
	private int timeDelay = 10;

	// Advances the order through processing states after a time delay
	public void processOrder(OrderModel order) {
		HashMap<String, Long> params = new HashMap<>();
		long orderNumber = order.getOrderNumber();
		params.put("orderNumber", orderNumber);
		
		Runnable saveOrder = () -> {
			if (!order.getPreparationState().isLast()) {
				// Calls a GET request to process the order
				ResponseEntity<OrderModel> response = restTemplate.getForEntity("http://localhost:8080/order/processOrder/{orderNumber}", OrderModel.class, params);
				// Calls this function again, until order reaches the last state (picked_up)
				this.processOrder((OrderModel) response.getBody());
			} else {
				// Once the last processing state has been reached, notify the order service
				restTemplate.getForEntity("http://localhost:8080/order/finishedOrder/{orderNumber}", null, params);
			}
		};
		// Schedules the process advancement
		executorService.schedule(saveOrder, timeDelay, TimeUnit.SECONDS);
	}

}
