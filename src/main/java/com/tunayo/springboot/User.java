package com.tunayo.springboot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import lombok.Data;


@Data
@Entity
@Table(name = "user")
//@Component
//@SessionScope
public class User implements Serializable{
	private static final long serialVersionUID = 4674112863194397526L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	
	private Integer id;
	
	@Column(length = 15,nullable = false)
	@NotEmpty
	@NotNull
	private String name;
	
	@Column(length = 10,nullable = false)
	private String job;
	
	@Column(length = 15,nullable = false)
	@NotEmpty
	@NotNull
	private String pass;
	
	
	
	@Column(length = 200)
	@Email
	private String mail;
	
	@Column(length = 3)
	private String type;
	
	@Column(length = 5,nullable = false)
	private String favorite;
	
	
	
	
}



