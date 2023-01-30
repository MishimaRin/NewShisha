package com.tunayo.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;


@Data
@Entity
@Table(name = "review")
public class Review {
	
	@Id
	@Column(nullable = false)
	private Integer id;
	
	@Column(nullable = true)
	private String review;
	
	@Column(nullable = true)
	private String name;
	
	@Min(1)
	@Max(10)
	@Column(nullable = false)
	private Integer star;
	
	@Column(nullable = false)
	private String date;
	
}


