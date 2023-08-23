package com.CoffeeShop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class OrderModel {
	private enum COFFEE {
	    long_black,
	    latte,
	    cappuccino,
	    espresso
	}
	
	private enum SIZE {
		small,
		medium,
		large
	}
	
	private enum MILK {
		cow_milk,
		soy_milk,
		almond_milk 
	}
	
	public enum STATE {
		waiting,
		in_preparation,
		finished,
		picked_up {
	        @Override
	        public STATE next() {
	            return values()[ordinal()];
	        };
	        
	        @Override
	        public boolean isLast() {
		    	return true;
		    }
	    };

	    public STATE next() {
	        return values()[ordinal() + 1];
	    }
	    
	    public boolean isLast() {
	    	return false;
	    }
	}
	
	@Id
	@Column
	private int orderNumber;
	@Column
	private COFFEE coffeeType;
	@Column
	private SIZE coffeeSize;
	@Column
	private MILK milkType;
	@Column
	private Boolean takeaway;
	@Column
	private float price;
	@Column
	private STATE preparationState;

	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public COFFEE getCoffeeType() {
		return coffeeType;
	}

	public void setCoffeeType(COFFEE coffeeType) {
		this.coffeeType = coffeeType;
	}
	
	public SIZE getCoffeeSize() {
		return coffeeSize;
	}

	public void setCoffeeSize(SIZE coffeeSize) {
		this.coffeeSize = coffeeSize;
	}

	public MILK getMilkType() {
		return milkType;
	}

	public void setMilkType(MILK milkType) {
		this.milkType = milkType;
	}

	public Boolean getTakeaway() {
		return takeaway;
	}

	public void setTakeaway(Boolean takeaway) {
		this.takeaway = takeaway;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public STATE getPreparationState() {
		return preparationState;
	}

	public void setPreparationState(STATE preparationState) {
		this.preparationState = preparationState;
	}
	
	public void advancePreparationState() {
		this.setPreparationState(this.preparationState.next());
	}
}
