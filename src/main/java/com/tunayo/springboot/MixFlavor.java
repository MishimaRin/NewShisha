package com.tunayo.springboot;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;

import lombok.Data;


@Data
@Entity
@Table(name = "mix_flavor")
public class MixFlavor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	
	private Integer id;
	
	@Column(length = 15,nullable = false)
	@NotEmpty
	private String name;
	
	@Column(length = 15,nullable = false)
	private String genre;
	
	@Column
	@Min(1)
	@Max(5000)
	private Integer value;
	
	@Column(length = 2,nullable = false)
	private String serve;
	
	@Column
	private String exp;
	
	@Column(nullable = false)
	private String cons;
	
	
}


