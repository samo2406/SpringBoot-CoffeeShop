package com.CoffeeShop.repository;

import org.springframework.data.repository.CrudRepository;  
import com.CoffeeShop.models.OrderModel;  
public interface OrderRepository extends CrudRepository<OrderModel, Integer>  
{  
}  
