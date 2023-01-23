package com.tunayo.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


@Data
@Entity
@Table(name = "composition")
public class Composition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	
	private Integer id;
	
	@Column(nullable = false)
	private Integer mixid;
	
	
	@Column(length = 15,nullable = false)
	private Integer flavorid;
	
}


