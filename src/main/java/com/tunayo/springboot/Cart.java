package com.tunayo.springboot;

import java.io.Serializable;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;



public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<MixCart> list;
	private int total;
	public Cart() {
		super();
		list=new ArrayList<>();
		
	}

	public List<MixCart> getList() {
		return list;
	}
	public void setList(List<MixCart> list) {
		this.list = list;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

}