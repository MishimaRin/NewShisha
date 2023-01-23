package com.tunayo.springboot;

public class CartLogic {
	public void execute(Cart cart,MixCart mixCart) {
		cart.getList().add(mixCart);
		cart.setTotal(cart.getTotal()+mixCart.getPrice());
	}
}
