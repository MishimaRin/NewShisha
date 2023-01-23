package com.tunayo.springboot;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import lombok.Data;
import com.tunayo.springboot.Cart;




@Data
@Component
@SessionScope
@Valid
public class LoginUser {
	private boolean isLogin;
	private Integer id;
	private String name;
	private String type;
	private String job;
	private String mail;
	private String pass;
	private String favorite;
	private Cart cart;
	private int total;
	private List<Integer>count;
	
	public LoginUser() {
		this.cart = new Cart();
		total=0;
		this.count = new ArrayList<Integer>();
	}
	
	
//session 用の箱
	
	//islogin関数　ログイン状態ならtrue
	//islogin関数　ログアウト状態ならfalse
}
