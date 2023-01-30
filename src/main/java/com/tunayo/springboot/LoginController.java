package com.tunayo.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tunayo.springboot.repositories.MyDataRepository;
import com.tunayo.springboot.repositories.MyDataRepository3;

import com.tunayo.springboot.Cart;
import com.tunayo.springboot.CartLogic;
import com.tunayo.springboot.MixCart;


@Controller
//@SessionAttributes(types = User.class) 


public class LoginController {
	
	
	@Autowired
	MyDataRepository repository;
	
	@Autowired
	LoginUser loginUser;
	
	
	@RequestMapping(value = "/made",method = RequestMethod.POST)

	public ModelAndView made(
			@RequestParam("checkbox")int[] id,
			ModelAndView mav) {
		
		
		List<Flavor> listA = new ArrayList<Flavor>();
		List<Integer> i = new ArrayList<Integer>();
		List<String> s = new ArrayList<String>();
		for (int o : id) {
			Flavor fla = repository.findById(o);
			i.add(fla.getValue());
			listA.add(fla);
			s.add(fla.getName());
		}
		int sum = i.stream().reduce(0, Integer::sum);
		String str = String.join(",", s);
		//int r = str.length()-1;
		//String str2 = str.substring(1, r);
		
		mav.addObject("datalist",listA);
		mav.addObject("total",sum);
		mav.addObject("consname",str);
		mav.addObject("loginUser",loginUser);
		mav.setViewName("make");
		
		
		return mav;
	}

    
	@Autowired
	MyDataRepository3 repository3;
	
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mav) {
 
		if(loginUser.isLogin()) {
			mav.setViewName("index3");
			mav.addObject("loginUser",loginUser);
			mav.addObject("msg","ようこそ");
		}else {
			mav.setViewName("login");
			mav.addObject("msg","check login.");
		}
		
		return mav;
	}
	@RequestMapping("/success")
	public ModelAndView login(
			@RequestParam("name")String name,
			@RequestParam("password")String pass,
			ModelAndView mav) {
		String password = "";
		User use = repository3.findByName(name);
		if(use == null) {
			mav.addObject("msg","false");
			mav.setViewName("index4");
		}else {
		password= use.getPass();
		}
		if(password.equals(pass) && pass != null) {
			loginUser.setLogin(true);
			loginUser.setName(use.getName());
			loginUser.setId(use.getId());
			loginUser.setMail(use.getMail());
			loginUser.setFavorite(use.getFavorite());
			loginUser.setJob(use.getJob());
			loginUser.setPass(use.getPass());
			loginUser.setType(use.getType());
			
			/*Cart cart = loginUser.getCart();
			List<MixCart> mixCart = new ArrayList<MixCart>();
			MixCart mix = new MixCart("kk",1);
			mixCart.add(mix);
			cart.setList(mixCart);
			
			MixCart mixC =new MixCart("ll",200);
			CartLogic logic=new CartLogic();
			logic.execute(cart, mixC);*/
			
			
			
			mav.setViewName("index3");
			mav.addObject("loginUser",loginUser);
			
			
			
			mav.addObject("msg","ようこそ");
		}
		else {
			mav.addObject("msg","false");
			mav.setViewName("index4");
		}
		
		return mav;	
	}
	
	@RequestMapping("/logout")
	public ModelAndView loginOut(
			ModelAndView mav) {
		//session dataを消す処理が必要
		loginUser.setLogin(false);
		
		mav.setViewName("login");
			
		return mav;	
	}
	
	@RequestMapping("/changeUser")
	public ModelAndView change(
			ModelAndView mav) {
			mav.addObject("loginUser",loginUser);
			mav.setViewName("changeUser");
			return mav;
		}
			
	
	
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public ModelAndView send(
			@RequestParam("id")int id,
			@RequestParam("password")String pass, 
			
			//BindingResult result,
			ModelAndView mav) {
		System.out.println(id);
		String password="";
		//if(!result.hasErrors()) {
		User use = repository3.findById(id);
		
		if(use == null) {
			mav.addObject("msg","logfailed.");
		}else {
		password= use.getPass();
		}

		
		if(password.equals(pass) && pass != null) {
			Iterable<User> list = repository3.findByIdAndPass(id,pass);
			mav.addObject("datalist",list);
			mav.addObject("msg","sucess.");
		
			
		}
		else {
			mav.addObject("msg","failed.");
		}
		
			
		
		/*}else {
			mav.addObject("msg","muriyansore.");
			System.out.println("muri");
		}*/
		mav.setViewName("login");
		return mav;
		
		
	}
	
	
	
}
